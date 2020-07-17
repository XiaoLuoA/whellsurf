package com.since.whellsurf.controller;

import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.Users;
import com.since.whellsurf.service.TestServiceImpl;
import com.since.whellsurf.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author luoxinyuan
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestServiceImpl testService;

    @ResponseBody
    @PostMapping("/multi")
    public Account mutiBody(@RequestBody Account account){
        Users[] users = new Users[1000];
        List<Users> usersList = Arrays.stream(users).map(users1 -> {
            users1 = new Users();
            users1.setName(RandomUtil.genRandomCode(4));
            return users1;
        }).collect(Collectors.toList());


        long start1 = System.currentTimeMillis();
        testService.saveAll(usersList);
        long start2 = System.currentTimeMillis();
        System.out.println("simple insert "+ (start2-start1));


        usersList = Arrays.stream(users).map(users1 -> {
            users1 = new Users();
            users1.setName(RandomUtil.genRandomCode(4));
            return users1;
        }).collect(Collectors.toList());

        long start3 = System.currentTimeMillis();
        testService.saveAllTrans(usersList);
        long start4 = System.currentTimeMillis();
        System.out.println("trans insert "+ (start4-start3));
        return account;
    }


}
