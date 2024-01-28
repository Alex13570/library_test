package ru.zakhrey.library_test.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.zakhrey.library_test.dto.task.*;
import ru.zakhrey.library_test.exception.TestTaskException;
import ru.zakhrey.library_test.service.TaskService;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping(path = "/getBooks")
    @Operation(summary = "get all books")
    public GetAllBooksResponse addBook() {
        return taskService.getAllBooks();
    }

    @PostMapping(path = "/takeBook")
    @Operation(summary = "taking book from library")
    public TakeBookResponse takeBook(@RequestBody TakeBookRequest request) throws TestTaskException {
        return taskService.takeBook(request);
    }

    @PostMapping(path = "/refundBook")
    @Operation(summary = "refunding book to library")
    public RefundBookResponse refundBook(@RequestBody RefundBookRequest request) throws TestTaskException {
        return taskService.refundBook(request);
    }

    @PostMapping(path = "/showOwners")
    @Operation(summary = "showing where the book is now")
    public ShowOwnerResponse showOwners(@RequestBody ShowOwnerRequest request) {
        return taskService.showOwner(request);
    }

}
