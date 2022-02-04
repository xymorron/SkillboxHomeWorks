package org.example.web.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Book {
    private Integer id;
    @NotBlank
    private String author;
    @NotBlank
    private String title;
    @Digits(integer =  4, fraction = 0)
    @Min(1)
    @NotNull
    private Integer size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return (author.isEmpty() && title.isEmpty() && size == null);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                '}';
    }
}
