package com.programmers.tilit.domain.auth.dto.response;

import com.programmers.tilit.domain.user.entity.User;

import lombok.Builder;

@Builder
public record UserResponse(
    Long id,
    String email,
    String password
) {
    public static UserResponse from(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .email(user.getEmail())
            .password(user.getPassword())
            .build();
    }
}
