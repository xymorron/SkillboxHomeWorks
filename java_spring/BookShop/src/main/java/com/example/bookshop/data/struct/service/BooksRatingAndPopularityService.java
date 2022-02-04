package com.example.bookshop.data.struct.service;

import com.example.bookshop.data.struct.book.BookRateEntity;
import com.example.bookshop.data.struct.repository.BookRateRepository;
import com.example.bookshop.data.struct.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class BooksRatingAndPopularityService {
    private final BookRepository bookRepository;
    private final BookRateRepository bookRateRepository;
    private final BookService bookService;
    private final Logger logger = Logger.getLogger(BooksRatingAndPopularityService.class.getName());

    @Autowired
    public BooksRatingAndPopularityService(BookRepository bookRepository, BookRateRepository bookRateRepository,
                                           BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookRateRepository = bookRateRepository;
        this.bookService = bookService;
        updateBookPopularity();
    }

    public void updateBookPopularity() {
        logger.info("executing updateBookPopularity task");
        bookRepository.updateBookPopularity();
    }

    public void rateBook(int userId, String bookSlug, int rate) {
        int bookId = bookService.getBookBySlug(bookSlug).getId();
        BookRateEntity bookRateEntity = bookRateRepository.getBookRateEntityByUserIdAndBookId(userId, bookId);
        if (bookRateEntity == null) {
            bookRateEntity = new BookRateEntity();
            bookRateEntity.setUserId(userId);
            bookRateEntity.setBookId(bookId);
        }
        bookRateEntity.setRate(rate);
        bookRateRepository.save(bookRateEntity);
    }
}
