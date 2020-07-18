package com.since.whellsurf.rep;

import com.since.whellsurf.entity.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRep extends JpaRepository<Award,Long> {


    /**
     * 查找所有奖品
     * @return
     */
    @Query(value = "select * from award where activity_id = ?1 order by probability asc",nativeQuery = true)
    List<Award> findAllAward(Long activityId);
}
