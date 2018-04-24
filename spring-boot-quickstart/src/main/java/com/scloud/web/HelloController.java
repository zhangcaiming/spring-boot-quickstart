package com.scloud.web;

import com.scloud.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by andy on 2018/4/23.
 */
@Controller
@RequestMapping("/")
public class HelloController {

    @RequestMapping("hello")
    public String index() throws Exception{
        throw new Exception("发生错误");
    }

    @RequestMapping("json")
    public String json() throws MyException {
        throw new MyException("发生错误2（自定义错误）");
    }

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }


}
