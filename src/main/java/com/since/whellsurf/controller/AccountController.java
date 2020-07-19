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
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.service.AccountAwardService;
import com.since.whellsurf.service.AccountService;
import com.since.whellsurf.service.ActivityService;
import com.since.whellsurf.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/account")
public class AccountController {


    @Autowired
    AccountService accountService;

    @Autowired
    private AccountAwardService accountAwardService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/findAccount")
    @ResponseBody
    public void findAccount(){
        long l=1;
        Account testUser= accountService.findById(l);
        System.out.println(testUser.getAddress());
        System.out.println();
    }


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
          Activity shopActivity = activityService.findValidActivityByShopId(shop.getId());
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

    /*    抽奖接口，出错了，我先注释了
    @RequestMapping("/drawPrize")
    @ResponseBody
    public Ret drawPrize(@RequestParam String activityId){
        /*
        Long acId = Long.valueOf(activityId);
        Activity activity = activityService.findByActivityIdAndStatus(acId, Status.ACTIVITY_VALID);
        if (activity == null){
            return Ret.error(ActivityResult.ACTIVITY_IS_OUTDATED);
        }
        Account account = (Account)request.getSession().getAttribute(SessionKey.LOGIN_USER);
        Shop shop = (Shop)request.getSession().getAttribute(SessionKey.LOGIN_SHOP);

        if (account == null || shop == null){
            return Ret.error(AccountResult.ACCOUNT_NOT_LOGIN);
        }
            //先判断用户
            AccountAward accountAward = accountAwardService.findByOpenId(account.getOpenid());
            if (accountAward != null){
                return Ret.error(AwardResult.GET_AWARD_REPEAT);
            }else {
                //用户可以抽奖
                AccountAward accountAward1 = new AccountAward();
                accountAward1.setOpenid(account.getOpenid());
                accountAward1.setAccountId(account.getId());
                accountAward1.setHeadImgUrl(account.getHeadImgUrl());
                accountAward1.setActivityId(acId);
                String str = accountAwardService.addAccountAward(accountAward1);
                if (str == "1") {
                    return Ret.error(AwardResult.GET_AWARD_REPEAT);
                } else {
                    return Ret.success(str);
                }

            }

            Activity shopActivity = activityService.findValidActivityByShopId(shop.getId());
            //如果商家的活动id和此活动id不匹配，说明商家作为一个用户抽别的商家的奖
            if (shopActivity.getId().equals(activityId) ){
                //商家抽奖自己的活动
                AccountAward accountAward1 = new AccountAward();
                accountAward1.setOpenid(shop.getOpenid());
                accountAward1.setAccountId(shop.getId());
                accountAward1.setHeadImgUrl(shop.getHeadImgUrl());
                accountAward1.setActivityId(acId);
                String str = accountAwardService.addAccountAward(accountAward1);
                if (str == "1"){
                    return Ret.error(AwardResult.GET_AWARD_REPEAT);
                } else {
                    return Ret.success(str);
                }

            }

        //商家作为用户可以抽奖别的活动
             AccountAward accountAward_shop = accountAwardService.findByOpenId(account.getOpenid());
            if (accountAward_shop != null){
                return Ret.error(AwardResult.GET_AWARD_REPEAT);
            }
          AccountAward accountAward1 = new AccountAward();
          accountAward1.setOpenid(shop.getOpenid());
          accountAward1.setAccountId(shop.getId());
          accountAward1.setHeadImgUrl(shop.getHeadImgUrl());
          accountAward1.setActivityId(acId);
          String str = accountAwardService.addAccountAward(accountAward1);
          if (str == "1"){
             return Ret.error(AwardResult.GET_AWARD_REPEAT);
          } else {
            return Ret.success(str);
         }
    }

*/
}
