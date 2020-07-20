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

    AccountAward redeem(Long activityId, Long AccountId);



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
     * @return Ret
     */
    String addAccountAward(AccountAward accountAward);


    /**
     * 通过openId查找中奖信息
     * @param openId openId
     * @return 中奖信息
     */
    AccountAward findByOpenId(String openId);


    /**
     * @author wyh
     */
    /**
     * 通过活动id和状态查找中奖信息
     * @param activityId 活动id
     * @param status 活动状态
     * @return 中奖信息集合
     */
    List<AccountAward> findAccountAwardByActivityIdAndStatus(Long activityId, Integer status);



    /**
     * @author wyh
     */
    /**
     * 通过活动id查找所有中奖信息
     * @param activityId 活动id
     * @return 中奖信息集合
     */
    List<AccountAward> findAccountAwardByActivityId(Long activityId);


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
