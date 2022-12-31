package me.lee_sh1673.book.config.auth;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.lee_sh1673.book.config.auth.dto.SessionUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements
    HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return isLoginUserAnnotation(parameter) && isUserClass(parameter);
    }

    private boolean isLoginUserAnnotation(final MethodParameter parameter) {
        return parameter.getParameterAnnotation(LoginUser.class) != null;
    }

    private boolean isUserClass(final MethodParameter parameter) {
        return SessionUser.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter,
        final ModelAndViewContainer mavContainer,
        final NativeWebRequest webRequest,
        final WebDataBinderFactory binderFactory) throws Exception {

        return httpSession.getAttribute("user");
    }
}
