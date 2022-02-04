package com.example.bookshop.data.struct.service;

import com.example.bookshop.data.struct.book.links.Book2UserTypeEntity;
import com.example.bookshop.data.struct.repository.Book2UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Book2UserTypeService {

    private final Book2UserTypeRepository book2UserTypeRepository;

    @Autowired
    public Book2UserTypeService(Book2UserTypeRepository book2UserTypeRepository) {
        this.book2UserTypeRepository = book2UserTypeRepository;
    }

    public Book2UserTypeEntity getBook2UserTypeEntityByCode(String code) {
        return book2UserTypeRepository.getBook2UserTypeEntityByCode(code);
    }
}
