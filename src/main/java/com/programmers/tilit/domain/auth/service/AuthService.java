package com.programmers.tilit.domain.auth.service;

import static com.programmers.tilit.global.common.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.tilit.domain.auth.dto.request.LoginRequest;
import com.programmers.tilit.domain.auth.dto.response.UserResponse;
import com.programmers.tilit.domain.auth.exception.AuthBadRequestException;
import com.programmers.tilit.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final UserRepository userRepository;

    public UserResponse login(LoginRequest request) {
        return userRepository.findByEmail(request.email())
            .filter(user -> user.getPassword().equals(request.password()))
            .map(UserResponse::from)
            .orElseThrow(() -> new AuthBadRequestException(LOGIN_FAIL));
    }
}
