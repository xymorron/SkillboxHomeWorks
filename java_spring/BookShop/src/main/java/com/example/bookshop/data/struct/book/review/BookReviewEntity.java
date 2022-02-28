package com.example.bookshop.data.struct.book.review;

import com.example.bookshop.data.struct.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "book_review")
public class BookReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_id", columnDefinition = "INT NOT NULL")
    private int bookId;

    @ManyToOne
    @JoinColumn(name = "user_id", columnDefinition = "INT NOT NULL")
    private UserEntity user;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String text;

    @OneToMany
    @JoinColumn(name = "review_id")
    private Set<BookReviewLikeEntity> likeEntities;

    public long getLikesCount() {
        return likeEntities.stream().filter(bookReviewLikeEntity -> bookReviewLikeEntity.getValue() > 0).count();
    }

    public long getDislikesCount() {
        return likeEntities.stream().filter(bookReviewLikeEntity -> bookReviewLikeEntity.getValue() < 0).count();
    }

    public float getStarRating() {
        long likes = getLikesCount();
        long dislikes = getDislikesCount();
        if (likeEntities.isEmpty()) return 0;
        if (dislikes == 0) return 1;
        return (float) likes / (likes + dislikes);
    }

    public long getTotalRate() {
        return likeEntities.stream().mapToInt(BookReviewLikeEntity::getValue).sum();
    }


}
