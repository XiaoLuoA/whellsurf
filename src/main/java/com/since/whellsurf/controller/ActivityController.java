package com.since.whellsurf.controller;

import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Award;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.ret.AccountResult;
import com.since.whellsurf.ret.ActivityResult;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.service.AccountService;
import com.since.whellsurf.service.ActivityService;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;
    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping("/shopIsActivitty")
    @ResponseBody
    public Ret createActivitty(Activity activity) {
        Shop shop  = (Shop) httpServletRequest.getSession().getAttribute(SessionKey.LOGIN_SHOP);
        if(shop==null){
            return Ret.error("没有此商户");
            }
        else{
            //判断当前商户是否有activity
               Activity isActivity = activityService.find();//调用大师isActivity方法
               if (isActivity==null) {
                   //判断参数合法性
                   List<Award> awardList = activity.getAwards();
                   int resultProbability = 0;

                   for (Award award : awardList) {
                       resultProbability = award.getProbability() + resultProbability;
                   }
                       if (resultProbability == 100) {
                           // 插入两个表
                           activityService.insertActivityAndAwardList(activity);
                           //to do 生成二维码及活动方案
                           return Ret.success("活动创建成功");
                       }
                       else {
                           return Ret.error("插入失败");

                       }

                   return Ret.error("奖品概率设置有问题");

               }
               else{
                       return Ret.error("已经有活动了"); // 跳转结果页
                   }

            }









    }
}
