package ru.zakhrey.library_test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.zakhrey.library_test.dto.book.AddBookRequest;
import ru.zakhrey.library_test.dto.book.AddBookResponse;
import ru.zakhrey.library_test.dto.task.BookDto;
import ru.zakhrey.library_test.entity.BookEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookEntity map(AddBookRequest addBookRequest);
    AddBookResponse map(BookEntity addBookRequest);
    @Mapping(target = "isTaken", expression = "java(java.util.Objects.nonNull(book.getIssueDate()))")
    BookDto mapBookToDto(BookEntity book);
    List<BookDto> map(List<BookEntity> books);

}
