package com.since.whellsurf.service.impl;

import com.since.whellsurf.entity.Award;
import com.since.whellsurf.rep.AwardRep;
import com.since.whellsurf.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.since.whellsurf.common.Status.AWARD_INSERT;

@Service
public class AwardServiceImpl implements AwardService {

    @Autowired
    AwardRep awardRep;

    /**
     * @author drj
     * 插入List<Award>
     * @param awards id
     * @return null
     */
    @Override
    public void insertAwards(List<Award> awards,Long activityId) {

       awards.forEach(award -> {
           award.setStatus(AWARD_INSERT);
           award.setActivityId(activityId);
       });
        awardRep.saveAll(awards);




    }



}
