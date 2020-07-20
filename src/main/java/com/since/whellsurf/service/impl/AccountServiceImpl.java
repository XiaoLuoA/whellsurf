package com.since.whellsurf.service.impl;

import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.rep.AccountRep;
import com.since.whellsurf.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王英豪111
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRep accountRep;


    @Override
    public Account save(Account acount) {
        return accountRep.save(acount);
    }

    @Override
    public Account findById(Long id) {
        Account testAccount= accountRep.findById(id).get();
        return testAccount;
    }

    @Override
    public Account addAccount(Account account) {
        return accountRep.save(account);
    }

    @Override
    public Account findByOpenId(String openId) {
        return accountRep.findByOpenid(openId);
    }
}
