package com.since.whellsurf.controller;

import com.alibaba.fastjson.JSON;
import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Award;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.resolve.ObjRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author luoxinyuan
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ActivityRep activityRep;

    @ResponseBody
    @PostMapping("/multi")
    public Activity mutiBody(@RequestBody Activity account){
        int sum = account.getAwards().stream().mapToInt(a->a.getProbability()).sum();
        System.out.println(sum);
        System.out.println(account);
        return account;
    }


    @ResponseBody
    @PostMapping("/mul")
    public Activity mul(@ObjRequestBody List<Award> awards, @ObjRequestBody Activity activity){
        System.out.println(awards);
        System.out.println(activity);
        return activity;
    }

    @ResponseBody
    @PostMapping("/mul2")
    public Activity mul(@ObjRequestBody Award award, @ObjRequestBody Activity activity){
        System.out.println(award);
        System.out.println(activity);
        return activity;
    }

//    @ResponseBody
//    @PostMapping("/act")
//    public Account act(@RequestBody Account account){
//        List<Activity> r = activityRep.findByShopIdAndStatus(1L,1);
//
//        r.getAwards().forEach(award -> {award.setName(null);});
//        System.out.println(JSON.toJSONString(r));
//        return account;
//    }


}
