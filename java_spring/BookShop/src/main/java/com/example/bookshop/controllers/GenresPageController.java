package com.example.bookshop.controllers;

import com.example.bookshop.data.struct.book.Book;
import com.example.bookshop.data.struct.genre.GenreFrontView;
import com.example.bookshop.data.struct.service.BookService;
import com.example.bookshop.data.struct.service.GenreService;
import com.example.bookshop.data.struct.genre.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/genres")
public class GenresPageController {

    private final GenreService genreService;
    private final Logger logger;
    private final BookService bookService;

    @Autowired
    public GenresPageController(GenreService genreService, BookService bookService) {
        this.genreService = genreService;
        this.bookService = bookService;
        logger = Logger.getLogger(GenresPageController.class.getName());
    }

    @GetMapping
    public String genresPage(Model model) {
        List<GenreFrontView> genres = genreService.getGenresTree();
        logger.info("Genres page calles. Genres data returned: " + genres);
        model.addAttribute("genres", genres);
        return "/genres/index";
    }

    @GetMapping("/{genreSlug}")
    public String slugPage(@PathVariable(required = false, name="genreSlug") String genreSlug, Model model) {
        GenreEntity genre = genreService.getGenreEntityBySlug(genreSlug);
        logger.info("Slug page query received for genreSlug = " + genreSlug + ", genre resolved to " + genre);
        List<Book> bookList = bookService.getPageOfBooksByGenre(genre.getId(), 0, 20);
        List<GenreEntity> parents= genreService.getParents(genre);
        model.addAttribute("bookList", bookList);
//        GenreEntity genre = genreService.getGenreEntityById(genreId);
        model.addAttribute("genre", genre);
        model.addAttribute("genreParents", parents);
        return "/genres/slug";
    }
}
