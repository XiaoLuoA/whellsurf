package com.since.whellsurf.service;


import com.since.whellsurf.entity.Activity;

import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Award;


import java.util.List;

public interface ActivityService  {


    Boolean createActivity();

    // todo  1 先插入activity 2在插入award

    /**
     * @author drj
     */

    Long insertActivity(Activity activity,Long shopId);







    Activity findValidActivityByShopId(Long shopId);

    Activity findByActivityIdAndStatus(Long activityId, Integer status);

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
     * 通过openId查询商家信息
     * @param openId 商家用户openId
     * @return 商家信息
     */

}
