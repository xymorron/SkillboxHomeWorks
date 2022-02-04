package com.example.bookshop.data.struct.repository;

import com.example.bookshop.data.struct.book.links.Book2UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Book2UserRepository extends JpaRepository<Book2UserEntity, Integer> {
    Book2UserEntity getBook2UserEntityByUserIdAndBookId(int userId, int bookId);
}
