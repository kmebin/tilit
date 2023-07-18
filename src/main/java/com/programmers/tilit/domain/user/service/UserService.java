package com.programmers.tilit.domain.user.service;

import static com.programmers.tilit.global.common.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.tilit.domain.user.entity.User;
import com.programmers.tilit.domain.user.exception.UserNotFoundException;
import com.programmers.tilit.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public User findUserOrThrow(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(NO_USER));
    }
}
