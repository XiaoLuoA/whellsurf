package com.since.whellsurf.service;


import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.ret.Ret;
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

    /**
     * 添加中奖信息
     * @param accountAward 中奖信息
     * @return 中奖信息
     */
    Ret addAccountAward(AccountAward accountAward);


}
