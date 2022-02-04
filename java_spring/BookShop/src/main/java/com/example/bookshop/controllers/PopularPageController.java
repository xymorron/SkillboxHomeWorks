package com.example.bookshop.controllers;

import com.example.bookshop.data.struct.book.Book;
import com.example.bookshop.data.struct.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books/popular")
public class PopularPageController {

    final private BookService bookService;


    @Autowired
    public PopularPageController(BookService bookService) {
        this.bookService = bookService;

    }

    @ModelAttribute("bookList")
    public List<Book> bookList() {
        return bookService.getPageOfPopularBooks(0, 20);
    }


    @GetMapping
    public String popularPage() {
        return "books/popular";
    }
}
