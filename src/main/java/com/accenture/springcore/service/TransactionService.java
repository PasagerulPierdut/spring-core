package com.accenture.springcore.service;

import com.accenture.springcore.utils.validator.ValidTransaction;
import com.accenture.springcore.exception.EntityNotFoundException;
import com.accenture.springcore.model.Criteria;
import com.accenture.springcore.model.Transaction;
import com.accenture.springcore.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Boolean.TRUE;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAll(Criteria criteria) {
        List<Transaction> finalList = transactionRepository.findAll();
        Stream<Transaction> stream = finalList.stream();
        if (criteria.getProduct() != null) {
            stream = stream.filter(transaction -> transaction.getProduct().equals(criteria.getProduct()));
        }
        if (criteria.getType() != null) {
            stream = stream.filter(transaction -> transaction.getType().equals(criteria.getType()));
        }
        if (criteria.getMinAmount() != null) {
            stream = stream.filter(transaction -> transaction.getAmount() > criteria.getMinAmount());
        }
        if (criteria.getMaxAmount() != null) {
            stream = stream.filter(transaction -> transaction.getAmount() < criteria.getMaxAmount());
        }
        return stream.toList();
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
        target.setType(source.getType());
        target.setAmount(source.getAmount());
        return transactionRepository.save(target);
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.findById(id).map(transaction -> {
            transactionRepository.delete(transaction);
            return transaction;
        }).orElseThrow(() -> new EntityNotFoundException
                ("This transaction does not exist in database."));
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
}