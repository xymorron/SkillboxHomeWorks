package com.example.bookshop.data.struct.book.links;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "book2user")
public class Book2UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "type_id", columnDefinition = "INT NOT NULL")
    private Book2UserTypeEntity type;

    @Column(name = "book_id", columnDefinition = "INT NOT NULL")
    private int bookId;

    @Column(name = "user_id", columnDefinition = "INT NOT NULL")
    private int userId;

}
