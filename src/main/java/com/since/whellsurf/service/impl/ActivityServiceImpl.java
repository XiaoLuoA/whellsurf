package com.since.whellsurf.service.impl;


import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * @author  drj
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityRep activityRep;


    /**
     * @author drj
     * 创建活动  todo: 1.userIsShop 2.isActivity 3.判断参数合法性 4.Insert 两个表
     */
    @Override
    public Boolean createActivity() {
        return null;
    }


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
}