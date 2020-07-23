package com.since.whellsurf.service.impl;


import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.common.Status;
import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.rep.ActivityRep;
import com.since.whellsurf.rep.ShopRep;
import com.since.whellsurf.ret.ActivityResult;
import com.since.whellsurf.ret.Ret;
import com.since.whellsurf.ret.ShopResult;
import com.since.whellsurf.service.AccountService;
import com.since.whellsurf.service.ActivityService;
import com.since.whellsurf.service.ShopService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jayzh
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRep shopRep;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private AccountService accountService;

    @Override
    public Ret checkShop(WxMpUser user){
        Shop shop = shopRep.findByOpenid(user.getOpenId());
        if (shop == null){
            shop = new Shop();
            shop.setOpenid(user.getOpenId());
            shop.setNickname(user.getNickname());
            shop.setAddress(user.getCountry() + user.getProvince() + user.getCity());
            shop.setHeadImgUrl(user.getHeadImgUrl());
            System.out.println(user.getHeadImgUrl());
            shop.setGender(user.getSexDesc());
            shop.setStatus(Status.SHOP_NOT_ACTIVATE);
            shop = shopRep.save(shop);
        }
        if (Status.SHOP_NOT_ACTIVATE.equals(shop.getStatus())){
            return Ret.error(ShopResult.SHOP_NOT_ACTIVATE);
        }
        return Ret.success(shop);
    }

    @Override
    public Ret checkActivity(String openid, Long activityId) {
        Activity activity = activityService.findById(activityId);
        if (activity==null){
            return Ret.error(ActivityResult.ACTIVITY_NOT_FIND);
        }else if(!Status.ACTIVITY_RUNNING.equals(activity.getStatus())){
            return Ret.error(ActivityResult.ACTIVITY_IS_OUTDATED);
        }

        Shop shop = shopRep.findByOpenid(openid);
        if (shop!=null&&shop.getId().equals(activity.getShopId())) {
            if (Status.ACTIVITY_RUNNING.equals(activity.getStatus())) {
                return Ret.success(SessionKey.LOGIN_SHOP);
            }
        }
        return Ret.success(SessionKey.LOGIN_USER);
    }

    @Override
    public Shop findShopByOpenId(String openid) {
       return shopRep.findByOpenid(openid);
    }


    @Override
    public Account findOrAddAccountByOpenId(WxMpUser user) {
        Account account = accountService.findByOpenId(user.getOpenId());
        if (account==null){
            account = new Account();
            account.setStatus(Status.ACCOUNT_NORMAL);
            account.setOpenid(user.getOpenId());
            account.setNickname(user.getNickname());
            account.setHeadImgUrl(user.getHeadImgUrl());
            account.setGender(user.getSexDesc());
            account.setAddress(user.getCountry()+user.getProvince()+user.getCity());
            account = accountService.save(account);
        }
        return account;
    }
}
