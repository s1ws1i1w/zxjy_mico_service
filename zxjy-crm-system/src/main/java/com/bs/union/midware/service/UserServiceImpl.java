package com.bs.union.midware.service;

import cn.hutool.core.util.ObjectUtil;
import com.bs.union.midware.auth.handler.CustomSessionRegistry;
import com.bs.union.midware.mapper.user.SysUserMapper;
import com.bs.union.midware.model.entity.SysUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author:zrt
 * @Date:2021/1/2 2:13
 * @version:1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private CustomSessionRegistry customSessionRegistry;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity user=sysUserMapper.loadUserByUserName(username);
        if(ObjectUtil.isEmpty(user)){
            throw new UsernameNotFoundException("0");
        }

        System.out.println(user);
        user.setRoles(sysUserMapper.getRoleById(user.getRoleId()));
        customSessionRegistry.invalidateSession(user);
      return user;
    }
}
