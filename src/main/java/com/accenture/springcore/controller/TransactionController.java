package com.accenture.springcore.controller;

import com.accenture.springcore.exception.customExceptions.EntityNotFoundException;
import com.accenture.springcore.model.Transaction;
import com.accenture.springcore.model.SortCriteria;
import com.accenture.springcore.model.TransactionType;
import com.accenture.springcore.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.GONE;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll(
                                                     SortCriteria sortCriteria) {
        return new ResponseEntity<>(transactionService.findAll(sortCriteria), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> returnTransactionById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(transactionService.getOneById(id));
    }

    @PostMapping
    public ResponseEntity<Transaction> addNewTransaction(
            @RequestBody @Validated Transaction transaction) {
        return new ResponseEntity<>(transactionService.addNew(transaction), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> replaceTransaction(
            @PathVariable("id") Integer id, @RequestBody Transaction transaction)
            throws EntityNotFoundException {
        return new ResponseEntity<>(transactionService.replaceTransaction(id, transaction), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Integer id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>("Product successfully removed.", GONE);
    }
}
