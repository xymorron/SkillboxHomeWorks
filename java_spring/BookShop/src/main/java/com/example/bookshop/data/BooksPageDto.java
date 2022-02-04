package com.example.bookshop.data;

import com.example.bookshop.data.struct.book.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BooksPageDto {

    private Integer count;
    private List<Book> books;

    public BooksPageDto(List<Book> books) {
        count = books.size();
        this.books = books;
    }

}
