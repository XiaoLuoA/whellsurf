package com.since.whellsurf.rep;

import com.since.whellsurf.entity.AccountAward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface  AccountAwardRep extends JpaRepository<AccountAward,Long> {
    AccountAward findByActivityIdAndAccountId(Long activityId,Long AccountId);
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
}
