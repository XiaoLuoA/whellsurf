package com.since.whellsurf.service;

import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.ret.Ret;

public interface ActivityService  {

    AccountAward findByActivityIdAndAccountId(Long activityId, Long AccountId);

    /**
     * @author drj
     * 对处理之后的activity和awardList进行插入
     * @param activity,shopId
     * @return true
     */
    Activity insertActivity(Activity activity, Long shopId);



    /**this abstract method aims to find the activity which has not been closed
     * @param shopId
     * @return Object of activity which has not been closed
     * @author jayzh
     */
   public Activity findRunningActivity(Long shopId);


    /**
     * 结束活动，修改状态
     * @param activity 活动信息
     * @param shopId 商户id
     * @return 活动信息
     */
    public Activity finish(Activity activity,Long shopId);


    /**
     * this abstract method aim to get the number of the people who participates in this activity by activity id;
     * @param activityId
     * @return number of people
     * @author jayzh
     */
    int getAmountJoinActivity(Long activityId);


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
     * 商家是否能够创建活动
     * @param shop 登录的商户
     * @param activity 要创建的活动
     * @return 包含了是否能创建成功信息的Ret
     * @author luoxinyuan
     */
    Ret canCreateActivity(Shop shop, Activity activity);


    Ret shopGetPrize(Long activityId, Shop shop);

    Ret userGetPrize(Long activityId, Account account);


}
