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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private HttpServletRequest request;

    @RequestMapping("/findAccount")
    @ResponseBody
    public void findAccount(){
        long l=1;
        Account testUser= accountService.findById(l);
        System.out.println(testUser.getAddress());
        System.out.println();
    }


    @RequestMapping("/drawPrize")
    @ResponseBody
    public Ret drawPrize(Long activityId){
        Activity activity = activityService.findByActivityIdAndStatus(activityId, Status.Activity_Valid);
        if (activity == null){
            return Ret.error(ActivityResult.ACTIVITY_IS_OUTDATED);
        }
        Account account = (Account)request.getSession().getAttribute(SessionKey.LOGIN_USER);
        Shop shop = (Shop)request.getSession().getAttribute(SessionKey.LOGIN_SHOP);
        AccountAward accountAward = new AccountAward();
        if (account != null){
            accountAward.setOpenId(account.getOpenId());
            accountAward.setAccountId(account.getId());
            accountAward.setHeadImgUrl(account.getHeadImgUrl());
            accountAward.setActivityId(activityId);
            return accountAwardService.addAccountAward(accountAward);
        }
        if (shop != null){
            accountAward.setOpenId(shop.getOpenId());
            accountAward.setAccountId(shop.getId());
            accountAward.setHeadImgUrl(shop.getHeadImgUrl());
            accountAward.setActivityId(activityId);
            return accountAwardService.addAccountAward(accountAward);
           
        }

        return Ret.error(AccountResult.ACCOUNT_NOT_LOGIN);
    }


}
