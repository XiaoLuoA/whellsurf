package com.since.whellsurf.rep;

import com.since.whellsurf.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author jayzh
 */
public interface ActivityRep extends JpaRepository<Activity,Long> {

    /**this method aims to findActivity by activityId
     * @param acId
     * @return the Object of Activity
     * @author jayzh
     */
   public Activity findActivityById(Long acId);


    /**this method aims to find Activity By shopId and status
     * @param shopId
     * @param status
     * @return list of Activity
     * @author jayzh
     */
    public List<Activity> findByShopIdAndStatus(Long shopId, Integer status);

}
