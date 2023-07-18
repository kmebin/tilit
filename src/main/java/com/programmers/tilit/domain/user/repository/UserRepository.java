package com.programmers.tilit.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programmers.tilit.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
