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
                .filter(transaction -> transaction.getType().name().equals(type))
                .collect(Collectors.toList());
        if (filteredByType.isEmpty()) {
            throw new NoSuchFieldException(NO_TRADES);
        }
        return filteredByType;
    }

    public List<Transaction> getAllFilteredByMinAmount(double minAMount) {
        return getAll().stream()
                .filter(transaction -> transaction.getAmount() > minAMount)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList());
    }

    public List<Transaction> getAllFilteredByMaxAmount(double maxAmount) {
        return getAll().stream()
                .filter(transaction -> transaction.getAmount() < maxAmount)
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

    public Transaction addNew(Transaction transaction) {
        return transactionRepository.save(checkTransaction(transaction));
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

    private Transaction checkTransaction(Transaction transaction) {
        boolean aTrue = true;
        if (transaction.getProduct() == null) {
            throw new IllegalArgumentException("Null value passed as product.");
        } else if (((transaction.getType() == Type.BUY) || (transaction.getType() == Type.SELL)) != aTrue) {
            throw new IllegalArgumentException("The type of transaction mut be BUY or SELL");
        } else if (transaction.getAmount() < 0.1) {
            throw new IllegalArgumentException("Negative/zero values not allowed.");
        } else {
            return transaction;
        }
    }


}