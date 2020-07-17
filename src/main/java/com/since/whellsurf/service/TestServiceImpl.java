package com.since.whellsurf.service;

import com.since.whellsurf.entity.Users;
import com.since.whellsurf.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author luoxinyuan
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    public List<Users> saveAll(Iterable<Users> var1){
        return  testRepository.saveAll(var1);
    }

    @Transactional
    public List<Users> saveAllTrans(Iterable<Users> var1){
        return  testRepository.saveAll(var1);
    }
}
