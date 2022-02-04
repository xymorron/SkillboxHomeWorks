package com.example.bookshop.data.struct.repository;

import com.example.bookshop.data.struct.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Author getAuthorBySlug(String slug);

    Author getAuthorById(Integer authorId);

}
