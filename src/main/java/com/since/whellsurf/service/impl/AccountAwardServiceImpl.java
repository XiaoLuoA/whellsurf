package com.since.whellsurf.service.impl;

import com.since.whellsurf.common.Status;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Award;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.rep.AccountAwardRep;
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
        return accountAwardRep.findAccountAwardByCode(awardCode,activity);
    }

    @Override
    public Ret addAccountAward(AccountAward accountAward) {
        String awardCode = RandomUtil.genRandomCode(Status.AWARD_VALID);
        Double awardProbability = RandomUtil.genAwardRandom(0.01,100,2);
        List<Award> awardList = awardRep.findAllAward(accountAward.getActivityId());
        activityService.findExitActivity(accountAward.getActivityId(),Status.ACTIVITY_VALID);
        Shop shop = shopService.findByOpenId(accountAward.getOpenId());
        if (shop == null){
           AccountAward accountAward1 = accountAwardRep.findByOpenId(accountAward.getOpenId());
           if (accountAward1 != null){
                return Ret.error(AwardResult.GET_AWARD_REPEAT);
           }
            for (Award award: awardList) {
                if (awardProbability <= award.getProbability()){
                    accountAward.setAwardName(award.getName());
                    accountAward.setAwardId(award.getId());
                    accountAward.setStatus(Status.AWARD_VALID);
                    accountAward.setAwardCode(awardCode);
                }
                awardProbability += award.getProbability();
            }
        }else {
            for (Award award: awardList) {
                if (awardProbability <= award.getProbability()){
                    //中奖
                }
                awardProbability += award.getProbability();
            }
        }


        accountAward.getActivityId();
        accountAwardRep.save(accountAward);
        return Ret.success(awardCode);
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
            ad.setOpenId(null);
            ad.setActivityId(null);
            ad.setAccountId(null);
        }
        return accountAwards;
    }

}
