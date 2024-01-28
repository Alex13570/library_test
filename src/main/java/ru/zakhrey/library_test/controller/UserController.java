package ru.zakhrey.library_test.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zakhrey.library_test.dto.user.AddUserRequest;
import ru.zakhrey.library_test.dto.user.AddUserResponse;
import ru.zakhrey.library_test.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/add")
    @Operation(summary = "helping method", description = "register new user")
    public AddUserResponse addUser(@RequestBody AddUserRequest request) {
        return userService.addUser(request);
    }


}
