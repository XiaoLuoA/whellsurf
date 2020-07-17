package com.since.whellsurf.rep;

import com.since.whellsurf.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRep extends JpaRepository<Activity,Long> {
    /**findActivity
     * @param acId
     * @return Activity
     */
    Activity findActivityById(Long acId);

    /**findByShopIdAndStatus
     * @param shopId
     * @param status
     * @return Activity
     */
    Activity findByShopIdAndStatus(Long shopId,Integer status);

}
