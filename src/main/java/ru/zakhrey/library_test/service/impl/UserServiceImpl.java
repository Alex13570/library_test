package ru.zakhrey.library_test.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zakhrey.library_test.dto.user.AddUserRequest;
import ru.zakhrey.library_test.dto.user.AddUserResponse;
import ru.zakhrey.library_test.entity.UserEntity;
import ru.zakhrey.library_test.mapper.UserMapper;
import ru.zakhrey.library_test.repository.UserRepository;
import ru.zakhrey.library_test.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public AddUserResponse addUser(AddUserRequest addUserRequest) {
        UserEntity user = userMapper.map(addUserRequest);
        userRepository.save(user);
        return userMapper.map(user);
    }

    @Override
    public UserEntity findUser(UUID ticketNumber) {
        return userRepository.findByTicketNumber(ticketNumber);
    }

    @Override
    public void updateUserById(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public List<UserEntity> findAllUsersHwoTakenTheBook(String bookName) {
        return userRepository.findByTakenBooksName(bookName);
    }

    @Override
    public List<UserEntity> findAllUsersByBooksIds(List<Long> ids) {
        return userRepository.findByTakenBooksIdIn(ids);
    }

    @Override
    public List<UserEntity> findAllUsersByBooksIssueDateLessThan(LocalDateTime dateTime) {
        return userRepository.findByTakenBooksIssueDateLessThan(dateTime);
    }

}
