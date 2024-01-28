package ru.zakhrey.library_test.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.zakhrey.library_test.dto.task.*;
import ru.zakhrey.library_test.exception.TestTaskException;

import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceTests {

    @Autowired
    private TaskService taskService;

    @Test
    void getAllBooksPositive() {
        //Run Logic
        GetAllBooksResponse books = taskService.getAllBooks();

        //Check
        for (Integer i = 1; i < 15; i++) {
            String bookName = String.format("book %s", i);
            String authorName = String.format("author %s", i);
            assertTrue(books.getBooks().stream().anyMatch(el -> el.getName().equals(bookName)));
            assertTrue(books.getBooks().stream().anyMatch(el -> el.getAuthor().equals(authorName)));
        }
    }

    @Test
    void takeBookPositive() throws TestTaskException {
        //Prepare
        TakeBookRequest takeBookRequest = TakeBookRequest.builder()
                .bookName("book 1")
                .author("author 1")
                .ticketNumber(UUID.fromString("5f7a911d-9ce9-49d0-b1f6-0328eca41cdd")) // user 1
                .build();
        //Run Logic
        TakeBookResponse response = taskService.takeBook(takeBookRequest);
        //Check
        assertNotEquals(response, null);
        assertEquals(response.getUserName(), "user 1");
        assertTrue(response.getTakenBooks().stream().anyMatch(el -> el.getName().equals(takeBookRequest.getBookName())));
    }

    @Test
    @SneakyThrows
    void takeBookNotRightTicket() {
        //Prepare
        TakeBookRequest takeBookRequest = TakeBookRequest.builder()
                .bookName("book 1")
                .author("author 1")
                .ticketNumber(UUID.fromString("5f7a911d-9ce9-49d0-b1f6-0328eca41666")) // no user
                .build();
        //Run Logic
        Exception exception = assertThrows(TestTaskException.class, () -> taskService.takeBook(takeBookRequest));

        String expectedMessage = "There is no such ticket, please, sign up";
        String actualMessage = exception.getMessage();

        //Check
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void takeBookNotRightBookName() {
        //Prepare
        TakeBookRequest takeBookRequest = TakeBookRequest.builder()
                .bookName("book not found")
                .author("author either")
                .ticketNumber(UUID.fromString("5f7a911d-9ce9-50d0-b1f6-0328eca41cda")) // user 2
                .build();
        //Run Logic
        Exception exception = assertThrows(TestTaskException.class, () -> taskService.takeBook(takeBookRequest));

        String expectedMessage = "There is no such book in our library";
        String actualMessage = exception.getMessage();

        //Check
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void takeBookBookIsTaken() throws TestTaskException {
        //Prepare
        TakeBookRequest takeBookRequest = TakeBookRequest.builder()
                .bookName("book 2")
                .author("author 2")
                .ticketNumber(UUID.fromString("5f7a911d-9ce9-50d0-b1f6-0328eca41cda")) // user 2
                .build();
        //Run Logic
        taskService.takeBook(takeBookRequest);
        Exception exception = assertThrows(TestTaskException.class, () -> taskService.takeBook(takeBookRequest));

        String expectedMessage = "There is no free book from your order";
        String actualMessage = exception.getMessage();

        //Check
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void refundBookPositive() throws TestTaskException {
        //Prepare
        TakeBookRequest takeBookRequest = TakeBookRequest.builder()
                .bookName("book 3")
                .author("author 3")
                .ticketNumber(UUID.fromString("5f7a911d-9ce9-51d0-b1f6-0328eca41cda")) // user 3
                .build();

        RefundBookRequest refundBookRequest = RefundBookRequest.builder()
                .bookName("book 3")
                .author("author 3")
                .ticketNumber(UUID.fromString("5f7a911d-9ce9-51d0-b1f6-0328eca41cda")) // user 3
                .build();
        //Run Logic
        taskService.takeBook(takeBookRequest);
        RefundBookResponse response = taskService.refundBook(refundBookRequest);
        //Check
        assertNotEquals(response, null);
        assertEquals(response.getUserName(), "user 3");
        assertTrue(response.getTakenBooks().stream().noneMatch(el -> el.getName().equals(takeBookRequest.getBookName())));
    }

    @Test
    void refundBookBookNotTaken() {
        //Prepare
        RefundBookRequest refundBookRequest = RefundBookRequest.builder()
                .bookName("book 3")
                .author("author 3")
                .ticketNumber(UUID.fromString("5f7a911d-9ce9-51d0-b1f6-0328eca41cda")) // user 2
                .build();
        //Run Logic
        Exception exception = assertThrows(TestTaskException.class, () -> taskService.refundBook(refundBookRequest));

        String expectedMessage = "You did not took this book";
        String actualMessage = exception.getMessage();

        //Check
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void showOwnersPositive() throws TestTaskException {
        //Prepare
        TakeBookRequest takeBookRequest = TakeBookRequest.builder()
                .bookName("book 5")
                .author("author 5")
                .ticketNumber(UUID.fromString("5f7a911d-9ce9-52d0-b1f6-0328eca41cda")) // user 4
                .build();
        ShowOwnerRequest showOwnerRequest = ShowOwnerRequest.builder()
                .bookName("book 5")
                .author("author 5")
                .build();
        //Run Logic
        taskService.takeBook(takeBookRequest);
        ShowOwnerResponse response = taskService.showOwner(showOwnerRequest);
        //Check
        assertNotEquals(response, null);
        assertEquals(response.getOwnersNames().size(), 1);
        assertTrue(response.getOwnersNames().stream().anyMatch(el -> el.equals("user 4")));
    }

}
