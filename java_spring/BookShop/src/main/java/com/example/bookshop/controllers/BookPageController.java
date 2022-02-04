package com.example.bookshop.controllers;

import com.example.bookshop.data.ResourceStorage;
import com.example.bookshop.data.struct.Author;
import com.example.bookshop.data.struct.book.Book;
import com.example.bookshop.data.struct.book.file.BookFile;
import com.example.bookshop.data.struct.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class BookPageController {

    private final BookService bookService;
    private final Logger logger;
    private final ResourceStorage storage;

    @Autowired
    public BookPageController(BookService bookService, ResourceStorage storage) {
        this.bookService = bookService;
        this.storage = storage;
        logger = Logger.getLogger(BookPageController.class.getName());
    }

    @GetMapping("/books/{slug}")
    public String bookPage(@PathVariable("slug") String slug, Model model) {
        logger.info("Slug page query received for bookSlug = " + slug);
        Book book = bookService.getBookBySlug(slug);
        List<Author> authors = book.getAuthors().stream()
                .sorted(Comparator.comparing(Author::getName))
                .collect(Collectors.toList());
        List<BookFile> bookFiles = book.getBookFiles().stream()
                .sorted(Comparator.comparing(o -> o.getBookFileType().getName()))
                .collect(Collectors.toList());
        logger.info("Slug " + slug + ", resolved to book " + book);
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("bookFiles", bookFiles);
        return "/books/slug";
//        return "/books/slugmy";
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
