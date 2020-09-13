package com.since.whellsurf.service.impl;


import com.since.whellsurf.common.Config;
import com.since.whellsurf.entity.*;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.rep.AccountAwardRep;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.rep.AwardRep;
import com.since.whellsurf.ret.ActivityResult;
import com.since.whellsurf.ret.AwardResult;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.service.AccountAwardService;
import com.since.whellsurf.service.AccountService;
import com.since.whellsurf.service.ActivityService;
import com.since.whellsurf.util.WXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Autowired
    AccountAwardService accountAwardService;

    @Autowired
    AccountService accountService;



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
        String wxUrl= WXUtil.genGetUserURL(Config.appId, Config.HOST+Config.ACTIVITY_INDEX+"/"+saveActivity.getId());
        System.out.println("创建活动的url "+wxUrl);
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

    @Override
    public Activity findById(Long id) {
        return activityRep.findById(id).get();
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




    /**this method aims to end the activity
     * @return Object of activity
     * @author jayzh
     */
    @Override
    public Activity finish(Long shopId) {
        Activity activity = activityRep.findByShopIdAndStatus(shopId,ACTIVITY_VALID);
        activity.setStatus(ACTIVITY_END);
        return activityRep.save(activity);
    }

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
                System.out.println(activity);

                int probability = activity.getAwards().stream()
                        .mapToInt(award->award.getProbability()).sum();

                System.out.println(probability);
                if (probability == AWARD_PROBABILITY) {
                    return Ret.success();
                } else {
                    return Ret.error(AwardResult.AWARD_PROBABILITY_WRONG);
                }
            }
        }
        return Ret.error(ActivityResult.ACTIVITY_IS_RUNNING);
    }


    private Account shopToAccount(Shop shop){
        Account account = new Account();
        account.setAddress(shop.getAddress());
        account.setGender(shop.getGender());
        account.setHeadImgUrl(shop.getHeadImgUrl());
        account.setNickname(shop.getNickname());
        account.setOpenid(shop.getOpenid());
        account.setStatus(ACCOUNT_NORMAL);
        return account;
    }

    @Override
    public Ret shopGetPrize(Long activityId, Shop shop) {
        Activity activity = findRunningActivity(shop.getId());
        if (activity.getId().equals(activityId)){
            Award award = accountAwardService.getPrize(activityId);
            return Ret.success(award);
        }else{
            Account account = accountService.findByOpenId(shop.getOpenid());
            if (account==null){
                account = shopToAccount(shop);
                account = accountService.save(account);
            }
            return userGetPrize(activityId, account);
        }
    }


    @Override
    public Ret userGetPrize(Long activityId, Account account) {
        AccountAward accountAward = accountAwardService.findByActivityIdAndAccountId(activityId,account.getId());
        if (accountAward != null){
            return Ret.error(AwardResult.GET_AWARD_REPEAT);
        }else {
            Long shopId = activityRep.findById(activityId).get().getShopId();
            Award award = accountAwardService.getPrizeAndSave(activityId,account,shopId);
            return Ret.success(award);
        }
    }


    @Override
    public Activity findByActivityIdAndStatus(Long activityId, Integer status) {
        return activityRep.findByIdAndStatus(activityId, status);
    }

}
