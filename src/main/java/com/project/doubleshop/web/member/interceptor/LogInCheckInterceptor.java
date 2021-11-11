package com.project.doubleshop.web.member.interceptor;

import com.project.doubleshop.domain.exception.UnauthenticatedMemberException;
import com.project.doubleshop.domain.member.service.LogInService;
import com.project.doubleshop.web.member.annotation.LogInCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class LogInCheckInterceptor implements HandlerInterceptor {

    private final LogInService logInService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LogInCheck logInCheck = handlerMethod.getMethodAnnotation(LogInCheck.class);

        if (logInCheck == null) {
            return true;
        }

        if (logInService.getLogInMember() == null) {
            throw new UnauthenticatedMemberException("로그인 후 이용 가능합니다.");
        }

        return true;
    }

}
