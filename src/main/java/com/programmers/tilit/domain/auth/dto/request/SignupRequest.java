package com.programmers.tilit.domain.auth.dto.request;

import com.programmers.tilit.domain.user.entity.User;

public record SignupRequest(
    String email,
    String password
) {
    public User toEntity() {
        return User.create(email, password);
    }
}
