package com.accenture.springcore.service;

import com.accenture.springcore.model.Transaction;
import com.accenture.springcore.model.Type;
import com.accenture.springcore.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final String NO_TRADES = "No trades with the given argument.";
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllFilteredByProduct(String product) throws NoSuchFieldException {
        List<Transaction> filteredByProduct = getAll().stream()
                .filter(transaction -> transaction.getProduct().equals(product))
                .collect(Collectors.toList());
        if (filteredByProduct.isEmpty()) {
            throw new NoSuchFieldException(NO_TRADES);
        } else {
            return filteredByProduct;
        }
    }

    public List<Transaction> getAllFilteredByType(String type) throws NoSuchFieldException {
        List<Transaction> filteredByType = getAll().stream()
                .filter(transaction -> transaction.getType().equals(type))
                .collect(Collectors.toList());
        if (filteredByType.isEmpty()) {
            throw new NoSuchFieldException(NO_TRADES);
        }
        return filteredByType;
    }

    public List<Transaction> getAllFilteredByMinAmount() {
        return getAll().stream()
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList());
    }

    public List<Transaction> getAllFilteredByMaxAmount() {
        return getAll().stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
    }

    public Transaction getOneById(Integer id) throws NoSuchElementException {
        Optional<Transaction> opt = transactionRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new NoSuchElementException("The transaction with the specified ID doesn't exist.");
        }
    }

    public Integer addNew(Transaction transaction) {
        return checkTransaction(transaction);
    }

    public Transaction replaceTransaction(Integer id, Transaction source) {
        Transaction target = getOneById(id);
        target.setProduct(source.getProduct());
        target.setType(source.getType());
        target.setAmount(source.getAmount());
        return transactionRepository.save(target);
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }

    private Integer checkTransaction(Transaction transaction) {
        if (transaction.getProduct() == null) {
            throw new IllegalArgumentException("Null value passed as product.");
        } else if (!transaction.getType().equals(Type.BUY) || (!transaction.getType().equals(Type.SELL))) {
            throw new IllegalArgumentException("The type of transaction mut be BUY or SELL");
        } else if (transaction.getAmount() < 0.1) {
            throw new IllegalArgumentException("Negative values not allowed.");
        } else {
            Transaction saved = transactionRepository.save(transaction);
            return saved.getId();
        }
    }


}