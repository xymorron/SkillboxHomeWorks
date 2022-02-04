package com.example.bookshop.data.struct.book.review;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "book_review_like")
public class BookReviewLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "review_id", columnDefinition = "INT NOT NULL")
    private int reviewId;

    @Column(name = "user_id", columnDefinition = "INT NOT NULL")
    private int userId;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "SMALLINT NOT NULL")
    private short value;

}
