package com.since.whellsurf.service.impl;

import com.since.whellsurf.entity.Account;
import com.since.whellsurf.rep.AccountRep;
import com.since.whellsurf.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRep accountRep;
    @Override
    public Account findById(Long id) {
        Account testAccount= accountRep.findById(id).get();
        return testAccount;
    }
}
