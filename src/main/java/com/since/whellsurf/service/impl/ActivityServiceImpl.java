package com.since.whellsurf.service.impl;


import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.rep.ShopRep;
import com.since.whellsurf.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author  drj
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ShopRep shopRep;
    @Autowired
    ActivityRep activityRep;
    @Autowired
    HttpServletRequest httpServletRequest;


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
     * 判断商户的一个活动是否正在执行
     */
    @Override
    public Boolean shopIsActivitty() {
        Object ob = httpServletRequest.getSession().getAttribute(SessionKey.LOGIN_USER);
        Shop s = new Shop();
        if (ob.getClass().isInstance(s.getClass())) {
            return null;
        } else {
            return null;
        }

    }




}
