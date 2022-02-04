package com.example.bookshop.data.struct.book.file;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "book_file")
public class BookFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hash;
    private String path;

    @ManyToOne
    @JoinColumn(name = "type_id")
    BookFileTypeEntity bookFileType;

}
