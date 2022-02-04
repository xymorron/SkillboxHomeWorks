package com.example.bookshop.data.struct.service;

import com.example.bookshop.data.struct.book.links.Book2UserEntity;
import com.example.bookshop.data.struct.book.links.Book2UserTypeEntity;
import com.example.bookshop.data.struct.repository.Book2UserRepository;
import com.example.bookshop.errs.BookstoreApiWrongParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class Book2UserService {

    private final Book2UserRepository book2UserRepository;
    private final Book2UserTypeService book2UserTypeService;
    private final BooksRatingAndPopularityService booksRatingAndPopularityService;
    private final BookService bookService;
    private final Logger logger = Logger.getLogger(Book2UserService.class.getName());

    @Autowired
    public Book2UserService(Book2UserRepository book2UserRepository, BookService bookService,
                            Book2UserTypeService book2UserTypeService, BooksRatingAndPopularityService booksRatingAndPopularityService) {
        this.book2UserRepository = book2UserRepository;
        this.bookService = bookService;
        this.book2UserTypeService = book2UserTypeService;
        this.booksRatingAndPopularityService = booksRatingAndPopularityService;
    }

    public Book2UserEntity getBookStatusByUserIdAndBookId(int userId, int bookId) {
        return book2UserRepository.getBook2UserEntityByUserIdAndBookId(userId, bookId);
    }



    public void changeBookStatusForUser(int userId, String bookSlug, String newTypeCode) throws BookstoreApiWrongParameterException {
        int bookId = bookService.getBookBySlug((bookSlug)).getId();
        Book2UserEntity currentBookStatus = getBookStatusByUserIdAndBookId(userId, bookId);
        String currentTypeCode = currentBookStatus != null ? currentBookStatus.getType().getCode() : "UNLINK";
        Book2UserTypeEntity newStatusType;
        logger.info("trying change book status for bookId: " + bookId
                + ",  currentStatusType: " + currentTypeCode + ", newTypeCode: " + newTypeCode);
        switch (newTypeCode) {
//            Тут пойдут лютые повторения одинаковых выбрасываний исключений.
//            Это сделало с заделом на возможную кастомизацию передаваемых сообщений для каждого случая
            case "UNLINK":
                if (currentTypeCode.equals("CART") || currentTypeCode.equals("KEPT")) {
                    book2UserRepository.delete(currentBookStatus);
                    return;
                } else
                    throw new BookstoreApiWrongParameterException("Wrong status change " + currentTypeCode + " to " + newTypeCode);
            case "CART":
                if (!currentTypeCode.equals("UNLINK") && !currentTypeCode.equals("KEPT"))
                    throw new BookstoreApiWrongParameterException("Wrong status change " + currentTypeCode + " to " + newTypeCode);
                break;
            case "KEPT":
                if (!currentTypeCode.equals("UNLINK") && !currentTypeCode.equals("CART"))
                    throw new BookstoreApiWrongParameterException("Wrong status change " + currentTypeCode + " to " + newTypeCode);
                break;
            case "PAID":
                if (!currentTypeCode.equals("ARCHIVED"))
                    throw new BookstoreApiWrongParameterException("Wrong status change " + currentTypeCode + " to " + newTypeCode);
                break;
            case "ARCHIVED":
                if (!currentTypeCode.equals("PAID"))
                    throw new BookstoreApiWrongParameterException("Wrong status change " + currentTypeCode + " to " + newTypeCode);
                break;
            default:
                logger.info("wrong status: " + newTypeCode);
                throw new BookstoreApiWrongParameterException("Wrong book status: " + newTypeCode);

        }
        newStatusType = book2UserTypeService.getBook2UserTypeEntityByCode(newTypeCode);
        setBookStatusForUser(userId, bookId, newStatusType);
    }

    public void setBookStatusForUser(int userId, int bookId, Book2UserTypeEntity newType) {
        Book2UserEntity book2User = book2UserRepository.getBook2UserEntityByUserIdAndBookId(userId, bookId);
        if (book2User == null) {
            book2User = new Book2UserEntity();
            book2User.setBookId(bookId);
            book2User.setUserId(userId);
        }
        book2User.setTime(LocalDateTime.now());
        book2User.setType(newType);
        book2UserRepository.save(book2User);
        booksRatingAndPopularityService.updateBookPopularity();
    }

}
