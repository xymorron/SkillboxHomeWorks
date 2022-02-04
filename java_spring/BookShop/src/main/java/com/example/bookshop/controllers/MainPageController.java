package com.example.bookshop.controllers;

import com.example.bookshop.data.struct.service.TagService;
import com.example.bookshop.data.struct.book.Book;
import com.example.bookshop.data.struct.service.BookService;
import com.example.bookshop.data.struct.tag.RatedTag;
import com.example.bookshop.data.struct.tag.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class MainPageController {

    private final BookService bookService;
    private final TagService tagService;

    @Autowired
    public MainPageController(BookService bookService, TagService tagService) {
        this.bookService = bookService;
        this.tagService = tagService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 20).getContent();
    }

    @ModelAttribute("recentBooks")
    public List<Book> recentBooks() {
        return bookService.getPageOfRecentBooks(0, 20);
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks() {
        return bookService.getPageOfPopularBooks(0, 20);
    }

    @ModelAttribute("tags")
    public List<RatedTag> tags() {
        List<TagEntity> tags = tagService.getTags();
        List<RatedTag> ratedTags = new ArrayList<>();
        int tagsSize = tags.size();
        for (int i = 0; i < tagsSize; i++) {
            String tagSizeIdentifier;
            float sizeRate = (float) i / tagsSize;
            if (sizeRate < 0.05)
                tagSizeIdentifier = " Tag_lg";
            else if (sizeRate < 0.2)
                tagSizeIdentifier = " Tag_md";
            else if (sizeRate < 0.8)
                tagSizeIdentifier = "";
            else if (sizeRate < 0.95)
                tagSizeIdentifier = " Tag_sm";
            else
                tagSizeIdentifier = " Tag_xs";
            RatedTag ratedTag = new RatedTag(tags.get(i), tagSizeIdentifier);
            ratedTags.add(ratedTag);
        }
        return ratedTags.stream().sorted(Comparator.comparingInt(RatedTag::getId)).collect(Collectors.toList());
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }


}
