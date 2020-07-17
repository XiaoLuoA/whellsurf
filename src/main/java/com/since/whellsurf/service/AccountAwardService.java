package com.since.whellsurf.service;


import com.since.whellsurf.entity.AccountAward;

/**
 * @author 王英豪111
 */
public interface AccountAwardService {


    /**
     * @param awardCode 兑奖码
     * @param activity
     * @return
     */
    AccountAward checkAccountAward(String awardCode,Long activity);


    void addAccountAward(AccountAward accountAward);


}
