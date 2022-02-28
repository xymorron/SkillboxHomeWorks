package com.example.bookshop.data.struct.repository;

import com.example.bookshop.data.struct.book.review.BookReviewEntity;
import com.example.bookshop.data.struct.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewRepository extends JpaRepository<BookReviewEntity, Integer> {
    BookReviewEntity getBookReviewEntityByBookIdAndUser(int bookId, UserEntity user);
}
