package com.scloud.web;


import com.scloud.domain.User;
import com.scloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 *
 * Created by andy on 2018/4/19.
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     *  获取用户分页列表
     *    处理 "/users" 的 GET 请求，用来获取用户分页列表
     *    通过 @RequestParam 传递参数，进一步实现条件查询或者分页查询
     *
     *    Pageable 支持的分页参数如下
     *    page - 当前页 从 0 开始
     *    size - 每页大小 默认值在 application.properties 配置
     */
    @RequestMapping(value = "withPage" ,method = RequestMethod.GET)
    @ResponseBody
    public Page<User> getUserPage(Pageable pageable) {
        return userService.findByPage(pageable);
    }


    /**
     * 获取用户列表
     *  通过处理 "/users" 的 GET请求，用来获取用户列表
     *
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(ModelMap map) {
        map.addAttribute("userList", userService.findAll());
        return "userList";
    }

    /**
     * 显示用户创建表单
     *
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createUserForm(ModelMap map) {
        map.addAttribute("user", new User());
        map.addAttribute("action", "create");
        return "userForm";
    }

    /**
     *  创建用户
     *      处理 "/users" 的 POST 请求，用来创建用户
     *      通过 @ModelAttribute 绑定参数，也通过 @RequestParam从页面中传递参数
     *
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        userService.insertByUser(user);
        return "redirect:/users/";
    }

    /**
     * 显示需要更新用户表单
     *      处理 "/update/{id}" 的 GET 请求，通过 URL 中的 id值获取 User 信息
     *      URL中的 id ，通过@PathVariable 绑定参数
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Long id, ModelMap map) {
        map.addAttribute("user", userService.findById(id));
        map.addAttribute("action", "update");
        return "userForm";
    }

    /**
     *  处理 "/update 的 PUT 请求,用来更新 User 信息
     *
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String putUser(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/users/";
    }

    /**
     *  处理 "/delete/{id}" 的 GET 请求,用来删除 User 信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users/";
    }


}
