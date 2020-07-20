package com.since.whellsurf.controller;

import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.common.Status;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static com.since.whellsurf.common.Status.*;
import static com.since.whellsurf.ret.AccountAwardResult.*;
import static com.since.whellsurf.ret.AccountResult.*;
import static com.since.whellsurf.ret.ActivityResult.*;
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



    /**
     * @author jayzh
     */
    @RequestMapping("/findAllRedeems")
    @ResponseBody
    public Ret findAllRedeems(Long activityId){
        if(activityId == null){
            return Ret.error(ACTIVITYID_EXCEPTION);
        }
        List<AccountAward> accountAwards = accountAwardService.findAccountAwardByActivityId(activityId);
          if (accountAwards.size() == 0){
              return Ret.error(NOT_FIND_ALLREDEEMS);
          }
        accountAwards = accountAwardService.hideUselessInformation(accountAwards);
        return Ret.success(accountAwards);
    }


    /**
     * @author jayzh
     */
    @RequestMapping("/findAccounts")
    @ResponseBody
    public Ret findAccounts(Long activityId){
        if( activityId == null){
            return Ret.error(ACTIVITYID_EXCEPTION);
        }
        int amount = accountAwardService.findJoinActivityAmount(activityId);
        return Ret.success(amount);
    }

    /**
     * @author jayzh
     */
    @RequestMapping("/finish")
    @ResponseBody
    public Ret finish(){
        Shop shop = (Shop)request.getSession().getAttribute(SessionKey.LOGIN_SHOP);
        if (shop == null){
            return Ret.error(ACCOUNT_NOT_LOGIN);
        }
        Activity activity = activityService.findRunningActivity(shop.getId());
        if (activity == null){
            return Ret.error(NOT_FIND_SHOP_ACTIVITY);
        }
        Activity activity1 = activityService.finish(shop.getId());
        if (ACTIVITY_END.equals(activity1.getStatus())){
            return Ret.success();
        }else {
            return Ret.error(FINISH_ACTIVITY_FAIL);
        }


    }
    /**
     * @author jayzh
     */
    @RequestMapping("/redeem")
    @ResponseBody
    public Ret redeem(Long userId,Long activityId){
        if (userId == null || activityId == null){
            return Ret.error(ACTIVITYID_EXCEPTION);
        }

       /* Shop shop = (Shop)request.getSession().getAttribute(SessionKey.LOGIN_SHOP);*/

        Activity activity = activityService.findRunningActivity(1L);
        if (!activity.getId().equals(activityId)){
            return Ret.error(ACTIVITY_EXCEPTION);
        }
        AccountAward accountAward = accountAwardService.redeem(activityId,userId);
        if (accountAward == null){
            return Ret.error(NOT_FIND_ACCOUNT_AWARD);
        }
        return Ret.success();
    }

    @RequestMapping("/findNotRedeems")
    @ResponseBody
    public Ret findNotRedeems(Long activityId){

        if(activityId == null){
            return Ret.error(ACTIVITYID_EXCEPTION);
        }
        List<AccountAward> accountAwards=accountAwardService.findAccountAwardByActivityIdAndStatus(activityId,REDEEM_STATUS_NOT);
        if (accountAwards.size() == 0){
            return Ret.error(NOT_FIND_NOT_REDEEMS);
        }
        accountAwards=accountAwardService.hideUselessInformation(accountAwards);
        return Ret.success(accountAwards);
    }

    @RequestMapping("/findRedeems")
    @ResponseBody
    public Ret findRedeems(Long activityId){
        if(activityId == null){
            return Ret.error(ACTIVITYID_EXCEPTION);
        }
        List<AccountAward> accountAwards = accountAwardService.findAccountAwardByActivityIdAndStatus(activityId,REDEEM_STATUS_ALREADY);
        if (accountAwards.size() == 0){
            return Ret.error(NOT_FIND_REDEEMS);
        }
        accountAwards=accountAwardService.hideUselessInformation(accountAwards);
        return Ret.success(accountAwards);
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
