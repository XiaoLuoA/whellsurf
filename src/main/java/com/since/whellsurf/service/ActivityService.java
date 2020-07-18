package com.since.whellsurf.service;

import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;

import com.since.whellsurf.entity.AccountAward;


import java.util.List;

public interface ActivityService  {


    Boolean insertActivityAndAwardList(Activity activity);



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
     * @author jayzh
     */
    public List<AccountAward> getActivityAwardsById(Long activityId);


    /**
     * this abstract method aim to get the number of the people who participates in this activity by activity id;
     * @param activityId
     * @return number of people
     * @author jayzh
     */
    public int getAmountJoinActivity(Long activityId);





}
