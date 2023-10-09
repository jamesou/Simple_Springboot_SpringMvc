package com.jamesou.springbootmvc.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jamesou
 * @create 2020-04-12 11:42
 */
@Controller
public class MyControler {

    @RequestMapping("/")
    public String login(){
        return "index";
    }

    @RequestMapping("/register")
    public String register(){
        return "mail";
    }

}
