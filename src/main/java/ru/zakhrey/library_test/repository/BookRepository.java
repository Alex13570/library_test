package ru.zakhrey.library_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zakhrey.library_test.entity.BookEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAllByName(String name);
    List<BookEntity> findAllByNameAndAuthor(String name, String author);
    List<BookEntity> findAllByNameAndAuthorAndIssueDate(String name, String author, LocalDateTime issueDate);
    List<BookEntity> findAllByIssueDateLessThan(LocalDateTime dateTime);

}
