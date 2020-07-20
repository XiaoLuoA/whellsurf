package com.since.whellsurf.service.impl;

import com.since.whellsurf.common.Status;
import com.since.whellsurf.entity.*;
import com.since.whellsurf.rep.AccountAwardRep;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.rep.AwardRep;
import com.since.whellsurf.service.AccountAwardService;
import com.since.whellsurf.service.ActivityService;
import com.since.whellsurf.service.ShopService;
import com.since.whellsurf.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        AccountAward accountAward = accountAwardRep.findByActivityIdAndAccountId(activityId,AccountId);
        accountAward.setStatus(Status.REDEEM_STATUS_ALREADY);
        return accountAwardRep.save(accountAward);
    }

    @Override
    public AccountAward checkAccountAward(String awardCode, Long activity) {
        return accountAwardRep.findByAwardCodeAndActivityId(awardCode,activity);
    }

    @Override
    public Award getPrize(Long activityId){
        String awardCode = RandomUtil.genRandomCode(Status.AWARD_CODE_LENGTH);
        double awardProbability = RandomUtil.genAwardRandom();
        List<Award> awardList = awardRep.findAllSortAward(activityId);
        int tmp = 0;
        Award ret = null;
        for (Award award:awardList){
            tmp += award.getProbability();
            if (tmp>=awardProbability){
                ret = award;
            }
        }

        if (ret==null){
            return  null;
        }else {
            ret.setAwardCode(awardCode);
            return ret;
        }
    }

    @Override
    public Award addAccountAward(Long activityId, Account account) {
            Award ret = getPrize(activityId);
            AccountAward accountAward1 = new AccountAward();
            accountAward1.setOpenid(account.getOpenid());
            accountAward1.setAccountId(account.getId());
            accountAward1.setHeadImgUrl(account.getHeadImgUrl());
            accountAward1.setActivityId(activityId);
            accountAward1.setAwardId(ret.getId());
            accountAward1.setAwardName(ret.getName());
            accountAward1.setAwardCode(ret.getAwardCode());
            accountAward1.setStatus(Status.ACCOUNT_AWARD_NOT_REDEEM);
            accountAwardRep.save(accountAward1);
            return ret;

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
        List<AccountAward> accountAwards=accountAwardRep.findAllByActivityIdAndStatus(activityId,status);
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
