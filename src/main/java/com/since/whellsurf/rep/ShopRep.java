package com.since.whellsurf.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import com.since.whellsurf.entity.Shop;
import org.springframework.stereotype.Repository;

/**
 * @author 王英豪111
 */
@Repository
public interface ShopRep extends JpaRepository<Shop,Long> {

    /**
     * @author wyh
     */
    /**
     * 通过openId查询商家信息
     * @param openId 商家用户openId
     * @return 商家信息
     */
    Shop findByOpenid(String openId);

    /**
     * @author wyh
     */
    /**
     * 添加商家信息
     * @param shop 商家信息
     * @return 商家信息
     */
    @Override
    Shop save(Shop shop);




}
