package com.since.whellsurf.rep;

import com.since.whellsurf.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRep extends JpaRepository<Activity,Long> {

    /**
     * @author wyh
     */
    /**
      * 通过商家id和活动状态查找活动
      * @param shopId 商家id
      * @param status 状态
      * @return 活动信息
      */

     Activity findByShopIdAndStatus(Long shopId, Integer status);


    /**
     * @author wyh
     */
    /**
     * 通过活动id和
     * @param activityId 活动id
     * @param status 活动状态
     * @return 活动信息
     */
     Activity findByIdAndStatus(Long activityId,Integer status);


    /**findActivity
     * @param acId
     * @return Activity
     */
    Activity findActivityById(Long acId);



}
