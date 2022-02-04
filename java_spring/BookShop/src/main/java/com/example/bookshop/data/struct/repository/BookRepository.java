package com.example.bookshop.data.struct.repository;

import com.example.bookshop.data.struct.Author;
import com.example.bookshop.data.struct.book.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {



    List<Book> findBooksByTitleContaining(String bookTitle);

    @Query(value = "SELECT MIN(pub_date) FROM book", nativeQuery = true)
    Date getOldestPubDate();

    List<Book> getBooksByPubDateBetweenOrderByPubDateDesc(Date min, Date max, Pageable pageable);


    @Query(value = "SELECT * FROM book ORDER BY pub_date DESC", nativeQuery = true)
    List<Book> getRecentBooks(Pageable pageable);

    @Query(value = "SELECT * FROM book ORDER BY popularity DESC", nativeQuery = true)
    List<Book> getPopularBooks(Pageable pageable);

    List<Book> findBooksByPriceBetween(Integer min, Integer max);

    List<Book> findBooksByPrice(Integer price);


    @Query("from Book where isBestseller > 80 order by pubDate desc")
    List<Book> getBestSellers();

    @Query(value = "SELECT * FROM book WHERE discount = (SELECT MAX(discount) FROM book) ORDER BY pub_date DESC",
            nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

    @Transactional
    @Modifying
    @Query(value = "UPDATE\n" +
            "    book\n" +
            "SET\n" +
            "    popularity = (SELECT coalesce( sum(popularity_by_type), 0)\n" +
            "     FROM\n" +
            "         (SELECT CASE type_id\n" +
            "                     WHEN 1 THEN 0.4 * count(*)\n" +
            "                     WHEN 2 THEN 0.7 * count(*)\n" +
            "                     WHEN 3 THEN count(*)\n" +
            "                     END AS popularity_by_type\n" +
            "         FROM book2user\n" +
            "         WHERE book_id = book.id\n" +
            "         GROUP BY type_id) AS popular_data_by_type)" +
            "WHERE true", nativeQuery = true)
    void updateBookPopularity();

    @Transactional
    @Modifying
    @Query(value = "UPDATE\n" +
            "    book\n" +
            "SET\n" +
            "    popularity =  coalesce(total_popularity, 0)\n" +
            "FROM\n" +
            "    (SELECT  book_id,\n" +
            "             sum(popularity_by_type) total_popularity\n" +
            "     FROM\n" +
            "         (SELECT book_id,\n" +
            "                 CASE type_id\n" +
            "                     WHEN 1 THEN 0.4 * count(*)\n" +
            "                     WHEN 2 THEN 0.7 * count(*)\n" +
            "                     WHEN 3 THEN count(*)\n" +
            "                     END AS popularity_by_type\n" +
            "          FROM book2user GROUP BY book_id, type_id) popular_data_by_type\n" +
            "     GROUP BY book_id) AS popular_data\n" +
            "WHERE book.id = book_id",
            nativeQuery = true)
    void updateBookPopularityAlter();

    @Query(value = "SELECT * FROM book WHERE id IN (SELECT book_id FROM book2tag WHERE tag_id = ?1 ORDER BY pub_date DESC )",
           nativeQuery = true)
    List<Book> getBooksByTag(Integer tagId, Pageable nextPage);

    @Query(value = "SELECT * FROM book WHERE id IN (SELECT book_id FROM book2genre WHERE genre_id = ?1 ORDER BY pub_date DESC )",
           nativeQuery = true)
    List<Book> getBooksByGenre(Integer genreId, Pageable nextPage);

    Long countBooksByAuthorsContains(Author author);

    List<Book> getBooksByAuthorsContainsOrderByPubDateDesc(Author author, Pageable nextPage);

    Book getBookBySlug(String slug);

    List<Book> getBooksByTitleContainingOrderByPubDateDesc(String title);

    List<Book> getBooksBySlugIn(String[] slugs);


    @Query(value = "SELECT *\n" +
                   "FROM book\n" +
                   "WHERE id IN (SELECT book_id FROM book2user WHERE user_id = ?1 AND type_id = ?2) ORDER BY pub_date DESC",
           nativeQuery = true)
    List<Book> getBooksByUserByType(int userId, int typeId);

}
