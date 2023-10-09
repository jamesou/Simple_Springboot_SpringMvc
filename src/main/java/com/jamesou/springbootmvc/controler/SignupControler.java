package com.jamesou.springbootmvc.controler;

import com.jamesou.springbootmvc.bean.Mail;
import com.jamesou.springbootmvc.bean.User;
import com.jamesou.springbootmvc.service.UserService;
import com.jamesou.springbootmvc.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * @author jamesou
 * @create 2020-04-12 14:01
 */
@Controller
public class SignupControler {


    @Resource
    UserService userService;

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @RequestMapping("/user/signup")
    public String signup(@Valid Mail mail,Map<String,Object> map){
        Random random = new Random();
        int number=random.nextInt(10000)+1000 ;
        map.put("validate_code",number);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Validate Code");
        simpleMailMessage.setText("Your validate code is :"+number);
        simpleMailMessage.setTo(mail.getEmail());
        simpleMailMessage.setFrom("xxx@163.com");
        javaMailSender.send(simpleMailMessage);
        return  "register";
    }

    @RequestMapping("/signup")
    public String sign(HttpServletRequest request, HttpServletResponse response,Map<String,Object> map) throws ServletException, IOException {
        String validate_code = request.getParameter("validate_code");
        String code = request.getParameter("code");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(code.equals(validate_code)&&username!=null&&password!=null){
            userService.InsertByUser(new User(username,MD5Utils.code(password)));
            return "index";
        }else if (code==null||username==null||password==null){
            map.put("msg","information can not be empty");
            map.put("validate_code",validate_code);
            return "register";
        }else{
            map.put("msg","validate code error");
            map.put("validate_code",validate_code);
            return "register";
        }


    }
}
