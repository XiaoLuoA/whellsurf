package com.since.whellsurf.service;

import com.since.whellsurf.entity.Award;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AwardService {

    void insertAwards(List<Award> awards,Long l);
    

}
