package com.jamesou.springbootmvc.exception;

/**
 * @author jamesou
 * @create 2020-04-13 21:43
 */
public class MailException extends Exception {

    public MailException() {

        super("illegal email address");
    }
}
