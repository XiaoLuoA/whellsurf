package com.since.whellsurf.controller;

import com.since.whellsurf.common.Config;
import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.common.Status;
import com.since.whellsurf.dto.User;
import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.service.AccountService;
import com.since.whellsurf.service.ActivityService;
import com.since.whellsurf.service.ShopService;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;
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
@RequestMapping("/wx")
public class WxRedirectController {
    private final WxMpService wxService;


    @Autowired
    private ShopService shopService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ActivityService activityService;


    @Autowired
    private HttpServletRequest request;



    @RequestMapping("/shopGreet")
    public String greetShop(@RequestParam String code) {
        String appid = Config.appId;
        if (!this.wxService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        try {
            WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
            WxMpUser user = wxService.oauth2getUserInfo(accessToken, null);
            Shop shop = shopService.findByOpenId(user.getOpenId());
            if (shop == null){
                 //不是商家没有权限，添加到数据库（状态为未激活）
                shop.setOpenid(user.getOpenId());
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
            Long activityId = Long.valueOf(request.getParameter("activity"));
            Activity activity = activityService.findByActivityIdAndStatus(activityId,Status.ACTIVITY_VALID);
            if (shop != null){
                //数据库存在商家信息
                if (shop.getId().equals(activity.getShopId())){
                    //活动属于商家
                    request.getSession().setAttribute(SessionKey.LOGIN_SHOP,shop);
                }else {
                    //但此活动不属于商家，将商家作为用户存进数据库
                    Account account = new Account();
                    account.setOpenid(shop.getOpenid());
                    account.setNickname(shop.getNickname());
                    account.setAddress(shop.getAddress());
                    account.setHeadImgUrl(shop.getHeadImgUrl());
                    account.setGender(shop.getGender());
                    account.setStatus(Status.ACCOUNT_EXIST);
                    Account account1 = accountService.addAccount(account);
                    request.getSession().setAttribute(SessionKey.LOGIN_USER,account1);
                }

            }
              //判断用户是否存在
                Account account = accountService.findByOpenId(user.getOpenId());
                if (account == null){
                    Account account1 = new Account();
                    account1.setOpenid(user.getOpenId());
                    account1.setNickname(user.getNickname());
                    account1.setAddress(user.getCountry() + user.getProvince() + user.getCity());
                    account1.setHeadImgUrl(user.getHeadImgUrl());
                    account1.setGender(user.getSexDesc());
                    account1.setStatus(Status.ACCOUNT_EXIST);
                    Account accountNew = accountService.addAccount(account1);
                    request.getSession().setAttribute(SessionKey.LOGIN_USER,accountNew);

                } else {
                    request.getSession().setAttribute(SessionKey.LOGIN_USER,account);
                }

            return "redirect:/to/index?activity="+activityId;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "/page/error/404.html";
    }
}
