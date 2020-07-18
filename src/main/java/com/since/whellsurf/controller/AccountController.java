package com.since.whellsurf.controller;

import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.ret.AccountResult;
import com.since.whellsurf.ret.AwardResult;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.service.AccountAwardService;
import com.since.whellsurf.service.AccountService;
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
        Account account = (Account)request.getSession().getAttribute(SessionKey.LOGIN_USER);
        Shop shop = (Shop)request.getSession().getAttribute(SessionKey.LOGIN_SHOP);
        AccountAward accountAward = new AccountAward();
        if (account != null){
            accountAward.setOpenId(account.getOpenId());
            accountAward.setAccountId(account.getId());
            accountAward.setHeadImgUrl(account.getHeadImgUrl());
            accountAward.setActivityId(activityId);
            AccountAward accountAward1 = accountAwardService.addAccountAward(accountAward);
            if (accountAward1 == null){
                return Ret.error(AwardResult.GET_AWARD_FAIL);
            }
            return Ret.success(accountAward1.getAwardCode());
        }
        if (shop != null){
            accountAward.setOpenId(shop.getOpenId());
            accountAward.setAccountId(shop.getId());
            accountAward.setHeadImgUrl(shop.getHeadImgUrl());
            accountAward.setActivityId(activityId);
            AccountAward accountAward2 = accountAwardService.addAccountAward(accountAward);
            if (accountAward2 == null){
                return Ret.error(AwardResult.GET_AWARD_FAIL);
            }
            return Ret.success(accountAward2.getAwardCode());
        }

        return Ret.error(AccountResult.ACCOUNT_NOT_LOGIN);
    }


}
