package com.example.bookshop.controllers;

import com.example.bookshop.errs.EmptySearchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchPageController {

    @GetMapping(value = {"/", "/{searchString}"})
    public String searchPage(Model model, @PathVariable(value = "searchString", required = false) String searchString) throws EmptySearchException {
        if (searchString != null) {
            model.addAttribute("searchString", searchString);
            return "search/index";
        } else {
            throw new EmptySearchException("Поиск по null невозможен");
        }
    }
}
