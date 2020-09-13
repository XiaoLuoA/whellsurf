package com.since.whellsurf.service;

import com.since.whellsurf.entity.Account;
import com.since.whellsurf.entity.AccountAward;

import java.util.List;


public interface AccountService {

    Account save(Account acount);



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
    Account addAccount(Account account);



    /**
     * @author wyh
     */
    /**
     * 通过openId查找用户
     * @param openId 用户openId
     * @return 用户对象实体
     */
    Account findByOpenId(String openId);


}
