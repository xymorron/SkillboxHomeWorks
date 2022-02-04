package com.example.bookshop.controllers;

import com.example.bookshop.data.ApiResponse;
import com.example.bookshop.data.ApiResponseStatus;
import com.example.bookshop.data.struct.book.Book;
import com.example.bookshop.data.struct.service.AuthorService;
import com.example.bookshop.data.struct.service.Book2UserService;
import com.example.bookshop.data.struct.service.BookService;
import com.example.bookshop.data.BooksPageDto;
import com.example.bookshop.data.struct.Author;
import com.example.bookshop.data.struct.service.BooksRatingAndPopularityService;
import com.example.bookshop.errs.BookstoreApiWrongParameterException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

@RestController
@Api(description = "authors description")
public class BooksRestApiController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final Book2UserService book2UserService;
    private final BooksRatingAndPopularityService booksRatingAndPopularityService;

    private final Logger logger = Logger.getLogger(BooksRestApiController.class.getName());


    @Autowired
    public BooksRestApiController(AuthorService authorService, BookService bookService,
                                  Book2UserService book2UserService, BooksRatingAndPopularityService booksRatingAndPopularityService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.book2UserService = book2UserService;
        this.booksRatingAndPopularityService = booksRatingAndPopularityService;
    }

    @ApiOperation("method to get map of authors")
    @GetMapping("api/authors")
    public Map<String, List<Author>> authors() {
        return authorService.getAuthorsMap();
    }

    @GetMapping("/api/books/recommended")
    public BooksPageDto getRecommendedBooks(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    @GetMapping("/api/books/recent")
    public BooksPageDto getRecentBooks(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit,
                                       @RequestParam(name = "from", required = false) String from,
                                       @RequestParam(name = "to", required = false) String to) {
        DateFormat dateFormat = new SimpleDateFormat("d.M.yyyy");
        Date fromDate;
        Date toDate;
        try {
            fromDate = dateFormat.parse(from);
        } catch (Exception e) {
            logger.info("fromDate parse failed. Default date applied");
            fromDate = bookService.getOldestPubDate();
        }
        try {
            toDate = dateFormat.parse(to);
        } catch (Exception e) {
            logger.info("toDate parse failed. Current date applied");
            toDate = new Date();
        }
        logger.info("fromString = " + from);
        logger.info("toString = " + to);
        logger.info("fromDate = " + dateFormat.format(fromDate));
        logger.info("toDate = " + dateFormat.format(toDate));

        return new BooksPageDto(bookService.getPageOfRecentBooksByDate(fromDate, toDate, offset, limit));
    }

    @GetMapping("/api/books/popular")
    public BooksPageDto getPopularBooks(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPageOfPopularBooks(offset, limit));
    }

    @GetMapping("/api/books/tag/{id}")
    public BooksPageDto getBooksByTag(@PathVariable(required = false, name = "id") Integer tagId,
                                      @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        logger.info("Query by tag received. TagId=" + tagId + ", offset=" + offset + ", limit=" + limit);
        return new BooksPageDto(bookService.getPageOfBooksByTag(tagId, offset, limit));
    }

//    @GetMapping("/api/books/by-title")
//    public ResponseEntity<List<Book>> booksByTitle(@RequestParam("title") String title) {
//        return ResponseEntity.ok(bookService.getBooksByTitle(title));
//    }

    @GetMapping("/api/books/genre/{id}")
    public BooksPageDto getBooksByGenre(@PathVariable(required = false, name = "id") Integer genreId,
                                        @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        logger.info("Query by genre received. genreId=" + genreId + ", offset=" + offset + ", limit=" + limit);
        return new BooksPageDto(bookService.getPageOfBooksByGenre(genreId, offset, limit));
    }

    @GetMapping("/api/books/author/{id}")
    public BooksPageDto getBooksByAuthor(@PathVariable(required = false, name = "id") Integer authorId,
                                         @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        Author author = authorService.getAuthorById(authorId);
        logger.info("Query by author received. AuthorId=" + authorId + ", offset=" + offset + ", limit=" + limit);
        return new BooksPageDto(bookService.getPageOfBooksByAuthor(author, offset, limit));
    }

    @PostMapping("/api/changeBookStatus")
    public ResponseEntity<ApiResponseStatus> handlerChangeBookStatus(
            @RequestParam(name = "status") String status,
            @RequestParam(name = "booksIds") String[] booksIds) throws BookstoreApiWrongParameterException {
        int userId = 47;
        for (String bookSlug : booksIds) {
            book2UserService.changeBookStatusForUser(userId, bookSlug, status);
        }
        ApiResponseStatus responseStatus = new ApiResponseStatus();
        responseStatus.setResult(true);
        return ResponseEntity.ok((responseStatus));
    }

    @PostMapping("/api/rateBook")
    public ResponseEntity<ApiResponseStatus> handlerRateBook(@RequestParam(name = "bookId") String bookSlug,
                                @RequestParam(name = "value") int rate) {
        int userId = 47;
        logger.info("rated book " + bookSlug + " with rate " + rate);
        booksRatingAndPopularityService.rateBook(userId, bookSlug, rate);
        ApiResponseStatus responseStatus = new ApiResponseStatus();
        responseStatus.setResult(true);
        return ResponseEntity.ok((responseStatus));
    }

    @PostMapping("/api/rateBookReview")
    public ResponseEntity<ApiResponseStatus> handlerRateBookReview(@RequestParam(name = "reviewId") int reviewId,
                                                                   @RequestParam(name = "value") int rate) {
        logger.info("rated bookReview " + reviewId + " with rate " + rate);
        ApiResponseStatus responseStatus = new ApiResponseStatus();
        responseStatus.setResult(true);
        return ResponseEntity.ok(responseStatus);
    }

    @PostMapping("/api/bookReview")
    public ResponseEntity<ApiResponseStatus> handlerBookReview(@RequestParam(name = "bookId") String bookSlug,
                                                                   @RequestParam(name = "text") String text) {
        logger.info("bookReview " + bookSlug + " with text " + text);
        ApiResponseStatus responseStatus = new ApiResponseStatus();
        if (text.length() > 10) {
            responseStatus.setResult(true);
        } else {
            responseStatus.setResult(false);
            responseStatus.setError("Отзыв слишком короткий. Напишите, пожалуйста, более развёрнутый отзыв");
        }
        return ResponseEntity.ok(responseStatus);
    }


    @GetMapping("/api/books/by-title")
    public ResponseEntity<ApiResponse<Book>> booksByTitle(@RequestParam("title") String title) throws BookstoreApiWrongParameterException {
        logger.info("Query by title received. Title = " + title);
        ApiResponse<Book> response = new ApiResponse<>();
        List<Book> data = bookService.getBooksByTitle(title);
        response.setDebugMessage("successful request");
        response.setMessage("data size: " + data.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimeStamp(LocalDateTime.now());
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponseStatus> handlerMissingServletRequestParameterException(Exception exception) {
        return new ResponseEntity<>(
                new ApiResponseStatus(false,"Missing required parameters", exception),
//                new ApiResponse<>(HttpStatus.BAD_REQUEST, "Missing required parameters", exception),
                HttpStatus.OK);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiResponseStatus> handlerNumberFormatException(Exception exception) {
        return new ResponseEntity<>(
                new ApiResponseStatus(false,"Wrong required parameter type", exception),
//                new ApiResponse<>(HttpStatus.BAD_REQUEST, "Missing required parameters", exception),
                HttpStatus.OK);
    }

    @ExceptionHandler(BookstoreApiWrongParameterException.class)
    public ResponseEntity<ApiResponseStatus> handlerBookstoreApiWrongParameterException(Exception exception) {
        return new ResponseEntity<>(
                new ApiResponseStatus(false,"Bad parameter value...", exception.getCause()),
//                new ApiResponse<>(HttpStatus.BAD_REQUEST, "Bad parameter value...", exception.getCause()),
                HttpStatus.OK);
    }

}
