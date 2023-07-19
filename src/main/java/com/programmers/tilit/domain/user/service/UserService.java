package com.programmers.tilit.domain.user.service;

import static com.programmers.tilit.global.common.ErrorCode.*;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.tilit.domain.course.exception.CourseConflictException;
import com.programmers.tilit.domain.user.dto.request.UserCreateRequest;
import com.programmers.tilit.domain.user.entity.User;
import com.programmers.tilit.domain.user.exception.UserNotFoundException;
import com.programmers.tilit.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void signup(UserCreateRequest request) {
        validateDuplicate(userRepository.findByEmail(request.email()));
        userRepository.save(request.toEntity());
    }

    public User findUserOrThrow(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(NO_USER));
    }

    private void validateDuplicate(Optional<User> user) {
        user.ifPresent(u -> {
            throw new CourseConflictException(DUPLICATE_USER_EMAIL);
        });
    }
}
