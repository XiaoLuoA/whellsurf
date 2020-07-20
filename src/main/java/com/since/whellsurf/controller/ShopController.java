package com.since.whellsurf.controller;

import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.common.Status;
import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.ret.AccountResult;
import com.since.whellsurf.ret.ActivityResult;
import com.since.whellsurf.ret.AwardResult;
import com.since.whellsurf.dto.CheckAwardParameter;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.service.AccountAwardService;
import com.since.whellsurf.service.ActivityService;
import com.since.whellsurf.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    HttpServletRequest request;



    @RequestMapping("/checkDrawPrize")
    @ResponseBody
    public Ret CheckDrawPrize(@RequestParam String activityId){
        Long acId = Long.valueOf(activityId);
        //判断活动是否有效
        Activity activity = activityService.findByActivityIdAndStatus(acId, Status.ACTIVITY_VALID);
        if (activity == null){
            return Ret.error(ActivityResult.ACTIVITY_IS_OUTDATED);
        }

        Account account = (Account)request.getSession().getAttribute(SessionKey.LOGIN_USER);
        Shop shop = (Shop) request.getSession().getAttribute(SessionKey.LOGIN_SHOP);
        if (account == null || shop == null){
            return Ret.error(AccountResult.ACCOUNT_NOT_LOGIN);
        }
        //先根据用户的openid得到中奖信息
        AccountAward accountAward = accountAwardService.findByOpenId(account.getOpenid());
        if (accountAward != null){
            //存在中奖信息
            return Ret.error(AwardResult.GET_AWARD_REPEAT);
        }
        Activity shopActivity = activityService.findRunningActivity(shop.getId());
        //如果商家的活动id和此活动id不匹配，说明商家作为一个用户抽别的商家的奖
        if (!shopActivity.getId().equals(activityId) ){
            //检查中奖表中是否存在商家中奖别的活动的信息
            AccountAward accountAward_shop = accountAwardService.findByOpenId(account.getOpenid());
            if (accountAward_shop != null){
                return Ret.error(AwardResult.GET_AWARD_REPEAT);
            }
        }
        return Ret.success("可以抽奖");
    }
    }

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
        Shop shop=(Shop)request.getSession().getAttribute("shoper");
        Activity activity = null;
        Ret ret;
        try {
            activity=activityService.findRunningActivity(shop.getId(),ACTIVITY_YET_EXIT);
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
    public Ret checkAccountAward(@RequestParam String awardCode){
        System.out.println(awardCode);
        if (awardCode == null || "".equals(awardCode)){
            return Ret.error(AwardResult.AWARD_CODE_NOT_FOUND);
        }
        /*
        Shop shop = (Shop)httpServletRequest.getSession().getAttribute(SessionKey.LOGIN_SHOP);*/
        Shop shop = new Shop();
        shop.setId(1L);
        shop.setOpenid("111111");
        shop.setNickname("老党");
        shop.setAddress("郑州");
        shop.setHeadImgUrl("wwwwwwww");
        shop.setGender("男");
        shop.setStatus(Status.ACCOUNT_EXIST);
        Activity activity = activityService.findRunningActivity(shop.getId());
        AccountAward accountAward = accountAwardService.checkAccountAward(awardCode,activity.getId());
        if (accountAward == null){
            return Ret.error(AwardResult.AWARD_CODE_NOT_FOUND);
        }
        CheckAwardParameter cap = new CheckAwardParameter();
        cap.setAwardName(accountAward.getAwardName());
        cap.setHeadImgUrl(accountAward.getHeadImgUrl());
        cap.setStatus(accountAward.getStatus());
        System.out.println(cap);
        return Ret.success(cap);
    }

}
