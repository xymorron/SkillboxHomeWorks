package com.example.bookshop.data.struct.repository;

import com.example.bookshop.data.struct.book.file.BookFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookFileRepository extends JpaRepository<BookFile, Integer> {

    public BookFile getBookFileByHash(String hash);
}
