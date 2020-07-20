package com.since.whellsurf.service.impl;

import com.since.whellsurf.common.Status;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Award;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.rep.AccountAwardRep;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.rep.AwardRep;
import com.since.whellsurf.ret.AwardResult;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.service.AccountAwardService;
import com.since.whellsurf.service.ActivityService;
import com.since.whellsurf.service.AwardService;
import com.since.whellsurf.service.ShopService;
import com.since.whellsurf.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.since.whellsurf.common.Status.REDEEM_STATUS_OK;

/**
 * @author jayzh
 */
@Service
public class AccountAwardServiceImpl implements AccountAwardService {

    @Autowired
    AccountAwardRep accountAwardRep;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRep activityRep;

    @Autowired
    private AwardRep awardRep;


    /**this implement method aims to modify the status of account award
     * @param  activityId, AccountId
     * @return AccountAward which is altered
     * @author jayzh
     */
    @Override
    public AccountAward redeem(Long activityId, Long AccountId) {
        AccountAward accountAward=accountAwardRep.findByActivityIdAndAccountId(activityId,AccountId);
        accountAward.setStatus(REDEEM_STATUS_OK);
        return accountAwardRep.save(accountAward);
    }

    @Override
    public AccountAward checkAccountAward(String awardCode, Long activity) {
        return accountAwardRep.findByAwardCodeAndActivityId(awardCode,activity);
    }

    @Override
    public String addAccountAward(AccountAward accountAward) {
        String awardCode = RandomUtil.genRandomCode(Status.AWARD_CODE_LENGTH);
        Double awardProbability = RandomUtil.genAwardRandom(0.01,100,2);
        List<Award> awardList = awardRep.findAllAward(accountAward.getActivityId());

        Activity activity = activityRep.findByIdAndStatus(accountAward.getActivityId(),Status.ACTIVITY_VALID);
        Shop shop = shopService.findByOpenId(accountAward.getOpenid());
        if (shop == null){
            //判断用户
           AccountAward accountAward_account = accountAwardRep.findByOpenid(accountAward.getOpenid());
           if (accountAward_account != null){
                return "1";
           }
           //可以抽奖
            for (Award award: awardList) {
                if (awardProbability <= award.getProbability()){
                    accountAward.setAwardName(award.getName());
                    accountAward.setAwardId(award.getId());
                    accountAward.setStatus(Status.AWARD_VALID);
                    accountAward.setAwardCode(awardCode);
                    accountAward.setShopId(activity.getShopId());
                    accountAwardRep.save(accountAward);
                }
                awardProbability += award.getProbability();
            }
            return awardCode;
        }else {
            //商家自己的活动可以无限抽奖
            if (activity.getShopId().equals(shop.getId())){
                for (Award award: awardList) {
                    if (awardProbability <= award.getProbability()){
                      accountAward.setAwardName(award.getName());
                      accountAward.setAwardId(award.getId());
                      accountAward.setStatus(Status.AWARD_VALID);
                      accountAward.setAwardCode(awardCode);
                      accountAward.setShopId(activity.getShopId());
                      accountAwardRep.save(accountAward);
                    }
                    awardProbability += award.getProbability();
                }
                return awardCode;
            } else {
                //商家抽奖别的活动只能抽一次
                AccountAward accountAward_account = accountAwardRep.findByOpenid(accountAward.getOpenid());
                if (accountAward_account != null){
                    return "1";
                }
                for (Award award: awardList) {
                    if (awardProbability <= award.getProbability()){
                        accountAward.setAwardName(award.getName());
                        accountAward.setAwardId(award.getId());
                        accountAward.setStatus(Status.AWARD_VALID);
                        accountAward.setAwardCode(awardCode);
                        accountAward.setShopId(activity.getShopId());
                        accountAwardRep.save(accountAward);
                    }
                    awardProbability += award.getProbability();
                }
            }
            return awardCode;
        }
    }

    @Override
    public AccountAward findByOpenId(String openId) {
        return accountAwardRep.findByOpenid(openId);
    }

    @Override
    public List<AccountAward> findAccountAwardByActivityIdAndStatus(Long activityId, Integer status) {
        return accountAwardRep.findAllByActivityIdAndStatus(activityId,status);
    }

    @Override
    public List<AccountAward> findAccountAwardByActivityId(Long activityId) {
        return accountAwardRep.findAllByActivityId(activityId);
    }



    /**
     * this method aims to find AccountAward By ActivityId and status
     *
     * @param activityId
     * @param status
     * @return list of AccountAward
     * @author jayzh
     */
    @Override
    public List<AccountAward> findAccountAward(Long activityId, Integer status) {
        List<AccountAward> accountAwards=accountAwardRep.findByActivityIdAndStatus(activityId,status);
        return accountAwards;
    }

    /**
     * this method aims to hide some information which is useless
     *
     * @param accountAwards
     * @return list of AccountAward
     * @author jayzh
     */
    @Override
    public List<AccountAward> hideUselessInformation(List<AccountAward> accountAwards) {
        for (AccountAward ad:accountAwards) {
            ad.setOpenid(null);
            ad.setActivityId(null);
            ad.setAccountId(null);
        }
        return accountAwards;
    }

}
