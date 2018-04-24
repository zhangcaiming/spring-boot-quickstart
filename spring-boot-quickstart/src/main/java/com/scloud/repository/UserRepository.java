package com.scloud.repository;


import com.scloud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户持久层操作接口
 * Created by andy on 2018/4/19.
 */
public interface UserRepository extends JpaRepository<User,Long>{

}
