package com.programmers.tilit.domain.user.entity;

import static com.programmers.tilit.global.common.ErrorCode.*;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.programmers.tilit.domain.user.exception.UserBadRequestException;

import lombok.val;

@Converter
public class NicknameConverter implements AttributeConverter<String, String> {
    private static final int MAX_NICKNAME_LENGTH = 20;

    @Override
    public String convertToDatabaseColumn(String email) {
        val nickname = email.substring(0, email.indexOf('@'));

        if (nickname.length() > MAX_NICKNAME_LENGTH) {
            throw new UserBadRequestException(INVALID_NICKNAME);
        }
        return nickname;
    }

    @Override
    public String convertToEntityAttribute(String nickname) {
        return nickname;
    }
}
