package com.since.whellsurf.service;


import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.ret.Ret;
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
     * @author wyh
     */

    /**
     * 核查中奖信息
     * @param awardCode 兑奖码
     * @param activityId 活动id
     * @return 中奖信息
     */
    AccountAward checkAccountAward(String awardCode,Long activityId);


    /**
     * @author wyh
     */

    Ret addAccountAward(AccountAward accountAward);


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
