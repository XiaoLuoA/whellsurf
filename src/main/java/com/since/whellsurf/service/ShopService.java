package com.since.whellsurf.service;

import com.since.whellsurf.entity.Shop;

public interface ShopService {

    /**
     * 通过openId查询商家信息
     * @param openId 商家用户openId
     * @return 商家信息
     */
    Shop findByOpenId(String openId);


}
