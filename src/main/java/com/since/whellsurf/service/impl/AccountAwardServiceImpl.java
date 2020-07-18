package com.since.whellsurf.service.impl;

import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.rep.AccountAwardRep;
import com.since.whellsurf.service.AccountAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.since.whellsurf.common.Status.REDEEM_STATUS_OK;

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
    /**this implement method aims to modify the status of account award
     * @param  activityId, AccountId
     * @return AccountAward which is altered
     */
    @Override
    public AccountAward redeem(Long activityId, Long AccountId) {
        AccountAward accountAward=accountAwardRep.findByActivityIdAndAccountId(activityId,AccountId);
        accountAward.setStatus(REDEEM_STATUS_OK);
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



    /**
     * this method aims to find AccountAward By ActivityId and status
     *
     * @param activityId
     * @param status
     * @return list of AccountAward
     * @author jayzh
     */
    @Override
    public List<AccountAward> findAccountAward(Long activityId, Integer status) {
        List<AccountAward> accountAwards=accountAwardRep.findByActivityIdAndStatus(activityId,status);
        return accountAwards;
    }

    /**
     * this method aims to hide some information which is useless
     *
     * @param accountAwards
     * @return list of AccountAward
     * @author jayzh
     */
    @Override
    public List<AccountAward> hideUselessInformation(List<AccountAward> accountAwards) {
        for (AccountAward ad:accountAwards) {
            ad.setOpenId(null);
            ad.setActivityId(null);
            ad.setAccountId(null);
        }
        return accountAwards;
    }

}
