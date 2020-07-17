package com.since.whellsurf.service.impl;

import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.rep.AccountAwardRep;
import com.since.whellsurf.service.AccountAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jayzh
 */
@Service
public class AccountAwardServiceImpl implements AccountAwardService {
    @Autowired
    AccountAwardRep accountAwardRep;
    /**
     * @author jayzh
     */
    @Override
    public AccountAward redeem(Long activityId, Long AccountId) {
        AccountAward accountAward=accountAwardRep.findByActivityIdAndAccountId(activityId,AccountId);
        accountAward.setStatus(2);
        return accountAwardRep.save(accountAward);
    }

    @Override
    public AccountAward checkAccountAward(String awardCode, Long activity) {
        return accountAwardRep.findAccountAwardByCode(awardCode,activity);
    }

    @Override
    public void addAccountAward(AccountAward accountAward) {
        accountAwardRep.save(accountAward);
    }
}
