package ru.zakhrey.library_test.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String name;
    private String author;
    private LocalDateTime publicationDate;
    private Boolean isTaken;

}
