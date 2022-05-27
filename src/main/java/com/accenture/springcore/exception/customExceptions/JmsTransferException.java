package com.accenture.springcore.exception.customExceptions;

import org.springframework.jms.JmsException;

public class JmsTransferException  extends RuntimeException {

    public JmsTransferException(String msg) {
        super(msg);
    }
}
