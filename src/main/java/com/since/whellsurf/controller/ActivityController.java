package com.since.whellsurf.controller;

import com.since.whellsurf.common.Config;
import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.common.Status;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.ret.*;
import com.since.whellsurf.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import static com.since.whellsurf.ret.ActivityResult.ACTIVITY_NOT_FIND;

/**
 * @author luoxinyuan,dangrongjin
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping("/test")
    @ResponseBody
    public Ret test(@RequestBody Activity activity){
        System.out.println(activity);
        return Ret.error();
    }

    @RequestMapping("/addActivity")
    @ResponseBody
    public Ret createActivity(@RequestBody Activity activity) {
        Shop shop = (Shop) httpServletRequest.getSession().getAttribute(SessionKey.LOGIN_SHOP);
        if (shop == null){
            return Ret.noPermission(Config.SHOP_NO_PERMISSION_REDIRECT);
        }
        Ret ret = activityService.canCreateActivity(shop,activity);
        if (ret.isOk()){
            Activity act = activityService.insertActivity(activity,shop.getId());
            return Ret.success(act.getImage());
        } else{
            return ret;
        }
    }


    @RequestMapping("/activityInfo")
    @ResponseBody
    public Ret activityInfo(){
        Shop shop = (Shop) httpServletRequest.getSession().getAttribute(SessionKey.LOGIN_SHOP);
        Activity act = activityService.findRunningActivity(shop.getId());
        act.setParticipates(act.getAccountAwards().size());
        if (null==act){
            return Ret.error(ACTIVITY_NOT_FIND);
        }
        return Ret.success(act);
    }
}

