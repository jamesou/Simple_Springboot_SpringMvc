package com.jamesou.springbootmvc.service;


import com.jamesou.springbootmvc.bean.Room;
import com.jamesou.springbootmvc.mapper.RoomMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jamesou
 * @create 2020-05-10 10:05
 */
@Service
public class RoomService {

    @Resource
    RoomMapper roomMapper;

    public List<Room> SelectAll(){
        return roomMapper.SelectAll();
    }

    public Integer SelectByCount(){
        return roomMapper.SelectByCount();
    }
    @Cacheable(value = "room" , key = "#query")
    public Room SelectById(String query){
        return roomMapper.SelectById(query);
    }

    @RabbitListener(queues = "addroom")
    public void InsertByRoom(Room room){
        roomMapper.InsertByRoom(room);
    }

    public List<Room> SelectByEmpty(){
        return roomMapper.SelectByEmpty();
    }
    public void UpdateByRno(String rno){
        roomMapper.UpdateByRno(rno);
    }

}
