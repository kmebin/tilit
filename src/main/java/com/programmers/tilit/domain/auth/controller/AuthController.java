package com.programmers.tilit.domain.auth.controller;

import static com.programmers.tilit.global.common.SuccessMessage.*;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.tilit.domain.auth.dto.request.LoginRequest;
import com.programmers.tilit.domain.auth.service.AuthService;
import com.programmers.tilit.global.common.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public BaseResponse<Object> login(@RequestBody LoginRequest request, HttpSession session) {
        var user = authService.login(request);
        session.setAttribute("user", user);

        return BaseResponse.ok(LOGIN_SUCCESS);
    }
}
