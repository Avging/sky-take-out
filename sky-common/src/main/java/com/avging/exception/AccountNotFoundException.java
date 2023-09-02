package com.avging.exception;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

/**
 * 账号不存在异常
 */
public class AccountNotFoundException extends Base64DecodingException {

    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String msg) {
        super(msg);
    }

}