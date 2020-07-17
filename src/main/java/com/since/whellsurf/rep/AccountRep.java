package com.since.whellsurf.rep;

import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRep extends JpaRepository<Account,Long> {
    
}

