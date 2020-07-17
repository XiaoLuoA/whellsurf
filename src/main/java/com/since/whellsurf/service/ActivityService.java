package com.since.whellsurf.service;


import com.since.whellsurf.entity.Activity;

public interface ActivityService  {

    Boolean createActivity();
    Boolean insertActivityAndAwardList(Activity activity);

}
