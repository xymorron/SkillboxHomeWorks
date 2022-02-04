package com.example.bookshop.data.struct.repository;

import com.example.bookshop.data.struct.tag.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity, Integer> {
    TagEntity getTagEntityById(Integer id);

    @Query(value = "SELECT tag.id, name, COUNT(*) AS tag_links_count\n" +
                   "FROM tag\n" +
                   "         JOIN book2tag b2t ON tag.id = b2t.tag_id\n" +
                   "GROUP BY tag.id, name\n" +
                   "ORDER BY tag_links_count DESC",
            nativeQuery = true)
    List<TagEntity> findAllOrderByBooksCount();
}
