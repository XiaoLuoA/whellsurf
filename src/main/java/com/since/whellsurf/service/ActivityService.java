package com.since.whellsurf.service;

import com.since.whellsurf.entity.Activity;

public interface ActivityService  {


    Boolean createActivity();
    Boolean shopIsActivitty();
    /**
     * @author jayzh
     */
    Activity findExitActivity(Long shopId, Integer status);
    /**
     * @author jayzh
     */
    Activity save(Activity activity);
    /**
     * @author jayzh
     */
    Activity finish(Activity activity);

}
