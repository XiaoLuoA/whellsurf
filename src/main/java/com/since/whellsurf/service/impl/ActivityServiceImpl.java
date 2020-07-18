package com.since.whellsurf.service.impl;


import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.common.Status;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.rep.ShopRep;
import com.since.whellsurf.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author  drj
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRep activityRep;
    @Autowired
    HttpServletRequest httpServletRequest;






    /**
     * @author drj
     * 对处理之后的activity和awardList进行插入
     * @param activity
     * @return true
     */
    @Transactional
    @Override
    public Boolean insertActivityAndAwardList(Activity activity) {
         activityRep.save(activity);
         System.out.println(activity.getAwards());
        return true;
    }

    /**
     * @author jayzh
     */

    @Override
    public Activity findExitActivity(Long shopId, Integer status){
        Activity activity=activityRep.findByShopIdAndStatus(shopId,status);
        return activity;
    }
    /**
     * @author jayzh
     */

    @Override
    public Activity save(Activity activity) {
        return activityRep.save(activity);
    }
    /**
     * @author jayzh
     */

    @Override
    public Activity finish(Activity activity) {
        activity.setStatus(2);
        activity=save(activity);
        return activity;
    }

    @Override
    public Activity findValidActivityByShopId(Long shopId) {
        return activityRep.findByShopIdAndStatus(shopId, Status.ACTIVITY_VALID);
    }

    @Override
    public Activity findByActivityIdAndStatus(Long activityId, Integer status) {
        return activityRep.findByIdAndStatus(activityId, status);
    }


    /**
     * this method aim to get the list of accountAward by activity id;
     *
     * @param activityId
     * @return List<AccountAward>
     */
    @Override
    public List<AccountAward> getActivityAwardsById(Long activityId) {
        Activity activity = activityRep.findActivityById(activityId);
        List<AccountAward> accountAwards=activity.getAccountAwards();
        return accountAwards;
    }

    /**
     * this method aim to get the number of the people who participates in this activity by activity id;
     *
     * @param activityId
     * @return number of people
     */
    @Override
    public int getAmountJoinActivity(Long activityId) {
        return getActivityAwardsById(activityId).size();
    }

}