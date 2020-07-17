package com.since.whellsurf.rep;

import com.since.whellsurf.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRep extends JpaRepository<Account,Long> {


    /**
     * 添加用户信息
     * @param account 用户信息
     * @return 用户信息集合
     */
    @Override
    Account save(Account account);

}

