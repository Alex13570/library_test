package ru.zakhrey.library_test.service;

import ru.zakhrey.library_test.entity.UserEntity;

import java.time.LocalDateTime;

public interface MailService {

    void sendBookExpirationMail(UserEntity user , LocalDateTime dateTime);

}
