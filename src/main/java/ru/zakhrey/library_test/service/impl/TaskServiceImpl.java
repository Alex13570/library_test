package ru.zakhrey.library_test.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.zakhrey.library_test.dto.task.*;
import ru.zakhrey.library_test.entity.BookEntity;
import ru.zakhrey.library_test.entity.UserEntity;
import ru.zakhrey.library_test.exception.TestTaskException;
import ru.zakhrey.library_test.mapper.TaskMapper;
import ru.zakhrey.library_test.service.BookService;
import ru.zakhrey.library_test.service.TaskService;
import ru.zakhrey.library_test.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    
    private final BookService bookService;
    private final UserService userService;
    private final TaskMapper taskMapper;

    @Override
    public TakeBookResponse takeBook(TakeBookRequest takeBookRequest) throws TestTaskException {
        UserEntity user = userService.findUser(takeBookRequest.getTicketNumber());
        if (Objects.isNull(user)) {
            throw new TestTaskException("There is no such ticket, please, sign up", HttpStatus.BAD_REQUEST);
        }

        List<BookEntity> books = bookService.getAllBooksByNameAndAuthor(takeBookRequest.getBookName(), takeBookRequest.getAuthor());
        if (books.isEmpty()) {
            throw new TestTaskException("There is no such book in our library", HttpStatus.BAD_REQUEST);
        }

        BookEntity book = books
                .stream()
                .filter(el -> Objects.isNull(el.getIssueDate()))
                .findFirst()
                .orElseThrow(() -> new TestTaskException("There is no free book from your order", HttpStatus.BAD_REQUEST));

        book.setIssueDate(LocalDateTime.now());
        bookService.updateBookById(book);

        user.getTakenBooks().add(book);
        userService.updateUserById(user);

        return taskMapper.mapUserToTakeBookResponse(user);
    }

    @Override
    public GetAllBooksResponse getAllBooks() {
        return new GetAllBooksResponse(bookService.getAllBooks());
    }

    @Override
    public RefundBookResponse refundBook(RefundBookRequest refundBookRequest) throws TestTaskException {
        UserEntity user = userService.findUser(refundBookRequest.getTicketNumber());
        if (Objects.isNull(user)) {
            throw new TestTaskException("There is no such ticket, please, sign up", HttpStatus.BAD_REQUEST);
        }

        BookEntity takenBook = user.getTakenBooks().stream()
                .filter(el -> refundBookRequest.getBookName().equals(el.getName())
                    && refundBookRequest.getAuthor().equals(el.getAuthor())
                ).findFirst().orElseThrow(() -> new TestTaskException("You did not took this book", HttpStatus.BAD_REQUEST));


        user.getTakenBooks().remove(takenBook);
        takenBook.setIssueDate(null);

        userService.updateUserById(user);

        return taskMapper.mapUserToRefundBookResponse(user);
    }

    @Override
    public ShowOwnerResponse showOwner(ShowOwnerRequest showOwnerRequest) {
        List<UserEntity> users = userService.findAllUsersHwoTakenTheBook(showOwnerRequest.getBookName());
        return taskMapper.mapUserToShowOwnerResponse(users);
    }
}
