package com.jamesou.springbootmvc.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jamesou.springbootmvc.bean.User;

import java.util.List;

/**
 * @author jamesou
 * @create 2020-04-12 11:49
 */
@Mapper
public interface UserMapper {

     List<User> SelectAll();

     User SelectByUser(User user);


     void InsertByUser(User user);

     void UpdateByusername(User user);
}
