package com.since.whellsurf.controller;

import com.since.whellsurf.common.Config;
import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.rep.AccountRep;
import com.since.whellsurf.ret.*;
import com.since.whellsurf.service.AccountAwardService;
import com.since.whellsurf.service.AccountService;
import com.since.whellsurf.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * @author luoxinyuan，wangyinghao
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private AccountAwardService accountAwardService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/drawPrize")
    @ResponseBody
    public Ret drawPrize(@RequestParam Long activityId){
        Account account = (Account) request.getSession().getAttribute(SessionKey.LOGIN_USER);
        if (account == null){
            Shop shop = (Shop)request.getSession().getAttribute(SessionKey.LOGIN_SHOP);
            if (shop==null){
                return Ret.noPermission(Config.USER_NO_PERMISSION_REDIRECT);
            }
            return activityService.shopGetPrize(activityId,shop);
        }
        return activityService.userGetPrize(activityId,account);
    }


    @RequestMapping("/checkAccountAward")
    @ResponseBody
    public Ret checkAward(@RequestParam Long activityId){
        Account account = null;
          account = (Account) request.getSession().getAttribute(SessionKey.LOGIN_USER);
         if (account == null){
             return Ret.error(AccountResult.ACCOUNT_NOT_LOGIN);
         }

        AccountAward accountAward = accountAwardService.findByActivityIdAndAccountId(activityId,account.getId());
         if (accountAward != null){
             return Ret.error(AwardResult.GET_AWARD_REPEAT);
         }
         return Ret.success();

    }

}
