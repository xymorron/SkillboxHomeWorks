package com.example.bookshop.data.struct.book;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "book_rate")
@Getter
@Setter
public class BookRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "user_id")
    private Integer userId;

    private Integer rate;
}
