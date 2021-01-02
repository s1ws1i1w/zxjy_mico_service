package com.bs.union.midware.auth.handler;

import com.bs.union.midware.model.entity.SysUserEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zrt
 * @Date:2021/1/2 2:37
 * @version:1.0
 */
@Component
public class CustomSessionRegistry extends SessionRegistryImpl {
    /**
     * 获得用户Session信息
     *
     * @param user 用户信息
     */
    private List<SessionInformation> getSessionInformationList(SysUserEntity user) {
        // 获取父类会话注册器Session主体
        List<Object> users = this.getAllPrincipals();
        List<SessionInformation> sessionInformationList = new ArrayList<>();
        for (Object principal : users) {
            if (principal instanceof SysUserEntity) {
                final SysUserEntity loggedUser = (SysUserEntity) principal;
                if (user.getId().equals(loggedUser.getId())) {
                    // 返回该用户全部Session信息
                    sessionInformationList.addAll(this.getAllSessions(principal, false));
                }
            }
        }
        return sessionInformationList;
    }

    /**
     * 若存在用户已登录对当前登录用户下线
     *
     * @param user 用户信息
     */
    public void invalidateSession(SysUserEntity user) {
        List<SessionInformation> sessionsInfo = this.getSessionInformationList(user);
        for (SessionInformation sessionInformation : sessionsInfo) {
            // 会话过期
            sessionInformation.expireNow();
        }
    }
}
