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
    public ResponseEntity<Object> returnTransactionById(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(transactionService.getOneById(id));
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addNewTransaction(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.addNew(transaction), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity replaceTransaction(@RequestParam Integer id, @RequestBody Transaction transaction) {
        try {
            return new ResponseEntity<>(transactionService.replaceTransaction(id, transaction), HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteTransaction(@RequestParam Integer id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity(HttpStatus.GONE);
    }
}
