package com.since.whellsurf.controller;

import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.since.whellsurf.ret.ActivityResult.ACTIVITYID_EXCEPTION;
import static com.since.whellsurf.ret.Result.SUCCESS;

/**
 * @author jayzh
 */

@Controller
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    ShopService shopService;
    @Autowired
    HttpServletRequest httpServletRequest;
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
        List<AccountAward> accountAwards=shopService.getActivityAwardsById(acId);
        ret=new Ret(SUCCESS,accountAwards);
        return ret;
    }



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
        int amount=shopService.getAmountJoinActivity(acId);
        ret=new Ret(SUCCESS,amount);
        return ret;
    }


    @RequestMapping("/findaccounts")
    @ResponseBody
    public Ret finish(){
        httpServletRequest.getSession().getAttribute("User");


        return null;
    }



}
