package com.jamesou.springbootmvc.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.jamesou.springbootmvc.bean.Customer;
import java.util.List;

/**
 * @author jamesou
 * @create 2020-05-10 10:01
 */
@Mapper
public interface CustomerMapper {

    List<Customer> SelectAll();

    Integer SelectByCount();

    Customer SelectById(String cno);

    void InsertByCustomer(Customer customer);
}
