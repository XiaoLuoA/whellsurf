package com.since.whellsurf.service;

import com.since.whellsurf.entity.Account;


public interface AccountService {
    /**
     * @author  drj
     * 测试数据库关联性
     */
    Account findById(Long id);
}
