package com.programmers.tilit.global.common.annotation;

import static com.programmers.tilit.global.common.ErrorCode.*;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.programmers.tilit.domain.auth.dto.response.SessionUser;
import com.programmers.tilit.domain.auth.exception.UnauthorizedException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginUserAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
        boolean isUserType = SessionUser.class.isAssignableFrom(parameter.getParameterType());

        return hasLoginUserAnnotation && isUserType;
    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory
    ) {
        return Optional.ofNullable(httpSession.getAttribute("user"))
            .orElseThrow(() -> new UnauthorizedException(UNAUTHORIZED_USER));
    }
}
