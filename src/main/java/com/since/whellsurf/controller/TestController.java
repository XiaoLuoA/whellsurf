package com.since.whellsurf.controller;

import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Users;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public Account mutiBody(@RequestBody Account account){
        Users[] users = new Users[1000];
        List<Users> usersList = Arrays.stream(users).map(users1 -> {
            users1 = new Users();
            users1.setName(RandomUtil.genRandomCode(4));
            return users1;
        }).collect(Collectors.toList());
        return account;
    }

//    @ResponseBody
//    @PostMapping("/act")
//    public Account act(@RequestBody Account account){
//        Activity r = activityRep.findByShopIdAndStatus(1L,1);
//        System.out.println(r);
//        return account;
//    }


}
