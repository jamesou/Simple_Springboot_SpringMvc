package com.jamesou.springbootmvc.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jamesou.springbootmvc.bean.Room;

import java.util.List;

/**
 * @author jamesou
 * @create 2020-05-10 10:01
 */
@Mapper
public interface RoomMapper {

    List<Room> SelectAll();

    Integer SelectByCount();

    Room SelectById(String rno);

    void InsertByRoom(Room room);
    List<Room> SelectByEmpty();

    void UpdateByRno(String rno);
}
