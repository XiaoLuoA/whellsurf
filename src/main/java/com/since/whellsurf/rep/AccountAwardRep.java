package com.since.whellsurf.rep;

import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 王英豪111
 */
@Repository
public interface  AccountAwardRep extends JpaRepository<AccountAward,Long> {

    AccountAward findByActivityIdAndAccountId(Long activityId,Long AccountId);

    /**
     * @author wyh
     */
    /**
    /**
     * 通过活动id和兑奖码核对兑奖信息
     * @param code 兑奖码
     * @param activityId 活动id
     * @return AccountAward 返回中奖表
     */

    AccountAward findByAwardCodeAndActivityId(String code,Long activityId);


    /**
     * @author wyh
     */
    /**
    /**
     * 保存中奖信息
     * @param accountAward 中奖信息
     * @return 中奖信息集合
     */
    @Override
    AccountAward save(AccountAward accountAward);



    /**
     * @author wyh
     */
    /**
     * 通过openId查找中奖信息
     * @param openId 用户openId
     * @return 中奖信息
     */
    AccountAward findByOpenid(String openId);


    /**
     * @author wyh
     */
    /**
     * 通过活动id和状态查找中奖信息
     * @param activityId 活动id
     * @param status 活动状态
     * @return 中奖信息集合
     */
    List<AccountAward> findAllByActivityIdAndStatus(Long activityId, Integer status);



    /**
     * @author wyh
     */
    /**
     * 通过活动id查找所有中奖信息
     * @param activityId 活动id
     * @return 中奖信息集合
     */
    List<AccountAward> findAllByActivityId(Long activityId);


}
