package com.example.bookshop.data.struct.other;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "faq")
public class FaqEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "INT NOT NULL  DEFAULT 0")
    private int sortIndex;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String question;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String answer;

}
