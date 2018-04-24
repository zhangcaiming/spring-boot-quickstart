package com.scloud.service.impl;


import com.scloud.domain.User;
import com.scloud.repository.UserRepository;
import com.scloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User 业务层实现
 * <p>
 * Created by andy on 2018/4/19.
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<User> findByPage(Pageable pageable) {
        log.info(" \n 分页查询用户："
                + " PageNumber = " + pageable.getPageNumber()
                + " PageSize = " + pageable.getPageSize());
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User insertByUser(User user) {
        log.info("新增用户：" + user.toString());
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        log.info("更新用户:" + user.toString());
        return userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);

        log.info("删除用户:" + user.toString());
        return user;
    }

    @Override
    public User findById(Long id) {
        log.info("获取用户 ID:" + id);
        return userRepository.findById(id).get();
    }
}
