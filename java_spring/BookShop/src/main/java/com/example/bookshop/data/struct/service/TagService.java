package com.example.bookshop.data.struct.service;

import com.example.bookshop.data.struct.repository.TagRepository;
import com.example.bookshop.data.struct.tag.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private  final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagEntity getTagEntityById(Integer id) {
        return tagRepository.getTagEntityById(id);
    }

    public List<TagEntity> getTags() {
        return tagRepository.findAllOrderByBooksCount();
    }
}
