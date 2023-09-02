package com.avging.exception;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

public class AddressBookBusinessException extends Base64DecodingException {

    public AddressBookBusinessException(String msg) {
        super(msg);
    }

}
