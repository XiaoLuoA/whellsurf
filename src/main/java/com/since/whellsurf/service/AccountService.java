package com.since.whellsurf.service;

import com.since.whellsurf.entity.Account;

import java.util.List;


public interface AccountService {
    /**
     * @author  drj
     * 测试数据库关联性
     */
    Account findById(Long id);


    /**
     * 添加用户信息
     * @param account 用户信息
     * @return 用户信息集合
     */
    Account save(Account account);


}
