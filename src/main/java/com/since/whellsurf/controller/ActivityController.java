package com.since.whellsurf.controller;

import com.since.whellsurf.common.Config;
import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.common.Status;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Award;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.ret.*;
import com.since.whellsurf.service.ActivityService;
import com.since.whellsurf.service.AwardService;
import com.since.whellsurf.util.WXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.since.whellsurf.common.Status.AWARD_MAX_NUMBER;
import static com.since.whellsurf.common.Status.PROBABILITY;
import static com.since.whellsurf.ret.ActivityResult.ACTIVITY_NOT_FIND;
import static com.since.whellsurf.ret.Result.SUCCESS;

@Controller
@RequestMapping("/activity")

public class ActivityController {
    @Autowired
    ActivityService activityService;
    @Autowired
    AwardService awardService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    ActivityRep activityRep;

    @RequestMapping("/addActivitty")
    @ResponseBody
    public Ret createActivitty(Activity activity) {

        int resultProbability = 0;
        int awardNumber = 0;

        Shop shop = (Shop) httpServletRequest.getSession().getAttribute(SessionKey.LOGIN_SHOP);
        if (shop == null) {
            return Ret.error(ShopResult.SHOPER_IS_NOT_EXIST);
        }
        else {
            //判断当前商户是否有activity
                Activity isActivity = activityService.findExitActivity(activity.getShopId(), Status.ACTIVITY_RUNNING);
                if (isActivity == null) {
                        List<Award> awardList = activity.getAwards();
                        for (Award award : awardList) {
                            resultProbability = award.getProbability() + resultProbability;
                            awardNumber++;
                        }
                        //判断商品个数s
                        if (awardNumber > AWARD_MAX_NUMBER) {
                            return Ret.error(AwardResult.AWARD_NUMBER_EXCEED);
                        }
                        else {
                            //判断参数合法性
                                if (resultProbability == PROBABILITY) {
                                    // 插入两个表
                                     Long l=  activityService.insertActivity(activity,shop.getId());
                                    awardService.insertAwards(awardList,l);
                                    //to do 生成二维码及活动方案
                                    String url = Config.LOTTERY_HOST + "?activity=" + activity.getId();
                                    String wxUrl= WXUtil.genGetUserURL("wx55cd46481d4e28d6", url);
                                    return Ret.success(wxUrl);
                                } else {
                                    return Ret.error(AwardResult.AWARD_PROBABILITY_WRONG);
                            }
                        }

                }
                else{
                return Ret.error(ActivityResult.ACTIVITY_IS_RUNNING); // 跳转结果页
            }

        }

    }

    @RequestMapping("/testSave")
    @ResponseBody
    //可以放入map中接收
    public void testSave(@RequestBody Activity activity){
       long a=1;
       System.out.println(activity);
       long l= activityService.insertActivity(activity,a);
        awardService.insertAwards(activity.getAwards(),l);


    }


    @RequestMapping("/activityInfo")
    @ResponseBody
    public Ret activityInfo(Long  id){
        Ret ret;
        Activity activity=activityRep.findActivityById(id);
        if (null==activity){
            ret=new Ret(ACTIVITY_NOT_FIND,null);
            return ret;
        }
        ret = new Ret(SUCCESS,activity);
        return ret;
        

    }


    }

