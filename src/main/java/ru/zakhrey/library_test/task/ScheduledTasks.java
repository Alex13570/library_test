package ru.zakhrey.library_test.task;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.zakhrey.library_test.entity.UserEntity;
import ru.zakhrey.library_test.service.MailService;
import ru.zakhrey.library_test.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final UserService userService;

    private final MailService mailService;

    @Scheduled(fixedRate = 5000)
    //@Scheduled(fixedRate = 86400000)
    public void reportCurrentTime() {
        LocalDateTime time = LocalDateTime.now().minusSeconds(10);
        //LocalDateTime time = LocalDateTime.now().minusDays(14);
        List<UserEntity> expiredUsers = userService.findAllUsersByBooksIssueDateLessThan(time);
        for (UserEntity user: expiredUsers) {
            mailService.sendBookExpirationMail(user, time);
        }
    }
}