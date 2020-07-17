package com.since.whellsurf.rep;

import com.since.whellsurf.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRep extends JpaRepository<Activity,Long> {
    Activity findActivityById(Long acId);
}
