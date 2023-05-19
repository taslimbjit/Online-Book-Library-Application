package com.taslim.onlinelibrary.service.impl;

import com.taslim.onlinelibrary.entity.BookEntity;
import com.taslim.onlinelibrary.exception.BookNameAuthorNameAlreadyExistsExcepion;
import com.taslim.onlinelibrary.exception.NoBooksFoundException;
import com.taslim.onlinelibrary.model.BookRequestModel;
import com.taslim.onlinelibrary.repository.BookRepository;
import com.taslim.onlinelibrary.service.BookService;
import com.taslim.onlinelibrary.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final JwtService jwtService;


    @Override
    public ResponseEntity<Object> createBook(BookRequestModel bookRequestModel) {

        BookEntity alreadyExist = bookRepository.findByAuthorNameAndBookName(bookRequestModel.getAuthorName(), bookRequestModel.getBookName());
        if (alreadyExist != null) {
            throw new BookNameAuthorNameAlreadyExistsExcepion("Author and Book Name Already Exist.");
        }


        BookEntity bookEntity = BookEntity.builder()
                .authorName(bookRequestModel.getAuthorName())
                .bookName(bookRequestModel.getBookName())
                .price(bookRequestModel.getPrice())
                .domain(bookRequestModel.getDomain())
                .build();
        BookEntity savedBook = bookRepository.save(bookEntity);

        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);

    }

    @Override
    public List<BookEntity> getAllBooks() {


        List<BookEntity> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new NoBooksFoundException("There is no books. Add New Books");

        }
        return books;
    }

    @Override
    public Optional<BookEntity> getBook(Long bookId) {
        Optional<BookEntity> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new NoBooksFoundException("ID Does Not Exist.");

        }
        return bookRepository.findById(bookId);
    }

    @Override
    public List<BookEntity> getBookByAuthorName(String authorName) {
        List<BookEntity> books = bookRepository.findByAuthorNameContaining(authorName);
        if (books.isEmpty()) {
            throw new NoBooksFoundException("Author Name ID Does Not Exist.");

        }
        return books;
    }

    @Override
    public BookEntity updateBook(Long bookId, BookRequestModel bookRequestModel) {
        Optional<BookEntity> userExistedAlready = bookRepository.findById(bookId);
        if (userExistedAlready.isEmpty()) {
            throw new NoBooksFoundException("ID Does Not Exist.");

        } else {
            BookEntity bookEntity = BookEntity.builder()
                    .id(bookId)
                    .authorName(bookRequestModel.getAuthorName())
                    .bookName(bookRequestModel.getBookName())
                    .price(bookRequestModel.getPrice())
                    .domain(bookRequestModel.getDomain())
                    .build();

            return bookRepository.save(bookEntity);

        }
    }
    @Override
    public void deleteBook(Long bookId) {
        Optional<BookEntity> userExistedAlready = bookRepository.findById(bookId);
        if (userExistedAlready.isEmpty()) {
            throw new NoBooksFoundException("ID Does Not Exist.");

        }
        bookRepository.deleteById(bookId);
    }

    @Override
    public BookEntity findByAuthorNameAndBookName(String authorName, String bookName) {
        BookEntity book = bookRepository.findByAuthorNameAndBookName(authorName, bookName);
        if (book == null) {
            throw new NoBooksFoundException("AuthorName and BookName Does Not Exist.");
        }
        return book;
    }
}
