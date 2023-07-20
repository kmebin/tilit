package com.programmers.tilit.domain.auth.controller;

import static com.programmers.tilit.global.common.SuccessMessage.*;
import static org.springframework.http.HttpStatus.*;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.tilit.domain.auth.dto.request.LoginRequest;
import com.programmers.tilit.domain.auth.dto.request.SignupRequest;
import com.programmers.tilit.domain.auth.service.AuthService;
import com.programmers.tilit.global.common.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    @ResponseStatus(CREATED)
    public BaseResponse<Object> signUp(@RequestBody SignupRequest request) {
        authService.signUp(request);
        return BaseResponse.created(CREATE_USER_SUCCESS);
    }

    @PostMapping("/login")
    public BaseResponse<Object> logIn(@RequestBody LoginRequest request, HttpSession session) {
        var user = authService.logIn(request);
        session.setAttribute("user", user);

        return BaseResponse.ok(LOGIN_SUCCESS);
    }
}
