package com.programmers.tilit.domain.auth.service;

import static com.programmers.tilit.global.common.ErrorCode.*;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.tilit.domain.auth.dto.request.LoginRequest;
import com.programmers.tilit.domain.auth.dto.request.SignupRequest;
import com.programmers.tilit.domain.auth.dto.response.SessionUser;
import com.programmers.tilit.domain.auth.exception.AuthBadRequestException;
import com.programmers.tilit.domain.user.entity.User;
import com.programmers.tilit.domain.user.exception.UserConflictException;
import com.programmers.tilit.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public void signUp(SignupRequest request) {
        validateDuplicate(userRepository.findByEmail(request.email()));
        userRepository.save(request.toEntity());
    }

    public SessionUser logIn(LoginRequest request) {
        return userRepository.findByEmail(request.email())
            .filter(user -> user.getPassword().equals(request.password()))
            .map(SessionUser::from)
            .orElseThrow(() -> new AuthBadRequestException(LOGIN_FAIL));
    }

    private void validateDuplicate(Optional<User> user) {
        user.ifPresent(u -> {
            throw new UserConflictException(DUPLICATE_USER_EMAIL);
        });
    }
}
