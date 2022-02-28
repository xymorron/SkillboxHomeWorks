package com.example.bookshop.data.struct.repository;

import com.example.bookshop.data.struct.book.review.BookReviewLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewLikeRepository extends JpaRepository<BookReviewLikeEntity, Integer> {
    BookReviewLikeEntity getBookReviewLikeEntityByReviewIdAndUserId(int reviewId, int userId);
}
