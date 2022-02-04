package com.example.bookshop.data.struct.repository;

import com.example.bookshop.data.struct.book.BookRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRateRepository extends JpaRepository<BookRateEntity, Integer> {
    BookRateEntity getBookRateEntityByUserIdAndBookId(Integer userId, Integer bookId);
}
