package com.accenture.springcore.service;

import com.accenture.springcore.exception.customExceptions.EntityNotFoundException;
import com.accenture.springcore.model.Transaction;
import com.accenture.springcore.model.TransactionType;
import com.accenture.springcore.repository.TransactionDao;
import com.accenture.springcore.repository.TransactionRepository;
import com.accenture.springcore.repository.base.BaseService;
import com.accenture.springcore.utils.validator.ValidTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Boolean.TRUE;

@Service
public class TransactionService extends BaseService<Transaction, Integer> {

    private TransactionRepository transactionRepository;

    private TransactionDao transactionDao;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionDao transactionDao) {
        this.transactionRepository = transactionRepository;
        this.transactionDao = transactionDao;
    }

    public List<Transaction> findAll(Integer id, Integer userId, String product, TransactionType transactionType,
                                     Double minAmount, Double maxAmount, String startDateTime, String endDateTime,
                                     boolean confirmed) {
        LocalDateTime startTime = null;
        LocalDateTime endTime  = null;
        if(startDateTime != null) {
            startTime = formatDate(startDateTime);
        }
        if(endDateTime != null) {
            endTime = formatDate(endDateTime);
        }
        return transactionDao.findAllByCriteria(id, userId, product, transactionType, minAmount,
                maxAmount, startTime, endTime, confirmed);
    }

    public Transaction getOneById(Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        ("The transaction with the specified ID doesn't exist."));
    }

    public Transaction addNew(@ValidTransaction Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction replaceTransaction(Integer id, Transaction source)
            throws EntityNotFoundException {
        Transaction target = getOneById(id);
        target.setProduct(source.getProduct());
        target.setTransactionType(source.getTransactionType());
        target.setAmount(source.getAmount());
        return transactionRepository.save(target);
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.findById(id).ifPresentOrElse(
                transaction -> transactionRepository.deleteById(transaction.getId()), NoSuchElementException::new);
    }

    public List<Transaction> getAllConfirmedTransactions() {
        return transactionRepository.findAll().stream()
                .filter(transaction -> transaction.getCreatedAt().isAfter(
                        LocalDateTime.now().minusMonths(1)) && (transaction.isConfirmed()))
                .toList();
    }

    public void confirmTransactions() {
        transactionRepository.findAll().forEach(transaction -> {
            if (!transaction.isConfirmed()) {
                transaction.setConfirmed(TRUE);
                transactionRepository.save(transaction);
            }
        });
    }

    private LocalDateTime formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime qTime = LocalDateTime.parse(date, formatter);
        return qTime;
    }
}