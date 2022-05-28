package com.accenture.springcore.jms;

import com.accenture.model.Transaction;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionReceiver {

    @JmsListener(destination = "TransactionQueue", containerFactory = "processorFactory")
    public void receiveTransaction(Transaction transaction) {
        System.out.println("Received: " + transaction);
    }
}
