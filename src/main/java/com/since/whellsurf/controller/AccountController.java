package com.since.whellsurf.controller;

import com.since.whellsurf.entity.Account;
import com.since.whellsurf.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account")
public class AccountController {


    @Autowired
    AccountService accountService;

    @RequestMapping("/findAccount")
    @ResponseBody
    public void findAccount(){
        long l=1;
        Account testUser= accountService.findById(l);
    }
}
