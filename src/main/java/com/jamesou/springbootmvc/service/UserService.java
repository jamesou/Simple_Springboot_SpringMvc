package com.jamesou.springbootmvc.service;

import com.jamesou.springbootmvc.bean.User;
import com.jamesou.springbootmvc.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jamesou
 * @create 2020-06-12 20:34
 */
@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public List<User> SelectAll(){
        return userMapper.SelectAll();
    }

    public User SelectByUser(User user){
        return userMapper.SelectByUser(user);
    }


    public void InsertByUser(User user){
        userMapper.InsertByUser(user);
    }

    public void UpdateByusername(User user){
        userMapper.UpdateByusername(user);
    }
}
