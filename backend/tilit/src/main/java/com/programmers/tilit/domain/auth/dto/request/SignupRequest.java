package com.programmers.tilit.domain.auth.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.programmers.tilit.domain.user.entity.User;

public record SignupRequest(
    @Email @NotBlank String email,
    @NotBlank @Size(min = 8, max = 32) String password
) {
    public User toEntity() {
        return User.create(email, password);
    }
}
