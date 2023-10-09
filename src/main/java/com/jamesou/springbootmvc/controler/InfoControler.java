package com.jamesou.springbootmvc.controler;

import com.github.pagehelper.PageHelper;
import com.jamesou.springbootmvc.bean.Customer;
import com.jamesou.springbootmvc.bean.Information;
import com.jamesou.springbootmvc.service.CustomerService;
import com.jamesou.springbootmvc.service.InfoService;
import com.jamesou.springbootmvc.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author jamesou
 * @create 2020-05-10 16:21
 */
@Controller
public class InfoControler {

    @Resource
    InfoService infoService;

    @Resource
    RoomService roomService;

    @Resource
    CustomerService customerService;

    @GetMapping("/searchinfo")
    public String selectroom(Model model, HttpServletRequest request){
        String pagenum =request.getParameter("pagenum") ;
        String msg = request.getParameter("msg");
        if(msg !=null){
            model.addAttribute("msg",msg);
        }
        Integer totalpage = infoService.SelectByCount()%3==0 ? (infoService.SelectByCount()/3) : (infoService.SelectByCount()/3)+1;
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
        List<Information> informations = infoService.SelectAll();
        model.addAttribute("informations",informations);
        return "select-info";
    }
    @PostMapping("/searchinfo")
    public String  searchone(@RequestParam String query, Model model){
        if(query==""){
            return "redirect:/searchinfo";
        }
        Information information = infoService.SelectById(query);
        model.addAttribute("informations",information);
        return "info-one";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("rooms",roomService.SelectByEmpty());
        return "create-info";
    }
    @PostMapping("/createinfo")
    public String createinfo(@RequestParam String cname, @RequestParam String cno, @RequestParam String telephone, @RequestParam String rno, @RequestParam String deposit, RedirectAttributes redirectAttributes){
        if(cno==""){
            redirectAttributes.addAttribute("msg","add failed, ID can not be empty");
            return "redirect:/searchinfo";
        }
        if(cname==""){
            redirectAttributes.addAttribute("msg","add failed, name can not be empty");
            return "redirect:/searchinfo";
        }
        if(telephone==""){
            redirectAttributes.addAttribute("msg","add failed, telephone can not be empty");
            return "redirect:/searchinfo";
        }
        if(rno==""){
            redirectAttributes.addAttribute("msg","add failed, rno can not be empty");
            return "redirect:/searchinfo";
        }
        if(deposit==""){
            redirectAttributes.addAttribute("msg","add failed, deposit can not be empty");
            return "redirect:/searchinfo";
        }
        double price;
        try {
            price = Double.parseDouble(deposit);
        }catch (Exception e){
            redirectAttributes.addAttribute("msg","add failed, deposit should be number");
            return "redirect:/searchinfo";
        }
        infoService.InsertByInformation(new Information(cno,rno,new Date(),price));
        customerService.InsertByCustomer(new Customer(cno,cname,telephone));
        roomService.UpdateByRno(rno);
        return "redirect:/searchinfo";
    }
}
