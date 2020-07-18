package com.since.whellsurf.controller;

import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.ret.AwardResult;
import com.since.whellsurf.ret.Result;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.dto.CheckAwardParameter;
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
import static com.since.whellsurf.common.Status.*;
import static com.since.whellsurf.ret.AccountAwardResult.NOT_FIND_ACCOUNT_AWARD;
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
    @RequestMapping("/findAllRedeems")
    @ResponseBody
    public Ret findAllRedeems(Long activityId){
        Ret ret;
        List<AccountAward> accountAwards=null;
        if(null==activityId){
            ret = new Ret(ACTIVITYID_EXCEPTION,null);
            return ret;
        }
        try{
            accountAwards=activityService.getActivityAwardsById(activityId);
        }catch (Exception e){
            ret=new Ret(NOT_FIND_ACCOUNT_AWARD,null);
            return ret;
        }
        accountAwards=accountAwardService.hideUselessInformation(accountAwards);
        ret=new Ret(SUCCESS,accountAwards);
        return ret;
    }


    /**
     * @author jayzh
     */
    @RequestMapping("/findaccounts")
    @ResponseBody
    public Ret findAccounts(Long activityId){
        Ret ret;
        int amount;
        if(null==activityId){
            ret = new Ret(ACTIVITYID_EXCEPTION,null);
            return ret;
        }
        try {
            amount=activityService.getAmountJoinActivity(activityId);
        }catch (Exception e){
            ret=new Ret(NOT_FIND_SHOP_ACTIVITY,ACTIVITY_ZERO);
            return ret;
        }

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
            activity=activityService.findExitActivity(shop.getId(),ACTIVITY_YET_EXIT);
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
    public Ret redeem(Long userId,Long activityId){
        AccountAward accountAward;
        try{
            accountAward=accountAwardService.redeem(activityId,userId);
        }catch (Exception e){
            Ret ret=new Ret(NOT_FIND_ACCOUNT_AWARD, null);
            return ret;
        }
        Ret ret=new Ret(SUCCESS, accountAward.getStatus());
        return ret;
    }

    @RequestMapping("/findNotRedeems")
    @ResponseBody
    public Ret findNotRedeems(Long activityId){
        Ret ret;
        if(null==activityId){
            ret = new Ret(ACTIVITYID_EXCEPTION,null);
            return ret;
        }
        List<AccountAward> accountAwards=accountAwardService.findAccountAward(activityId,REDEEM_STATUS_OK);
        if (accountAwards.size()<=0){
            ret=new Ret(NOT_FIND_ACCOUNT_AWARD,null);
            return ret;
        }
        accountAwards=accountAwardService.hideUselessInformation(accountAwards);
        ret=new Ret(SUCCESS,accountAwards);
        return ret;
    }

    @RequestMapping("/findRedeems")
    @ResponseBody
    public Ret findRedeems(Long activityId){
        Ret ret;
        if(null==activityId){
            ret = new Ret(ACTIVITYID_EXCEPTION,null);
            return ret;
        }
        List<AccountAward> accountAwards=accountAwardService.findAccountAward(activityId,REDEEM_STATUS_OK);
        if (accountAwards.size()<=0){
            ret=new Ret(NOT_FIND_ACCOUNT_AWARD,null);
            return ret;
        }
        accountAwards=accountAwardService.hideUselessInformation(accountAwards);
        ret=new Ret(SUCCESS,accountAwards);
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
        CheckAwardParameter cap = new CheckAwardParameter();
        cap.setAwardName(accountAward.getAwardName());
        cap.setHeadImgUrl(accountAward.getHeadImgUrl());
        cap.setStatus(accountAward.getStatus());
        return Ret.success(cap);
    }

}
