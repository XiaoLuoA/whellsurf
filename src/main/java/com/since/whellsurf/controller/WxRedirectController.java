package com.since.whellsurf.controller;

import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.common.Status;
import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.service.ShopService;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Edward
 */
@AllArgsConstructor
@Controller
@RequestMapping("/wx/redirect/{appid}")
public class WxRedirectController {
    private final WxMpService wxService;


    @Autowired
    private ShopService shopService;


    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/greet")
    public String greetUser(@PathVariable String appid, @RequestParam String code) {
        if (!this.wxService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        try {
            WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
            WxMpUser user = wxService.oauth2getUserInfo(accessToken, null);
            Account account = new Account();
            account.setOpenId(user.getOpenId());
            account.setNickname(user.getNickname());
            account.setAddress(user.getCountry() + user.getProvince() + user.getCity());
            account.setHeadImgUrl(user.getHeadImgUrl());
            account.setGender(user.getSexDesc());
            account.setStatus(Status.Account_Exist);

            Shop shop = shopService.findByOpenId(user.getOpenId());
            if (shop == null){
                request.getSession().setAttribute(SessionKey.LOGIN_USER,account);
            } else {
                request.getSession().setAttribute(SessionKey.LOGIN_SHOP,account);
            }

            return "redirect:/to/index";
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "/page/error/404.html";
    }
}
