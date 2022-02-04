package com.example.bookshop.controllers;

import com.example.bookshop.data.struct.Author;
import com.example.bookshop.data.struct.book.Book;
import com.example.bookshop.data.struct.service.AuthorService;
import com.example.bookshop.data.struct.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class BooksByAuthorPageController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BooksByAuthorPageController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/books/author/{slug}")
    public String booksByAuthorPage(@PathVariable("slug") String slug, Model model) {
        Author author = authorService.getAuthorBySlug(slug);
        List<Book> bookList = bookService.getPageOfBooksByAuthor(author, 0, 20);
        model.addAttribute("author", author);
        model.addAttribute("bookList", bookList);
        return "books/author";
    }
}
