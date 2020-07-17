package com.since.whellsurf.service;

import com.since.whellsurf.entity.AccountAward;
import org.springframework.stereotype.Service;

/**
 * @author jayzh
 */
@Service
public interface AccountAwardService {
    /**redeem
     * @param activityId
     * @param AccountId
     * @return
     */

    /**
     * @author jayzh
     */

    public AccountAward redeem(Long activityId, Long AccountId);



    /**
     * @param awardCode 兑奖码
     * @param activity
     * @return
     */
    AccountAward checkAccountAward(String awardCode,Long activity);


    void addAccountAward(AccountAward accountAward);
}
