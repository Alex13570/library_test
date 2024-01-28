package ru.zakhrey.library_test.service;

import ru.zakhrey.library_test.dto.book.AddBookRequest;
import ru.zakhrey.library_test.dto.book.AddBookResponse;
import ru.zakhrey.library_test.dto.task.BookDto;
import ru.zakhrey.library_test.entity.BookEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface BookService {

    AddBookResponse addBook(AddBookRequest addBookRequest);
    List<BookDto> getAllBooks();
    List<BookEntity> getAllBooksByNameAndAuthor(String name, String author);
    List<BookEntity> getAllFreeBooksByNameAndAuthor(String name, String author);
    List<BookEntity> getAllExpiredBooks(LocalDateTime dateTime);
    void updateBookById(BookEntity book);
//    DeleteBookResponse deleteBook(DeleteBookRequest deleteBookRequest);

}
