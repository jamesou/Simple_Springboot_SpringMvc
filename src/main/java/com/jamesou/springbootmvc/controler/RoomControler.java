package com.jamesou.springbootmvc.controler;

import com.github.pagehelper.PageHelper;
import com.jamesou.springbootmvc.bean.Room;
import com.jamesou.springbootmvc.service.RoomService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jamesou
 * @create 2020-05-10 9:43
 */
@Controller
public class RoomControler {

    @Resource
    RoomService roomService;

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping("/searchroom")
    public String selectroom(Model model, HttpServletRequest request){
        String pagenum =request.getParameter("pagenum") ;
        String msg = request.getParameter("msg");
        if(msg !=null){
            model.addAttribute("msg",msg);
        }
        Integer totalpage = roomService.SelectByCount()%3==0 ? (roomService.SelectByCount()/3) : (roomService.SelectByCount()/3)+1;
        if(pagenum !=null){
            if(Integer.parseInt(pagenum)==0){
                PageHelper.startPage(totalpage, 3);
                model.addAttribute("pagenum",totalpage);
            }else if(Integer.parseInt(pagenum)==totalpage+1){
                PageHelper.startPage(1, 3);
                model.addAttribute("pagenum",1);
            }else{
                PageHelper.startPage(Integer.parseInt(pagenum), 3);
                model.addAttribute("pagenum",Integer.parseInt(pagenum));
            }

        }else{
            PageHelper.startPage(1, 3);
            model.addAttribute("pagenum",1);
        }
        List<Room> rooms = roomService.SelectAll();
        model.addAttribute("rooms",rooms);
        return "select-room";
    }
    @PostMapping("/searchroom")
    public String  searchone(@RequestParam String query,Model model){
        if(query==""){
            return "redirect:/searchroom";
        }
        Room room = roomService.SelectById(query);
        model.addAttribute("rooms",room);
        return "room-one";
    }

    @GetMapping("/roomnew")
    public String roomnew(){
        return "room-new";
    }


    @PostMapping("/roomnew")
    public String roomnew(@RequestParam String rno, @RequestParam String rname, @RequestParam String rprice, RedirectAttributes redirectAttributes){
        if(rno==""){
            redirectAttributes.addAttribute("msg","add failed,room number can be empty");
            return "redirect:/searchroom";
        }
        if(rname==""){
            redirectAttributes.addAttribute("msg","add failed,room type can be empty");
            return "redirect:/searchroom";
        }
        if(rprice==""){
            redirectAttributes.addAttribute("msg","add failed,room price can be empty");
            return "redirect:/searchroom";
        }
        double price;
        try {
            price = Double.parseDouble(rprice);
        }catch (Exception e){
            redirectAttributes.addAttribute("msg","add failed,wrong room price");
            return "redirect:/searchroom";
        }
        Room room = new Room(rno,rname,price);
        // roomService.InsertByRoom(room);
        rabbitTemplate.convertAndSend("hotel","addroom",room);
        return "redirect:/searchroom";
    }

}
