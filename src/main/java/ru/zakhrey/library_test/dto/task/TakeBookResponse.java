package ru.zakhrey.library_test.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TakeBookResponse {

    private UUID ticketNumber;
    private String userName;
    private List<BookDto> takenBooks;

}
