package ru.zakhrey.library_test.mapper;

import org.mapstruct.Mapper;
import ru.zakhrey.library_test.dto.task.RefundBookResponse;
import ru.zakhrey.library_test.dto.task.ShowOwnerResponse;
import ru.zakhrey.library_test.dto.task.TakeBookResponse;
import ru.zakhrey.library_test.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {BookMapper.class, UserMapper.class})
public interface TaskMapper {

    TakeBookResponse mapUserToTakeBookResponse(UserEntity user);
    RefundBookResponse mapUserToRefundBookResponse(UserEntity user);
    default ShowOwnerResponse mapUserToShowOwnerResponse(List<UserEntity> users) {
        return new ShowOwnerResponse(users.stream().map(UserEntity::getUserName).collect(Collectors.toList()));
    }

}
