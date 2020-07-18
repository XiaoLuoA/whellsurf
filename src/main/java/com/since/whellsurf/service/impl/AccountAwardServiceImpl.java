package com.since.whellsurf.service.impl;

import com.since.whellsurf.common.Status;
import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.rep.AccountAwardRep;
import com.since.whellsurf.ret.AwardResult;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.service.AccountAwardService;
import com.since.whellsurf.service.ShopService;
import com.since.whellsurf.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王英豪111
 */
@Service
public class AccountAwardServiceImpl implements AccountAwardService {

    @Autowired
    private AccountAwardRep accountAwardRep;

    @Autowired
    private ShopService shopService;

    @Override
    public AccountAward checkAccountAward(String awardCode, Long activity) {
        return accountAwardRep.findAccountAwardByCode(awardCode,activity);
    }

    @Override
    public Ret addAccountAward(AccountAward accountAward) {
        String awardCode = RandomUtil.genRandomCode(Status.Award_CodeN);
        Shop shop = shopService.findByOpenId(accountAward.getOpenId());
        if (shop == null){
           AccountAward accountAward1 = accountAwardRep.findByOpenId(accountAward.getOpenId());
           if (accountAward1 != null){
                return Ret.error(AwardResult.GET_AWARD_REPEAT);
           }
        }
        accountAward.getActivityId();
        accountAwardRep.save(accountAward);
        return Ret.success(awardCode);
    }
}
