package com.bs.union.midware.auth.handler;

import com.bs.union.midware.responsecode.EResponseCode;
import com.bs.union.midware.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:zrt
 * @Date:2021/1/2 2:31
 * @version:1.0
 */
@Component
public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String code = EResponseCode.FALI.getCode();
        String describe = EResponseCode.FALI.getDescribe();
        System.out.println(code+describe);
    }
}
