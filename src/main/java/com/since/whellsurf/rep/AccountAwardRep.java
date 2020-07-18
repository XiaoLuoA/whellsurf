package com.since.whellsurf.rep;

import com.since.whellsurf.entity.AccountAward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王英豪111
 */
@Repository
public interface  AccountAwardRep extends JpaRepository<AccountAward,Long> {


    /**
     * 通过活动id和兑奖码核对兑奖信息
     * @param code 兑奖码
     * @param activityId 活动id
     * @return AccountAward 返回中奖表
     */
    @Query(value = "select * from account_award where activity_id = ?1 and award_code = ?2", nativeQuery = true)
    AccountAward findAccountAwardByCode(String code,Long activityId);


    /**
     * 保存中奖信息
     * @param accountAward 中奖信息
     * @return 中奖信息集合
     */
    @Override
    AccountAward save(AccountAward accountAward);


    /**
     * 通过openId查找
     * @return
     */
    AccountAward findByOpenId(String openIdss);
}
