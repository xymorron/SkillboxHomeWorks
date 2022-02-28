package com.example.bookshop.controllers;

import com.example.bookshop.data.ResourceStorage;
import com.example.bookshop.data.struct.book.Book;
import com.example.bookshop.data.struct.book.BookRateEntity;
import com.example.bookshop.data.struct.book.review.BookReviewEntity;
import com.example.bookshop.data.struct.service.BookService;
import com.example.bookshop.data.struct.service.BooksRatingAndPopularityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.logging.Logger;

@Controller
public class BookPageController {

    private final BookService bookService;
    private final BooksRatingAndPopularityService booksRatingAndPopularityService;
    private final Logger logger;
    private final ResourceStorage storage;

    @Autowired
    public BookPageController(BookService bookService, ResourceStorage storage,
                              BooksRatingAndPopularityService booksRatingAndPopularityService) {
        this.bookService = bookService;
        this.storage = storage;
        this.booksRatingAndPopularityService = booksRatingAndPopularityService;
        logger = Logger.getLogger(BookPageController.class.getName());
    }

    @GetMapping("/books/{slug}")
    public String bookPage(@PathVariable("slug") String slug, Model model, Authentication authentication) {
        logger.info("Slug page query received for bookSlug = " + slug);
        int userId = 47;
        Book book = bookService.getBookBySlug(slug);
        BookRateEntity bookRateEntity = booksRatingAndPopularityService.getBookRateEntityByUserIdAndBookId(userId, book.getId());
        int bookRateByUser = bookRateEntity != null ? bookRateEntity.getRate() : 0;
        logger.info("Slug " + slug + ", resolved to book " + book);
        book.getBookReviewEntities().sort(Comparator.comparing(BookReviewEntity::getTotalRate).reversed());
        book.getBookFiles().sort(Comparator.comparing(o -> o.getBookFileType().getName()));
        model.addAttribute("book", book);
        model.addAttribute("bookRateByUser", bookRateByUser);

        if (authentication != null && authentication.isAuthenticated()) {
            return "/books/slugmy";
        }
        return "/books/slug";
    }

    @PostMapping("/books/{slug}/img/save")
    public String saveNewBookImage(@RequestParam("file") MultipartFile file, @PathVariable("slug") String slug) throws IOException {
        String savePath = storage.saveNewBookImage(file, slug);
        Book book = bookService.getBookBySlug(slug);
        book.setImage(savePath);
        bookService.saveBook(book);

        return ("redirect:/books/" + slug);
    }

    @GetMapping("/books/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash") String hash) throws IOException {
        Path path = storage.getBookFilePath(hash);
        MediaType mediaType = storage.getBookFileMime(hash);
        byte[] data = storage.getBookFileByteArray(hash);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
    }
}
