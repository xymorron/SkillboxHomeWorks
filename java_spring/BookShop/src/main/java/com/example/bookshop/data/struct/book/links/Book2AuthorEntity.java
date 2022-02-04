package com.example.bookshop.data.struct.book.links;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "book2author")
public class Book2AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_id", columnDefinition = "INT NOT NULL")
    private int bookId;

    @Column(name = "author_id", columnDefinition = "INT NOT NULL")
    private int authorId;

    @Column(name = "sort_index", columnDefinition = "INT NOT NULL  DEFAULT 0")
    private int sortIndex;

}
