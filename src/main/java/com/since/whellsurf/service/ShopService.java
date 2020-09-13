package com.since.whellsurf.service;

import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.ret.Ret;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Service;


@Service
public interface ShopService {
    Ret checkShop(WxMpUser user);
    Ret checkActivity(String openid, Long activityId);
    Shop findShopByOpenId(String id);
    Account findOrAddAccountByOpenId(WxMpUser wxMpUser);
}
