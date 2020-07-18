package com.since.whellsurf.rep;

import com.since.whellsurf.entity.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRep extends JpaRepository<Award,Long> {

}
