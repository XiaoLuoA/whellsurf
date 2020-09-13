package com.since.whellsurf.controller;

import com.since.whellsurf.common.Config;
import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.ret.ActivityResult;
import com.since.whellsurf.ret.Code;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.service.ActivityService;
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
@RequestMapping("/wx")
public class WxRedirectController {
    private final WxMpService wxService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/shopGreet")
    public String greetShop(@RequestParam String code) {
        String appId = Config.appId;
        if (!this.wxService.switchover(appId)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appId));
        }
        try {
            WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
            WxMpUser user = wxService.oauth2getUserInfo(accessToken, null);
            Ret ret = shopService.checkShop(user);
            if (ret.isOk()){
                Shop shop = (Shop)ret.getData();
                request.getSession().setAttribute(SessionKey.LOGIN_SHOP, shop);
                Activity activity = activityService.findRunningActivity(shop.getId());
                if (activity==null){
                    return "redirect:/to/createActivity";
                }
                return "redirect:/to/findAward?activityId="+activity.getId();
            } else {
                return "redirect:/to/noShop";
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "/page/error/404.html";
    }


    @RequestMapping("/accountGreet/{activityId}")
    public String greetUser(@PathVariable Long activityId , @RequestParam String code) {
        String appId = Config.appId;
        if (!this.wxService.switchover(appId)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appId));
        }
        try {
            WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
            WxMpUser user = wxService.oauth2getUserInfo(accessToken, null);
            Ret ret = shopService.checkActivity(user.getOpenId(),activityId);
            if (ret.isOk()){
                String data = (String) ret.getData();
                switch (data){
                    case SessionKey.LOGIN_SHOP : {
                        request.getSession().setAttribute(SessionKey.LOGIN_SHOP,shopService.findShopByOpenId(user.getOpenId()));
                        return "redirect:/to/lottery";
                    }
                    case SessionKey.LOGIN_USER : {
                        request.getSession().setAttribute(SessionKey.LOGIN_USER,shopService.findOrAddAccountByOpenId(user));
                        return "redirect:/to/lottery";
                    }
                }
            }else{
                Code errCode = (Code)ret.getData();
                if(errCode.equals(ActivityResult.ACTIVITY_NOT_FIND)){
                    return "redirect:/to/noActivity";
                }else {
                    return "redirect:/to/activityOut";
                }
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "/page/error/404.html";
    }
}
