package com.programmers.tilit.domain.user.dto.request;

import com.programmers.tilit.domain.user.entity.User;

public record UserCreateRequest(
    String email,
    String password
) {
    public User toEntity() {
        return User.create(email, password);
    }
}
