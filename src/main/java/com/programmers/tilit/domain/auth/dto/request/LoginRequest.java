package com.programmers.tilit.domain.auth.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record LoginRequest(
    @NotBlank @Email String email,

    @NotBlank @Size(min = 8, max = 32) String password
) {
}
