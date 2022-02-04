package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.example.web.dto.BookIdToRemove;
import org.example.web.dto.RegexToRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


@Controller
@RequestMapping(value = "/books")
public class BookShelfController {

    private final Logger logger = Logger.getLogger(BookShelfController.class);
    private final BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookIdToRemove", new BookIdToRemove());
        model.addAttribute("bookList", bookService.getAllBooks());
        model.addAttribute("regexToRemove", new RegexToRemove());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("regexToRemove", new RegexToRemove());
            logger.info("error occurred while saving book");
            logger.info(bindingResult);
            return "book_shelf";
        } else {
            bookService.saveBook(book);
            logger.info("current repository contents: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/remove")
    public String removeBook(@Valid BookIdToRemove bookIdToRemove, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", new Book());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("regexToRemove", new RegexToRemove());
            logger.info("error occurred while deleting book by id");
            logger.info(bindingResult);
            return "book_shelf";
        } else {
            bookService.removeBookById(bookIdToRemove.getId());
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/removeByRegex")
    public String removeBooksByRegex(@Valid RegexToRemove regexToRemove, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", new Book());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());
            logger.info("error occurred while deleting book by regex");
            logger.info(regexToRemove);
            logger.info(bindingResult);
            return "book_shelf";
        } else {
            bookService.removeBooksByRegex(regexToRemove.getRegex());
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();
        if (name.isEmpty()) {
            logger.info("null file upload prevented");
        } else {
            byte[] bytes = file.getBytes();
            String rootPath = System.getProperty("catalina.home");
            File dir = new File(rootPath + File.separator + "external_uploads");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            logger.info("new file saved at " + serverFile.getAbsolutePath());
        }
        return "redirect:/books/shelf";
    }
}
