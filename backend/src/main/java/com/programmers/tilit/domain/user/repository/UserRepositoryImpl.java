package com.programmers.tilit.domain.user.repository;

import static com.programmers.tilit.global.common.ErrorCode.*;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.programmers.tilit.domain.user.entity.User;
import com.programmers.tilit.domain.user.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userJpaRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(NO_USER));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }
}
