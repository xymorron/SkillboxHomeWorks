package com.example.bookshop.data.struct.service;

import com.example.bookshop.data.struct.Author;
import com.example.bookshop.data.struct.book.Book;
import com.example.bookshop.data.struct.repository.Book2UserTypeRepository;
import com.example.bookshop.data.struct.repository.BookRepository;
import com.example.bookshop.errs.BookstoreApiWrongParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final Book2UserTypeRepository book2UserTypeRepository;

    @Autowired
    public BookService(BookRepository bookRepository, Book2UserTypeRepository book2UserTypeRepository) {
        this.bookRepository = bookRepository;
        this.book2UserTypeRepository = book2UserTypeRepository;
    }

    public List<Book> getBooks() {
       return bookRepository.findAll();
    }

    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public List<Book> getPageOfRecentBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getRecentBooks(nextPage);
    }

    public List<Book> getPageOfRecentBooksByDate(Date from, Date to, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getBooksByPubDateBetweenOrderByPubDateDesc(from, to, nextPage);
    }


    public List<Book> getPageOfPopularBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getPopularBooks(nextPage);
    }

    public Date getOldestPubDate() {
        return bookRepository.getOldestPubDate();
    }

    public List<Book> getPageOfBooksByTag(Integer tagId, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getBooksByTag(tagId, nextPage);
    }

    public List<Book> getPageOfBooksByGenre(Integer genreId, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getBooksByGenre(genreId, nextPage);
    }

    public Long countBooksByAuthorsContains(Author author) {
        return bookRepository.countBooksByAuthorsContains(author);
    }

    public List<Book> getPageOfBooksByAuthor(Author author, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getBooksByAuthorsContainsOrderByPubDateDesc(author, nextPage);
    }

    public Book getBookBySlug(String slug) {
        return bookRepository.getBookBySlug(slug);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getBooksByTitle(String title) throws BookstoreApiWrongParameterException {
        if (title.length() <= 1) {
            throw new BookstoreApiWrongParameterException("Wrong values passed to one or more parameters");
        } else {
            List<Book> data = bookRepository.getBooksByTitleContainingOrderByPubDateDesc(title);
            if (data.size() > 0) {
                return data;
            } else {
                throw new BookstoreApiWrongParameterException("No data found with specified parameters...");
            }
        }
    }

    private List<Book> getBooksByUserByType(int userId, int typeId) {
        return bookRepository.getBooksByUserByType(userId, typeId);
    }

    public List<Book> getKeptBooksByUser(int userId) {
        return getBooksByUserByType(userId, 1);
    }

    public List<Book> getCartBooksByUser(int userId) {
        return getBooksByUserByType(userId, 2);
    }

    public List<Book> getPaidBooksByUser(int userId) {
        return getBooksByUserByType(userId, 3);
    }

    public List<Book> getArchivedBooksByUser(int userId) {
        return getBooksByUserByType(userId, 4);
    }

    public List<Book> findBooksBySlugs(String[] slugs) {
        return bookRepository.getBooksBySlugIn(slugs);
    }


}
