package com.since.whellsurf.service.impl;

import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.rep.AccountAwardRep;
import com.since.whellsurf.service.AccountAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王英豪111
 */
@Service
public class AccountAwardServiceImpl implements AccountAwardService {

    @Autowired
    private AccountAwardRep accountAwardRep;

    @Override
    public AccountAward checkAccountAward(String awardCode, Long activity) {
        return accountAwardRep.findAccountAwardByCode(awardCode,activity);
    }

    @Override
    public void addAccountAward(AccountAward accountAward) {
        accountAwardRep.save(accountAward);
    }
}
