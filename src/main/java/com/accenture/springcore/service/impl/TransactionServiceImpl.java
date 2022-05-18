package com.accenture.springcore.service.impl;

import com.accenture.springcore.exception.BadRequestException;
import com.accenture.springcore.exception.EntityNotFoundException;
import com.accenture.springcore.model.Criteria;
import com.accenture.springcore.model.Transaction;
import com.accenture.springcore.model.TransactionType;
import com.accenture.springcore.repository.TransactionRepository;
import com.accenture.springcore.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final String NO_TRADES = "No trades with the given argument.";

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAll(Criteria criteria) {
        List<Transaction> finalList = transactionRepository.findAll();
        if (criteria.getProduct() != null) {
            finalList = finalList.stream().filter(transaction -> transaction.getProduct().equals(criteria.getProduct())).toList();
            criteria.setFlag(true);
        } else if (criteria.getType() != null && criteria.isFlag() == true) {
            finalList = finalList.stream().filter(transaction -> transaction.getType().equals(criteria.getType())).toList();
        } else if (criteria.getMinAmount() != null) {
            finalList = finalList.stream().filter(transaction -> transaction.getAmount() > criteria.getMinAmount()).toList();
        } else if (criteria.getMaxAmount() != null) {
            finalList = finalList.stream().filter(transaction -> transaction.getAmount() < criteria.getMaxAmount()).toList();
        }
        return finalList;
    }

//    @Override
//    public List<Transaction> getAllFilteredByProduct(String product) throws BadRequestException {
//        List<Transaction> filteredByProduct = getAll().stream()
//                .filter(transaction -> transaction.getProduct().equals(product))
//                .collect(Collectors.toList());
//        if (filteredByProduct.isEmpty()) {
//            throw new BadRequestException(NO_TRADES);
//        } else {
//            return filteredByProduct;
//        }
//    }
//
//    @Override
//    public List<Transaction> getAllFilteredByType(String type) {
//        List<Transaction> filteredByType = getAll().stream()
//                .filter(transaction -> transaction.getType().name().equals(type))
//                .collect(Collectors.toList());
////        if (filteredByType.isEmpty()) {
////            throw new BadRequestException(NO_TRADES);
////        }
//        return filteredByType;
//    }
//
//    @Override
//    public List<Transaction> getAllFilteredByMinAmount(double minAMount) {
//        return getAll().stream()
//                .filter(transaction -> transaction.getAmount() > minAMount)
//                .sorted(Comparator.comparing(Transaction::getAmount))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Transaction> getAllFilteredByMaxAmount(double maxAmount) {
//        return getAll().stream()
//                .filter(transaction -> transaction.getAmount() < maxAmount)
//                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
//                .collect(Collectors.toList());
//    }

    @Override
    public Transaction getOneById(Integer id) throws EntityNotFoundException {
        Optional<Transaction> opt = transactionRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new EntityNotFoundException("The transaction with the specified ID doesn't exist.");
        }
    }

    @Override
    public Transaction addNew(Transaction transaction) throws BadRequestException {
        if (isValid(transaction)) {
            return transactionRepository.save(transaction);
        } else {
            throw new BadRequestException("Invalid transaction.");
        }
    }

    @Override
    public Transaction replaceTransaction(Integer id, Transaction source) throws EntityNotFoundException {
        Transaction target = getOneById(id);
        target.setProduct(source.getProduct());
        target.setType(source.getType());
        target.setAmount(source.getAmount());
        return transactionRepository.save(target);
    }

    @Override
    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }

    private boolean isValid(Transaction transaction) throws BadRequestException {
        if (transaction.getProduct() == null) {
            throw new BadRequestException("Null value passed as product.");
        } else if (!((transaction.getType() == TransactionType.BUY) || (transaction.getType() == TransactionType.SELL))) {
            throw new BadRequestException("The type of transaction mut be BUY or SELL");
        } else if (transaction.getAmount() < 0.1) {
            throw new BadRequestException("Negative/zero values not allowed.");
        } else {
            return true;
        }
    }


}