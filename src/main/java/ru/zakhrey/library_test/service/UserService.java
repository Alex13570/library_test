package ru.zakhrey.library_test.service;

import ru.zakhrey.library_test.dto.user.AddUserRequest;
import ru.zakhrey.library_test.dto.user.AddUserResponse;
import ru.zakhrey.library_test.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface UserService {

    AddUserResponse addUser(AddUserRequest addUserRequest);
    UserEntity findUser(UUID ticketNumber);
    void updateUserById(UserEntity user);
    List<UserEntity> findAllUsersHwoTakenTheBook(String bookName);
    List<UserEntity> findAllUsersByBooksIds(List<Long> ids);
    List<UserEntity> findAllUsersByBooksIssueDateLessThan(LocalDateTime dateTime);
}
