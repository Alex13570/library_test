package ru.zakhrey.library_test.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zakhrey.library_test.dto.book.AddBookRequest;
import ru.zakhrey.library_test.dto.book.AddBookResponse;
import ru.zakhrey.library_test.dto.task.BookDto;
import ru.zakhrey.library_test.entity.BookEntity;
import ru.zakhrey.library_test.mapper.BookMapper;
import ru.zakhrey.library_test.repository.BookRepository;
import ru.zakhrey.library_test.service.BookService;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Override
    public AddBookResponse addBook(AddBookRequest addBookRequest) {
        BookEntity entity = bookMapper.map(addBookRequest);
        bookRepository.save(entity);
        return bookMapper.map(entity);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookMapper.map(bookRepository.findAll());
    }

    @Override
    public List<BookEntity> getAllBooksByNameAndAuthor(String name, String author) {
        return bookRepository.findAllByNameAndAuthor(name, author);
    }

    @Override
    public List<BookEntity> getAllFreeBooksByNameAndAuthor(String name, String author) {
        return bookRepository.findAllByNameAndAuthorAndIssueDate(name, author, null);
    }

    @Override
    public List<BookEntity> getAllExpiredBooks(LocalDateTime dateTime) {
        return bookRepository.findAllByIssueDateLessThan(dateTime);
    }

    @Override
    public void updateBookById(BookEntity book) {
        bookRepository.save(book);
    }

}
