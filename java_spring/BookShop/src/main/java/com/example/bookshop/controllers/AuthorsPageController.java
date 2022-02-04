package com.example.bookshop.controllers;

import com.example.bookshop.data.struct.Author;
import com.example.bookshop.data.struct.book.Book;
import com.example.bookshop.data.struct.service.AuthorService;
import com.example.bookshop.data.struct.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.                                                                                                                   util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/authors")
public class AuthorsPageController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final Logger logger;

    @Autowired
    public AuthorsPageController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
        logger = Logger.getLogger(AuthorsPageController.class.getName());
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<Author>> authorsMap() {
        return authorService.getAuthorsMap();
    }

    @GetMapping
    public String authorsPage() {
        return "/authors/index";
    }

    @GetMapping("/{authorSlug}")
    public String slugPage(@PathVariable("authorSlug") String authorSlug, Model model) {
        Author author = authorService.getAuthorBySlug(authorSlug);
        logger.info("Slug page query received for authorSlug = " + authorSlug + ", author resolved to " + author);
        long booksCount = bookService.countBooksByAuthorsContains(author);
        List<Book> bookList = bookService.getPageOfBooksByAuthor(author, 0, 9);
        model.addAttribute("author", author);
        model.addAttribute("booksCount", booksCount);
        model.addAttribute("bookList", bookList);
        return "/authors/slug";
    }
}
