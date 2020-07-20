package com.since.whellsurf.service.impl;


import com.since.whellsurf.common.Config;
import com.since.whellsurf.entity.*;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.rep.AwardRep;
import com.since.whellsurf.ret.ActivityResult;
import com.since.whellsurf.ret.AwardResult;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.service.ActivityService;
import com.since.whellsurf.util.WXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static com.since.whellsurf.common.Status.*;

/**
 * @author  drj
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRep activityRep;

    @Autowired
    AwardRep awardRep;

//    @Autowired
//    HttpServletRequest httpServletRequest;

    /**
     * @author drj
     * 对处理之后的activity和awardList进行插入
     * @param activity
     * @return true
     */
    @Transactional
    @Override
    public Activity insertActivity(Activity activity, Long id) {
        activity.setShopId(id);
        activity.setStatus(ACTIVITY_RUNNING);
        Activity saveActivity = activityRep.save(activity);
        String wxUrl= WXUtil.genGetUserURL(Config.appId, Config.HOST+Config.ACTIVITY_INDEX+"?activityId="+saveActivity.getId());
        saveActivity.setImage(wxUrl);
        activity.getAwards().forEach(
                award -> {
                    award.setActivityId(saveActivity.getId());
                    award.setStatus(AWARD_NORMAL);
                }
        );
        awardRep.saveAll(activity.getAwards());
        return saveActivity;
    }




    /**this abstract method aims to find the activity which has not been closed
     * @param shopId
     * @return Object of activity which has not been closed
     * @author jayzh
     */
    @Override
    public Activity findRunningActivity(Long shopId){
        return activityRep.findByShopIdAndStatus(shopId, ACTIVITY_RUNNING);
    }


    /**this  method aims to save the activity
     * @param activity
     * @return Object of activity
     * @author jayzh
     */
    @Override
    public Activity save(Activity activity) {
        return activityRep.save(activity);
    }

    /**this method aims to end the activity
     * @param activity
     * @return Object of activity
     * @author jayzh
     */
    @Override
    public Activity finish(Activity activity) {
        activity.setStatus(ACTIVITY_END);
        activity=save(activity);
        return activity;
    }

//    @Override
//    public Activity findValidActivityByShopId(Long shopId) {
//        return activityRep.findByShopIdAndStatus(shopId, Status.ACTIVITY_VALID).getId();
//    }

    /**
     * @author luoxinyuan
     * @param shop
     * @return
     */
    @Override
    public Ret canCreateActivity(Shop shop,Activity activity) {
        Activity runningActivity = findRunningActivity(activity.getShopId());
        if (runningActivity == null) {
            int awardNumber = activity.getAwards().size();
            if (awardNumber > AWARD_MAX_NUMBER){
                return Ret.error(AwardResult.AWARD_NUMBER_EXCEED);
            }
            else{
                int probability = activity.getAwards().stream()
                        .mapToInt(award->award.getProbability()).sum();
                //判断参数合法性
                if (probability == PROBABILITY) {
                    return Ret.success();
                } else {
                    return Ret.error(AwardResult.AWARD_PROBABILITY_WRONG);
                }
            }
        }
        return Ret.error(ActivityResult.ACTIVITY_IS_RUNNING);
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
