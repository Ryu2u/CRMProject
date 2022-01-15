package com.cht.crm.exception;

/**
 * @author Ryuzu
 * @date 2022/1/15 13:44
 */
public class LoginException extends Exception {
    public LoginException(){
        super();
    }
    public LoginException(String msg){
        super(msg);
    }
}
