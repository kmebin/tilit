package com.programmers.tilit.domain.user.controller;

import static com.programmers.tilit.global.common.SuccessMessage.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.tilit.domain.user.dto.request.UserCreateRequest;
import com.programmers.tilit.domain.user.service.UserService;
import com.programmers.tilit.global.common.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(CREATED)
    public BaseResponse<Object> createUser(@RequestBody UserCreateRequest request) {
        userService.signup(request);
        return BaseResponse.created(CREATE_USER_SUCCESS);
    }
}
