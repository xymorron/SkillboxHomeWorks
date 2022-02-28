package com.example.bookshop.data.struct.service;

import com.example.bookshop.data.struct.book.review.BookReviewEntity;
import com.example.bookshop.data.struct.repository.BookReviewRepository;
import com.example.bookshop.data.struct.user.UserEntity;
import com.example.bookshop.errs.BookstoreApiWrongParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class BookReviewService {

    private final BookReviewRepository bookReviewRepository;

    @Autowired
    public BookReviewService(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    public BookReviewEntity getBookReviewEntityByBookIdAndUserId(int bookId, UserEntity user) {
        return bookReviewRepository.getBookReviewEntityByBookIdAndUser(bookId, user);
    }

    public void addBookReview(int bookId, UserEntity user, String reviewText) throws BookstoreApiWrongParameterException {
        BookReviewEntity bookReviewEntity = getBookReviewEntityByBookIdAndUserId(bookId, user);
        if (reviewText.length() < 10) {
            throw new BookstoreApiWrongParameterException("Отзыв слишком короткий. Напишите, пожалуйста, более развёрнутый отзыв");
        }
        if (bookReviewEntity == null) {
            bookReviewEntity = new BookReviewEntity();
            bookReviewEntity.setBookId(bookId);
            bookReviewEntity.setUser(user);
        }
        bookReviewEntity.setText(reviewText);
        bookReviewEntity.setTime(LocalDateTime.now());
        bookReviewRepository.save(bookReviewEntity);
    }
}
