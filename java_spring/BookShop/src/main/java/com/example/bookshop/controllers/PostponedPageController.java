package com.example.bookshop.controllers;

import com.example.bookshop.data.struct.book.Book;
import com.example.bookshop.data.struct.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/postponed")
public class PostponedPageController {

    private final Logger logger = Logger.getLogger(PostponedPageController.class.getName());
    private final BookService bookService;

    @Autowired
    public PostponedPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String handlerPostponedRequest(Model model) {
        logger.info("postponed page requested");
        int userId = 47;
        List<Book> bookKept = bookService.getKeptBooksByUser(userId);
        List<String> bookIds = bookKept.stream().map(Book::getSlug).collect(Collectors.toList());
        model.addAttribute("bookKept", bookKept);
        model.addAttribute("bookIds", bookIds);
        return "postponed";
    }
}
