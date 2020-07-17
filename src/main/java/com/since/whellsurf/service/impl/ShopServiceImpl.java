package com.since.whellsurf.service.impl;


import com.since.whellsurf.entity.Shop;
import com.since.whellsurf.rep.ShopRep;
import com.since.whellsurf.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRep shopRep;

    @Override
    public Shop findByOpenId(String openId) {
        return shopRep.findByOpenId(openId);
    }
}
