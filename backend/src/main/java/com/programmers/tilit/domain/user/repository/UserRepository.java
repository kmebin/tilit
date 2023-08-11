package com.programmers.tilit.domain.user.repository;

import java.util.Optional;

import com.programmers.tilit.domain.user.entity.User;

public interface UserRepository {

    void save(User user);

    User getById(Long id);

    Optional<User> findByEmail(String email);
}
