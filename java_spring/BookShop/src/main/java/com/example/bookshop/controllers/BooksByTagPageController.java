package com.example.bookshop.controllers;

import com.example.bookshop.data.struct.service.BookService;
import com.example.bookshop.data.struct.service.TagService;
import com.example.bookshop.data.struct.book.Book;
import com.example.bookshop.data.struct.tag.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BooksByTagPageController {

    final private BookService bookService;
    final private TagService tagService;
    @Autowired
    public BooksByTagPageController(BookService bookService, TagService tagService) {
        this.bookService = bookService;
        this.tagService = tagService;
    }

    @ModelAttribute("bookList")
    public List<Book> bookList() {
        return bookService.getPageOfPopularBooks(0, 20);
    }


    @GetMapping(path={"/tags", "/tags/{id}"})
    public String popularPage(@PathVariable(required = false, name = "id") Integer tagId, Model model) {
        List<Book> bookList = bookService.getPageOfBooksByTag(tagId, 0, 20);
        model.addAttribute("bookList", bookList);
        TagEntity tag = tagService.getTagEntityById(tagId);
        model.addAttribute("tag", tag);
        return "tags/index";
    }

}
