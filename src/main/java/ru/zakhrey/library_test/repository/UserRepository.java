package ru.zakhrey.library_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zakhrey.library_test.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByTicketNumber(UUID ticketNumber);
    List<UserEntity> findByTakenBooksName(String name);
    List<UserEntity> findByTakenBooksIdIn(List<Long> ids);
    List<UserEntity> findByTakenBooksIssueDateLessThan(LocalDateTime dateTime);
}
