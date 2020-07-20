package com.since.whellsurf.service;

import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.ret.Ret;

/**
 * @author luoxinyuan,drj
 */
public interface ActivityService  {

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
    Activity findRunningActivity(Long shopId);


    /**
     * 结束活动，修改状态
     * @param shopId 商户id
     * @return 活动信息
     */
     Activity finish(Long shopId);




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
