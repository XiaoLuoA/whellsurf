package com.since.whellsurf.service;


import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Award;
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
     * 添加中奖信息
     * @param activityId 活动id
     * @param account 用户
     * @return
     */
    Award addAccountAward(Long activityId, Account account);

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



    /**
     * this abstract method aims to hide some information which is useless
     *
     * @param accountAwards
     * @return list of AccountAward
     * @author jayzh
     */
    public List<AccountAward> hideUselessInformation(List<AccountAward> accountAwards);

    Award getPrize(Long activityId);




    /**
     * @author wyh
     */
    /**
     * 通过用户id和活动id查找中奖信息
     * @param activityId 活动id
     * @param accountId 用户id
     * @return 中奖信息
     */
    AccountAward findByActivityIdAndAccountId(Long activityId,Long accountId);



    /**
     * 查找参加活动的人数
     * @param activityId 活动id
     * @return 人数
     */
     int findJoinActivityAmount(Long activityId);


    }
