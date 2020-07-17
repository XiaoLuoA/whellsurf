package com.since.whellsurf.rep;

import com.since.whellsurf.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.since.whellsurf.entity.Shop;

/**
 * @author jayzh
 */
public interface ShopRep extends JpaRepository<Shop,Long> {
    

}
