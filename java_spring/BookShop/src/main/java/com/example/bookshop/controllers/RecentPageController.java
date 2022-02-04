package com.example.bookshop.controllers;

import com.example.bookshop.data.struct.book.Book;
import com.example.bookshop.data.struct.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping("/books/recent")
public class RecentPageController {

    final private BookService bookService;

    @Autowired
    public RecentPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("bookList")
    public List<Book> bookList() {
        List<Book> bookList = bookService.getPageOfRecentBooks(0, 20);
        return bookList;
    }

    @ModelAttribute("defaultDate")
    public String defaultDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, -1);
        return dateFormat.format(calendar.getTime());
    }

    @GetMapping
    public String recentPage() {
        return "/books/recent";
    }
}
