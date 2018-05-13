package com.scloud.web;

import com.scloud.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by andy on 2018/4/23.
 */
@Slf4j
@Controller
@RequestMapping("/")
public class HelloController {

    @RequestMapping("hello")
    public String index() throws Exception{
        log.error("发生错误");
        throw new Exception("发生错误");
    }

    @RequestMapping("json")
    public String json() throws MyException {
        log.error("发生错误2（自定义错误）");
        throw new MyException("发生错误2（自定义错误）");
    }

    @RequestMapping("/")
    public String index(ModelMap map) {
        log.info("hello index ");
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }


}
