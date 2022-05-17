package com.accenture.springcore.controller;

import com.accenture.springcore.model.Transaction;
import com.accenture.springcore.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> returnTransactionById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(transactionService.getOneById(id));
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product")
    public ResponseEntity<Object> getAllByProduct(@RequestParam("product") String product) {
        try {
            return new ResponseEntity<>(transactionService.getAllFilteredByProduct(product), HttpStatus.OK);
        } catch (NoSuchFieldException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/type")
    public ResponseEntity<Object> getAllByType(@RequestParam("type") String type) {
        try {
            return new ResponseEntity<>(transactionService.getAllFilteredByType(type), HttpStatus.OK);
        } catch (NoSuchFieldException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/minAmount")
    public ResponseEntity<List<Transaction>> getAllByMinAmount(@RequestParam double minAmount) {
        return new ResponseEntity<>(transactionService.getAllFilteredByMinAmount(minAmount), HttpStatus.OK);
    }

    @GetMapping("/maxAmount")
    public ResponseEntity<List<Transaction>> getAllByMaxAmount(@RequestParam double maxAmount) {
        return new ResponseEntity<>(transactionService.getAllFilteredByMaxAmount(maxAmount), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addNewTransaction(@RequestBody Transaction transaction) {
        try {
            return new ResponseEntity<>(transactionService.addNew(transaction), HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity replaceTransaction(@RequestParam Integer id, @RequestBody Transaction transaction) {
        try {
            return new ResponseEntity<>(transactionService.replaceTransaction(id, transaction), HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransaction(@RequestParam Integer id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity(HttpStatus.GONE);
    }
}
