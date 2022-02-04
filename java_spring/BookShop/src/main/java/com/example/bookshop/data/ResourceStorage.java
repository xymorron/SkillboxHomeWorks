package com.example.bookshop.data;

import com.example.bookshop.data.struct.book.file.BookFile;
import com.example.bookshop.data.struct.repository.BookFileRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@Service
public class ResourceStorage {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${download.path}")
    private String downloadPath;

    BookFileRepository bookFileRepository;

    @Autowired
    public ResourceStorage(BookFileRepository bookFileRepository) {
        this.bookFileRepository = bookFileRepository;
    }

    Logger logger = Logger.getLogger(ResourceStorage.class.getName());

    public String saveNewBookImage(MultipartFile file, String slug) throws IOException {

        String resourceURI = null;

        if (!file.isEmpty()) {
            if (!new File(uploadPath).exists()) {
                Files.createDirectories(Paths.get(uploadPath));
                logger.info("created image folder in " + uploadPath);
            }
            String fileName = slug + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            Path path = Paths.get(uploadPath, fileName);
            resourceURI = "/book-covers/" + fileName;
            file.transferTo(path);
            logger.info(fileName + " uploaded OK! with resourceURI = " + resourceURI);
        }
        return resourceURI;
    }

    public Path getBookFilePath(String hash) {
        BookFile bookFile = bookFileRepository.getBookFileByHash(hash);
        return Paths.get(bookFile.getPath());
    }

    public MediaType getBookFileMime(String hash) {
        BookFile bookFile = bookFileRepository.getBookFileByHash(hash);
        String mimeType = URLConnection.guessContentTypeFromName(Paths.get(bookFile.getPath()).getFileName().toString());
        if (mimeType != null) {
            return MediaType.parseMediaType(mimeType);
        } else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public byte[] getBookFileByteArray(String hash) throws IOException {
        BookFile bookFile = bookFileRepository.getBookFileByHash(hash);
        Path path = Paths.get(downloadPath, bookFile.getPath());
        return Files.readAllBytes(path);

    }
}
