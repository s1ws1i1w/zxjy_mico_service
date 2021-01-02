package com.bs.union.midware.auth.handler;

import com.bs.union.midware.responsecode.EResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:zrt
 * @Date:2021/1/2 2:30
 * @version:1.0
 */
@Component
@Slf4j
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String code = EResponseCode.SUCCESS.getCode();
        String describe = EResponseCode.SUCCESS.getDescribe();
        log.info(code+describe);
    }
}
