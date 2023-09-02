package com.avging.exception;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

/**
 * 账号被锁定异常
 */
public class AccountLockedException extends Base64DecodingException {

    public AccountLockedException() {
    }

    public AccountLockedException(String msg) {
        super(msg);
    }

}