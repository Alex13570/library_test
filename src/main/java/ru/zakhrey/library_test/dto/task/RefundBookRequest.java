package ru.zakhrey.library_test.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundBookRequest {

    private UUID ticketNumber;
    private String bookName;
    private String author;

}
