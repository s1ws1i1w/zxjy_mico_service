package com.bs.union.midware.controller;



import com.alibaba.fastjson.JSONObject;
import com.bs.union.midware.config.WxInitProperty;
import com.bs.union.midware.config.WxUserInfoDTO;
import com.bs.union.midware.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author:zrt
 * @Date:2020/12/27 2:22
 * @Description:com.bs.union.crm.controller
 * @version:1.0
 */

@Controller
@Slf4j
@RequestMapping(value = "/api/ucenter/wx")
public class WxLoginController {

    @Autowired
    private WxInitProperty wxInitProperty;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("/login")
    public String  login(){
        String baseUrl="https://open.weixin.qq.com/connect/qrconnect"+
                "?appid=%s"+
                "&redirect_uri=%s"+
                "&response_type=code"+
                "&scope=snsapi_login"+
                "&state=%s"+
                "#wechat_redirect";
        String redirectUrl = wxInitProperty.getRedirectUrl();
            String url=String.format(
                    baseUrl,
                    wxInitProperty.getAppId(),
                    redirectUrl,
                    "zzzz"
            );
        return "redirect:"+url;
    }

    /**
     * 微信回调接口时,将其注册到我们系统当中并让他登录到我们系统当中去
     * @param code 验证码
     * @param state 状态值
     * @return
     */
    @GetMapping("/callback")
    public String callback(String code,String state){
        String baseUrl="https://api.weixin.qq.com/sns/oauth2/access_token";
        Map<String,String> accessToken=new HashMap<>();
        accessToken.put("appid",wxInitProperty.getAppId());
        accessToken.put("secret",wxInitProperty.getAppSecret());
        accessToken.put("code",code);
        accessToken.put("grant_type","authorization_code");
        HttpClientUtils client=new HttpClientUtils(baseUrl,accessToken);
        String content;
        try {
            //发送请求
            client.get();
             content = client.getContent();
            log.info("result:"+content);
            JSONObject jsonObject= JSONObject.parseObject(content);
            if(jsonObject.getString("errorcode")!=null){
                content=jsonObject.getString("errmsg");
            }
            else{
                String userInfoUrl="https://api.weixin.qq.com/sns/userinfo";
                Map<String,String> userInfo=new HashMap<>();
                userInfo.put("access_token",jsonObject.getString("access_token"));
                userInfo.put("openid",jsonObject.getString("openid"));
                HttpClientUtils clientUtils=new HttpClientUtils(userInfoUrl,userInfo);
                clientUtils.get();
                String userDetils = clientUtils.getContent();
                log.info("userInfo:"+userDetils);
                JSONObject user=JSONObject.parseObject(userDetils);
                if(user!=null){
                    WxUserInfoDTO wxUserInfoDTO=new WxUserInfoDTO();
                    wxUserInfoDTO.setNickName(user.getString("nickname"));
                    wxUserInfoDTO.setCity(user.getString("city"));
                    wxUserInfoDTO.setHeadImageUrl(user.getString("headimgurl"));
                    wxUserInfoDTO.setSex(user.getString("sex"));
                    log.info("wxUserInfoDTO:"+wxUserInfoDTO);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
