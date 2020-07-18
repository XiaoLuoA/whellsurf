package com.since.whellsurf.service;

import com.since.whellsurf.entity.Activity;

public interface ActivityService  {


    Boolean createActivity();
    Boolean shopIsActivitty();

    /**
     * 通过商家id和活动状态查找活动
     * @param shopId 商家id
     * @return 活动信息
     */
    Activity findByShopIdAndStatus(Long shopId);


}
