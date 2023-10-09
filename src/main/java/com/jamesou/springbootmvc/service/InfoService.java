package com.jamesou.springbootmvc.service;

import com.jamesou.springbootmvc.bean.Information;
import com.jamesou.springbootmvc.mapper.InfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jamesou
 * @create 2020-05-10 16:19
 */
@Service
public class InfoService {

    @Resource
    InfoMapper infoMapper;

    public List<Information> SelectAll(){
        return infoMapper.SelectAll();
    }

    public Integer SelectByCount(){
        return infoMapper.SelectByCount();
    }

    public Information SelectById(String cno){
        return infoMapper.SelectById(cno);
    }

    public void InsertByInformation(Information information){
        infoMapper.InsertByInformation(information);
    }
}
