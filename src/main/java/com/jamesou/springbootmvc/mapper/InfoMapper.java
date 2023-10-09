package com.jamesou.springbootmvc.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jamesou.springbootmvc.bean.Information;

import java.util.List;

/**
 * @author jamesou
 * @create 2020-05-10 10:01
 */
@Mapper
public interface InfoMapper {

    List<Information> SelectAll();

    Integer SelectByCount();

    Information SelectById(String cno);

    void InsertByInformation(Information information);
}
