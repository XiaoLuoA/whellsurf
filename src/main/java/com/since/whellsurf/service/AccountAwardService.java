package com.since.whellsurf.service;


import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.ret.Ret;

/**
 * @author 王英豪111
 */
public interface AccountAwardService {


    /**
     * 核查中奖信息
     * @param awardCode 兑奖码
     * @param activityId 活动id
     * @return 中奖信息
     */
    AccountAward checkAccountAward(String awardCode,Long activityId);


    /**
     * 添加中奖信息
     * @param accountAward 中奖信息
     * @return 中奖信息
     */
    Ret addAccountAward(AccountAward accountAward);


}
