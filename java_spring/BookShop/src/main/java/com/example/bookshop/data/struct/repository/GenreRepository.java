package com.example.bookshop.data.struct.repository;

import com.example.bookshop.data.struct.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
    GenreEntity getGenreEntityById(Integer id);

    @Query(value = "SELECT COUNT(*) FROM book2genre WHERE genre_id = :id", nativeQuery = true)
    int countById(int id);

    GenreEntity getGenreEntityBySlug(String slug);

}
