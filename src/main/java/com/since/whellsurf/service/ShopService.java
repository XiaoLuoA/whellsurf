package com.since.whellsurf.service;

import com.since.whellsurf.entity.AccountAward;
import com.since.whellsurf.entity.Activity;
import com.since.whellsurf.entity.Shop;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jayzh
 */
@Service
public interface ShopService {

    /**
     * @author wyh
     */
    /**
     * 通过openId查询商家信息
     * @param openId 商家用户openId
     * @return 商家信息
     */
    Shop findByOpenId(String openId);


    /**
     * @author wyh
     */
    /**
     * 添加商家信息
     * @param shop 商家信息
     */
    void addShop(Shop shop);



}
