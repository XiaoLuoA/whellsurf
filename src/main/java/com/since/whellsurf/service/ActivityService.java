package com.since.whellsurf.service;

import com.since.whellsurf.entity.Activity;


import com.since.whellsurf.entity.Activity;

import com.since.whellsurf.entity.AccountAward;


import java.util.List;

public interface ActivityService  {


    Boolean createActivity();
    Boolean insertActivityAndAwardList(Activity activity);
    /**
     * @author jayzh
     */
    Activity findExitActivity(Long shopId, Integer status);
    /**
     * @author jayzh
     */
    Activity save(Activity activity);
    /**
     * @author jayzh
     */
    Activity finish(Activity activity);
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
     * @author wyh
     */
    /**
    /**
     * 通过商家id和活动状态查找活动
     * @param shopId 商家id
     * @return 活动信息
     */
    Activity findByShopIdAndStatus(Long shopId);


    /**
     * @author wyh
     */
    /**
     * 通过活动id和状态查询活动
     * @param activityId 活动id
     * @param status 活动状态
     * @return 活动信息
     */
    Activity findByActivityIdAndStatus(Long activityId,Integer status);
}
