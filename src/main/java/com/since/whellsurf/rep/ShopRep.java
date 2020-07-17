package com.since.whellsurf.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import com.since.whellsurf.entity.Shop;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRep extends JpaRepository<Shop,Long> {

    /**
     * 通过openId查询商家信息
     * @param openId 商家用户openId
     * @return 商家信息
     */
    Shop findByOpenId(String openId);


}
