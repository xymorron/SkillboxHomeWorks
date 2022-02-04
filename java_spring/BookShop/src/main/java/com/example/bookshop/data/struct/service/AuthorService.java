package com.example.bookshop.data.struct.service;

import com.example.bookshop.data.struct.Author;
import com.example.bookshop.data.struct.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Map<String, List<Author>> getAuthorsMap() {
        List<Author> authors= authorRepository.findAll();
        return authors.stream().collect(Collectors.groupingBy((Author a) -> {return  a.getName().substring(0,1);}));
    }

    public Author getAuthorBySlug(String slug) {
        return authorRepository.getAuthorBySlug(slug);
    }

    public Author getAuthorById(Integer authorId) {
        return authorRepository.getAuthorById(authorId);
    }
}
