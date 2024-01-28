package ru.zakhrey.library_test.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zakhrey.library_test.dto.book.AddBookRequest;
import ru.zakhrey.library_test.dto.book.AddBookResponse;
import ru.zakhrey.library_test.service.BookService;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(path = "/add")
    @Operation(summary = "helping method", description = "register new book")
    public AddBookResponse addBook(@RequestBody AddBookRequest request) {
        return bookService.addBook(request);
    }

}
