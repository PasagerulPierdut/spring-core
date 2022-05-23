package com.accenture.springcore.config;

import com.accenture.springcore.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class Scheduler {

    @Autowired
    private TransactionService transactionService;

    @Scheduled(cron = "0 9 1 * * ?")
    public void generateMonthlyReport() {
        System.out.println(transactionService.getAllConfirmedTransactions());
    }


    /** Initial delay set in order to avoid TransactionSystemException
     *
     */
    @Scheduled(fixedRate = 60000, initialDelay = 20000)
    public void confirmTransactions() {
        transactionService.confirmTransactions();
        System.out.println(transactionService.findAll());
    }
}
