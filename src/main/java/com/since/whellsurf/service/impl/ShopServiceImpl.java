package com.since.whellsurf.service.impl;


import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jayzh
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ActivityRep activityRepository;
    /**
     * this method aim to get the list of accountAward by activity id;
     *
     * @param activityId
     * @return List<AccountAward>
     */
    @Override
    public List<AccountAward> getActivityAwardsById(Long activityId) {
        Activity activity = activityRepository.findActivityById(activityId);
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
