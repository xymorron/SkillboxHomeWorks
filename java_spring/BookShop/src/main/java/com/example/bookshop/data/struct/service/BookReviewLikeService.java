package com.example.bookshop.data.struct.service;

import com.example.bookshop.data.struct.book.review.BookReviewLikeEntity;
import com.example.bookshop.data.struct.repository.BookReviewLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookReviewLikeService {

    BookReviewLikeRepository bookReviewLikeRepository;

    @Autowired
    public BookReviewLikeService(BookReviewLikeRepository bookReviewLikeRepository) {
        this.bookReviewLikeRepository = bookReviewLikeRepository;
    }

    public BookReviewLikeEntity getBookReviewLikeEntityByReviewIdAndUserId(int reviewId, int userId) {
        return bookReviewLikeRepository.getBookReviewLikeEntityByReviewIdAndUserId(reviewId, userId);
    }

    public void addReviewLike(int userId, int reviewId, short value) {
        BookReviewLikeEntity bookReviewLike = getBookReviewLikeEntityByReviewIdAndUserId(reviewId, userId);
        if (bookReviewLike == null) {
            bookReviewLike = new BookReviewLikeEntity();
            bookReviewLike.setReviewId(reviewId);
            bookReviewLike.setUserId(userId);
        }
        bookReviewLike.setTime(LocalDateTime.now());
        bookReviewLike.setValue(value);
        bookReviewLikeRepository.save(bookReviewLike);
    }
}
