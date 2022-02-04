package com.example.bookshop.data.struct.repository;

import com.example.bookshop.data.struct.book.links.Book2UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface Book2UserTypeRepository extends JpaRepository<Book2UserTypeEntity, Integer> {
    Book2UserTypeEntity getBook2UserTypeEntityByCode(String code);
}
