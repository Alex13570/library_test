package ru.zakhrey.library_test.service;

import ru.zakhrey.library_test.dto.task.*;
import ru.zakhrey.library_test.exception.TestTaskException;


public interface TaskService {

    TakeBookResponse takeBook(TakeBookRequest takeBookRequest) throws TestTaskException;
    GetAllBooksResponse getAllBooks();
    RefundBookResponse refundBook(RefundBookRequest refundBookRequest) throws TestTaskException;
    ShowOwnerResponse showOwner(ShowOwnerRequest showOwnerRequest);

}
