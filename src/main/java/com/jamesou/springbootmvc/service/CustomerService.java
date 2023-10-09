package com.jamesou.springbootmvc.service;

import com.jamesou.springbootmvc.bean.Customer;
import com.jamesou.springbootmvc.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jamesou
 * @create 2020-05-10 16:05
 */
@Service
public class CustomerService {

    @Resource
    CustomerMapper customerMapper;


    public List<Customer> SelectAll(){
        return customerMapper.SelectAll();
    }

    public Integer SelectByCount(){
        return customerMapper.SelectByCount();
    }

    public Customer SelectById(String cno){
        return customerMapper.SelectById(cno);
    }

    public void InsertByCustomer(Customer customer){
        customerMapper.InsertByCustomer(customer);
    }
}
