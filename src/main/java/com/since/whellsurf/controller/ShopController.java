package com.since.whellsurf.controller;


import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.ret.AwardResult;
import com.since.whellsurf.ret.Result;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.service.AccountAwardService;
import com.since.whellsurf.service.ActivityService;
import com.since.whellsurf.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 王英豪111
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private AccountAwardService accountAwardService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/check")
    public Ret checkAccountAward(String awardCode){
         if (awardCode == null || "".equals(awardCode)){
              return Ret.error(AwardResult.AWARD_CODE_NOT_FOUND);
         }
         Shop shop = (Shop)request.getSession().getAttribute(SessionKey.LOGIN_SHOP);
         Shop shop1 = shopService.findByOpenId(shop.getOpenId());
         Activity activity = activityService.findByShopIdAndStatus(shop1.getId());
         AccountAward accountAward = accountAwardService.checkAccountAward(awardCode,activity.getId());
         if (accountAward == null){
             return Ret.error(AwardResult.AWARD_CODE_NOT_FOUND);
         }
         return Ret.success(accountAward);
    }


}
