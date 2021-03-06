package com.since.whellsurf.rep;

import com.since.whellsurf.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王英豪111
 */
@Repository
public interface AccountRep extends JpaRepository<Account,Long> {


    /**
     * @author wyh
     */
    /**
     * 添加用户信息
     * @param account 用户对象实体
     * @return 用户对象实体
     */
    @Override
    Account save(Account account);



    /**
     * @author wyh
     */
    /**
     * 通过openId查找用户
     * @param openId 用户openId
     * @return 用户对象实体
     */
    Account findByOpenid(String openId);

}

