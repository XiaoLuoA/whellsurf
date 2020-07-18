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

    Shop findByOpenId(String openId);


}
