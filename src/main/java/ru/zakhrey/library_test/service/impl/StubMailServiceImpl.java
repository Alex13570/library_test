package ru.zakhrey.library_test.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zakhrey.library_test.entity.BookEntity;
import ru.zakhrey.library_test.entity.UserEntity;
import ru.zakhrey.library_test.service.MailService;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StubMailServiceImpl implements MailService  {
    /**
     * самая простая заглушкаю можно поставить больше условий,
     * чтобы не спамить посчтй пользователям на каждое отрабатывание таски
     * @param user пользователь
     * @param dateTime максимальное время
     */
    @Override
    public void sendBookExpirationMail(UserEntity user, LocalDateTime dateTime) {

        String booksNames = user.getTakenBooks()
                .stream()
                .filter(el -> el.getIssueDate().isBefore(dateTime))
                .map(BookEntity::getName)
                .collect(Collectors.joining(", "));

        String message = String.format("sending mail to %s \nyou took our book(s): %s.\n We are waiting for you again to exchange them to new!", user.getUserName(), booksNames);
        System.out.println(message);
    }
}
