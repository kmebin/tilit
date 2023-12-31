package com.programmers.tilit.domain.auth.dto.response;

import com.programmers.tilit.domain.user.entity.User;

import lombok.Builder;

@Builder
public record SessionUser(
    Long id,
    String email,
    String nickname
) {
    public static SessionUser from(User user) {
        return SessionUser.builder()
            .id(user.getId())
            .email(user.getEmail())
            .nickname(user.getNickname())
            .build();
    }
}
