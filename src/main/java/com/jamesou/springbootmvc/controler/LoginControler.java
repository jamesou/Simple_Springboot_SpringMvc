package com.jamesou.springbootmvc.controler;

import com.jamesou.springbootmvc.bean.User;
import com.jamesou.springbootmvc.service.UserService;
import com.jamesou.springbootmvc.util.MD5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author jamesou
 * @create 2020-04-06 13:48
 */
@Controller
public class LoginControler {

    @Resource
    UserService userService;


    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password, Map<String,Object> map, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        String password1 = MD5Utils.code(password);
        User newuser = new User(username,password1);
        User user = userService.SelectByUser(newuser);
        if(user != null){
            session.setAttribute("loginuser",user);
            return "redirect:/success";
        }else{
            map.put("msg","username or password are incorrect");
            return "index";
        }


    }
    @GetMapping("loginout")
    public String loginout(HttpSession session){
        session.removeAttribute("loginuser");
        return "index";
    }
}
