package com.jamesou.springbootmvc.bean;

import javax.validation.constraints.Email;

/**
 * @author jamesou
 * @create 2020-04-12 14:00
 */
public class Mail {
    @Email
    private String email;

    public Mail(String email) {
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
