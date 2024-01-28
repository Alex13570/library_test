package ru.zakhrey.library_test.mapper;

import org.mapstruct.Mapper;
import ru.zakhrey.library_test.dto.user.AddUserRequest;
import ru.zakhrey.library_test.dto.user.AddUserResponse;
import ru.zakhrey.library_test.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity map(AddUserRequest addUserRequest);
    AddUserResponse map(UserEntity user);

}
