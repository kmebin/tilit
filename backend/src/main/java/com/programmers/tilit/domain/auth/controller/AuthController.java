package com.programmers.tilit.domain.auth.controller;

import static com.programmers.tilit.global.common.SuccessMessage.*;
import static java.util.Objects.*;
import static org.springframework.http.HttpStatus.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

    @PostMapping("/signup")
    @ResponseStatus(CREATED)
    public BaseResponse<Object> signUp(@RequestBody @Valid SignupRequest request) {
        authService.signUp(request);
        return BaseResponse.created(SIGN_UP_SUCCESS);
    }

    @PostMapping("/login")
    public BaseResponse<Object> logIn(@RequestBody @Valid LoginRequest request, HttpSession session) {
        var user = authService.logIn(request);
        session.setAttribute("user", user);

        return BaseResponse.ok(LOG_IN_SUCCESS, user);
    }

    @PostMapping("/logout")
    public BaseResponse<Object> logOut(HttpSession session) {
        if (!isNull(session)) {
            session.invalidate();
        }
        return BaseResponse.ok(LOG_OUT_SUCCESS);
    }
}
