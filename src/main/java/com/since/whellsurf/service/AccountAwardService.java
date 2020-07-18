package com.since.whellsurf.service;

import com.since.whellsurf.entity.AccountAward;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jayzh
 */
@Service
public interface AccountAwardService {

    /**this abstract method aims to modify the status of account award
     * @param activityId
     * @param AccountId
     * @return AccountAward which is altered
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


    /**this method aims to find AccountAward By ActivityId and status
     * @param activityId
     * @param status
     * @return list of AccountAward
     * @author jayzh
     */
    public List<AccountAward> findAccountAward(Long activityId, Integer status);


    /**
     * this abstract method aims to hide some information which is useless
     *
     * @param accountAwards
     * @return list of AccountAward
     * @author jayzh
     */
    public List<AccountAward> hideUselessInformation(List<AccountAward> accountAwards);


    }





