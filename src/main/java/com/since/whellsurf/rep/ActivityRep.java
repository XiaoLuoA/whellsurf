package com.since.whellsurf.rep;

import com.since.whellsurf.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * @author 王英豪111
 */
@Repository
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






    /**
     * @author wyh
     */
    /**
     * 通过活动id和状态查找活动信息
     * @param activityId 活动id
     * @param status 活动状态
     * @return 活动信息
     */
    Activity findByIdAndStatus(Long activityId,Integer status);


}
