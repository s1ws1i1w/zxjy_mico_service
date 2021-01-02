package com.bs.union.midware.auth.handler;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Author:zrt
 * @Date:2021/1/2 2:36
 * @version:1.0
 */
@Component
public class SessionInformationExpiredStrategy  implements org.springframework.security.web.session.SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        System.out.println("该账号在异地登录了");
    }
}
