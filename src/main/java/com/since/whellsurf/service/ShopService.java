package com.since.whellsurf.service;

import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jayzh
 */
@Service
public interface ShopService {
    /**
     * this method aim to get the list of accountAward by activity id;
     * @param activityId
     * @return List<AccountAward>
     *
     */
    public List<AccountAward> getActivityAwardsById(Long activityId);

    /**
     * this method aim to get the number of the people who participates in this activity by activity id;
     * @param activityId
     * @return number of people
     */
    public int getAmountJoinActivity(Long activityId);

    /**
     * 通过openId查询商家信息
     * @param openId 商家用户openId
     * @return 商家信息
     */
    Shop findByOpenId(String openId);
}
