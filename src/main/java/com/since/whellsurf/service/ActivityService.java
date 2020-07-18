package com.since.whellsurf.service;

import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Award;


import java.util.List;

public interface ActivityService  {

    /**
     * @author drj
     * 对处理之后的activity和awardList进行插入
     * @param activity,shopId
     * @return true
     */
    Long insertActivity(Activity activity,Long shopId);



    /**this abstract method aims to find the activity which has not been closed
     * @param shopId
     * @param status
     * @return Object of activity which has not been closed
     * @author jayzh
     */
   public Activity findExitActivity(Long shopId, Integer status);



    /**this abstract method aims to save the activity
     * @param activity
     * @return Object of activity
     * @author jayzh
     */
    public Activity save(Activity activity);


    /**this abstract method aims to end the activity
     * @param activity
     * @return Object of activity
     * @author jayzh
     */
    public Activity finish(Activity activity);


    /**
     * this abstract method aim to get the list of accountAward by activity id;
     * @param activityId
     * @return List<AccountAward>
     *
     */
    public List<AccountAward> getActivityAwardsById(Long activityId);


    /**
     * this abstract method aim to get the number of the people who participates in this activity by activity id;
     * @param activityId
     * @return number of people
     * @author jayzh
     */
    public int getAmountJoinActivity(Long activityId);




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


    /**
     * @author wyh
     */
    /**
     /**
     * 通过商家id和活动状态查找活动
     * @param shopId 商家id
     * @return 活动信息
     */
    Activity findValidActivityByShopId(Long shopId);



}
