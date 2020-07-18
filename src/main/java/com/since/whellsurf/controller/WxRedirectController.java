package com.since.whellsurf.controller;

import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.common.Status;
import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.service.AccountService;
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
@RequestMapping("/wx/{appid}")
public class WxRedirectController {
    private final WxMpService wxService;


    @Autowired
    private ShopService shopService;

    @Autowired
    private AccountService accountService;


    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/shopGreet")
    public String greetShop(@PathVariable String appid, @RequestParam String code) {
        if (!this.wxService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        try {
            WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
            WxMpUser user = wxService.oauth2getUserInfo(accessToken, null);
            Shop shop = shopService.findByOpenId(user.getOpenId());
            if (shop == null){
                 //不是商家没有权限，添加到数据库（状态为未激活）
                shop.setOpenId(user.getOpenId());
                shop.setNickname(user.getNickname());
                shop.setAddress(user.getCountry() + user.getProvince() + user.getCity());
                shop.setHeadImgUrl(user.getHeadImgUrl());
                shop.setGender(user.getSexDesc());
                shop.setStatus(Status.SHOP_NOT_ACTIVATE);
                shopService.addShop(shop);
                return "/page/error/403.html";
            } else {
                request.getSession().setAttribute(SessionKey.LOGIN_SHOP,shop);
            }

            return "redirect:/to/index";
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "/page/error/404.html";
    }

    @RequestMapping("/accountGreet")
    public String greetUser(@PathVariable String appid, @RequestParam String code) {
        if (!this.wxService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        try {
            WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
            WxMpUser user = wxService.oauth2getUserInfo(accessToken, null);
            Shop shop = shopService.findByOpenId(user.getOpenId());
            if (shop == null){
                Account account = accountService.findByOpenId(user.getOpenId());
                if (account == null){
                    account.setOpenId(user.getOpenId());
                    account.setNickname(user.getNickname());
                    account.setAddress(user.getCountry() + user.getProvince() + user.getCity());
                    account.setHeadImgUrl(user.getHeadImgUrl());
                    account.setGender(user.getSexDesc());
                    account.setStatus(Status.ACCOUNT_EXIST);
                    accountService.addAccount(account);
                }
                request.getSession().setAttribute(SessionKey.LOGIN_USER,account);
            } else  {
                request.getSession().setAttribute(SessionKey.LOGIN_SHOP,shop);
            }

            return "redirect:/to/index";
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "/page/error/404.html";
    }
}
