package com.programmers.tilit.domain.auth.dto.request;

public record LoginRequest(
    String email,
    String password
) {
}
