package com.example.bookshop.data.struct.book;

import com.example.bookshop.data.struct.Author;
import com.example.bookshop.data.struct.book.file.BookFile;
import com.example.bookshop.data.struct.book.review.BookReviewEntity;
import com.example.bookshop.data.struct.tag.TagEntity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@JsonPropertyOrder({"id", "slug", "image", "authors", "title", "discount", "isBestseller",
                    "rating", "status", "price", "discountPrice"})
@Getter
@Setter
@ToString(of = {"title", "price"})
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "text")
    @JsonIgnore
    private String description;


    @Column(columnDefinition = "smallint")
    private Integer discount;

    private String image;

    @Column(columnDefinition = "smallint")
    private Integer isBestseller;

    private Integer price;

    @Column(columnDefinition = "date")
    @JsonIgnore
    private Date pubDate;

    private String slug;
    private String title;

    @ManyToMany
    @JoinTable(name = "book2author",
               joinColumns = @JoinColumn(name = "book_id"),
               inverseJoinColumns = @JoinColumn(name = "author_id"))
    @OrderColumn(name = "sort_index")
    private List<Author> authors;

    @ManyToMany
    @JoinTable(name = "book2tag",
               joinColumns = @JoinColumn(name = "book_id"),
               inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @JsonIgnore
    @OrderBy(value = "name")
    private List<TagEntity> tags;

    @ManyToMany
    @JoinTable(name = "book2genre",
               joinColumns = @JoinColumn(name = "book_id"),
               inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @JsonIgnore
    private Set<TagEntity> genres;

    @OneToMany
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private List<BookFile> bookFiles;

    @OneToMany
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Set<BookRateEntity> bookRateEntities;

    @OneToMany
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private List<BookReviewEntity> bookReviewEntities;

    @JsonGetter("authors")
    public String getJsonAuthors() {
        // нужно для корректного вывода на фронт записи об авторе/авторах, используется в thymeleaf-шаблоне
        Iterator<Author> authorIterator = authors.iterator();
        Author author= authorIterator.next();
        return author.getName() + (authorIterator.hasNext() ? " and others" : " personally");
    }

    @JsonGetter("rating")
    public int getRating() {
        return (int) Math.round(bookRateEntities.stream().collect(Collectors.averagingInt(BookRateEntity::getRate)));
    }

    @JsonIgnore
    public int getRatingTotalCount() {
        return bookRateEntities.size();
    }

    public int getNumberOfRating(int rate) {
        return (int) bookRateEntities.stream().filter(r -> r.getRate() == rate).count();
    }

    @JsonGetter("discountPrice")
    public Integer getDiscountPrice() {
        return discount > 0 ? price * (100 - discount) / 100 : 0;
    }

}
