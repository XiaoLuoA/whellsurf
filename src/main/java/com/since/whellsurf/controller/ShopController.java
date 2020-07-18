package com.since.whellsurf.controller;


import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.ret.AwardResult;
import com.since.whellsurf.ret.Result;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.service.AccountAwardService;
import com.since.whellsurf.service.ActivityService;
import com.since.whellsurf.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.since.whellsurf.ret.AccountAwardResult.NOT_FIND_AccountAward;
import static com.since.whellsurf.ret.ActivityResult.ACTIVITYID_EXCEPTION;
import static com.since.whellsurf.ret.Result.SUCCESS;
import static com.since.whellsurf.ret.ShopResult.NOT_FIND_SHOP_ACTIVITY;

/**
 * @author jayzh
 */

@Controller
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    AccountAwardService accountAwardService;

    @Autowired
    ActivityService activityService;

    @Autowired
    ShopService shopService;

    @Autowired
    HttpServletRequest httpServletRequest;



    /**
     * @author jayzh
     */
    @RequestMapping("/findNotRedeems")
    @ResponseBody
    public Ret findNotRedeems(String activityId){
        System.out.println("前台获得"+activityId);
        Ret ret;
        if(""==activityId){
           ret = new Ret(ACTIVITYID_EXCEPTION,null);
           return ret;
        }
        Long acId=Long.parseLong(activityId);
        List<AccountAward> accountAwards=activityService.getActivityAwardsById(acId);
        ret=new Ret(SUCCESS,accountAwards);
        return ret;
    }


    /**
     * @author jayzh
     */
    @RequestMapping("/findaccounts")
    @ResponseBody
    public Ret findAccounts(String activityId){
        System.out.println("前台获得"+activityId);
        Ret ret;
        if(""==activityId){
            ret = new Ret(ACTIVITYID_EXCEPTION,null);
            return ret;
        }
        Long acId=Long.parseLong(activityId);
        int amount=activityService.getAmountJoinActivity(acId);
        ret=new Ret(SUCCESS,amount);
        return ret;
    }

    /**
     * @author jayzh
     */
    @RequestMapping("/finish")
    @ResponseBody
    public Ret finish(){
        Shop shop=(Shop)httpServletRequest.getSession().getAttribute("shoper");
        Activity activity = null;
        Ret ret;
        try {
            activity=activityService.findExitActivity(shop.getId(),1);
            activity=activityService.finish(activity);
        }catch (Exception e){
            ret=new Ret(NOT_FIND_SHOP_ACTIVITY, null);
            return ret;
        }
        ret=new Ret(SUCCESS, activity.getStatus());
        return ret;
    }
    /**
     * @author jayzh
     */
    @RequestMapping("/redeem")
    @ResponseBody
    public Ret redeem(String userId,String activityId){
        Long acId=Long.parseLong(activityId);
        Long actId=Long.parseLong(userId);
        AccountAward accountAward;
        try{
            accountAward=accountAwardService.redeem(acId,actId);
        }catch (Exception e){
            Ret ret=new Ret(NOT_FIND_AccountAward, null);
            return ret;
        }
        Ret ret=new Ret(SUCCESS, accountAward);
        return ret;
    }


    @RequestMapping("/check")
    public Ret checkAccountAward(String awardCode){
        if (awardCode == null || "".equals(awardCode)){
            return Ret.error(AwardResult.AWARD_CODE_NOT_FOUND);
        }
        Shop shop = (Shop)httpServletRequest.getSession().getAttribute(SessionKey.LOGIN_SHOP);
        Activity activity = activityService.findValidActivityByShopId(shop.getId());
        AccountAward accountAward = accountAwardService.checkAccountAward(awardCode,activity.getId());
        if (accountAward == null){
            return Ret.error(AwardResult.AWARD_CODE_NOT_FOUND);
        }
        return Ret.success(accountAward);
    }

}
