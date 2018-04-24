package com.scloud.service;


import com.scloud.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * User 业务层接口
 *
 * Created by andy on 2018/4/19.
 */
public interface UserService {

    /**
     * 获取用户分页列表
     *
     * @param pageable
     * @return
     */
    Page<User> findByPage(Pageable pageable);

    List<User> findAll();

    User insertByUser(User user);

    User update(User user);

    User delete(Long id);

    User findById(Long id);

}
