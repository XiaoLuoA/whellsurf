package com.since.whellsurf.repository;

import com.since.whellsurf.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestRepository extends JpaRepository<Users, Long> {


}
