package com.accenture.springcore.controller;

import com.accenture.springcore.exception.customExceptions.EntityNotFoundException;
import com.accenture.springcore.model.Product;
import com.accenture.springcore.model.Transaction;
import com.accenture.springcore.model.TransactionType;
import com.accenture.springcore.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "userId", required = false) Integer userId,
            @RequestParam(name = "transactionType", required = false) TransactionType transactionType,
            @RequestParam(name = "minAmount", required = false) Double minAmount,
            @RequestParam(name = "maxAmount", required = false) Double maxAmount,
            @RequestParam(name = "startDateTime", required = false) String startDateTime,
            @RequestParam(name = "endDateTime", required = false) String endDateTime,
            @RequestParam(name = "confirmed", required = false) boolean confirmed
    ) {
        return new ResponseEntity<>(transactionService.findAll(id, userId, transactionType,
                minAmount, maxAmount, startDateTime, endDateTime, confirmed), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getOneByID(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(transactionService.getOneById(id));
    }

    @PostMapping
    public ResponseEntity<Transaction> addNewTransaction(
            @RequestBody Transaction transaction) {
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

    @GetMapping("/reports/type")
    public ResponseEntity<Map<TransactionType, List<Transaction>>> getAllTransactionOfType() {
        return new ResponseEntity<>(transactionService.findAllByTransactionType(), OK);
    }

    @GetMapping("/reports/product")
    public ResponseEntity<Map<Product, List<Transaction>>> getAllTransactionOfProduct() {
        return new ResponseEntity<>(transactionService.findAllByProduct(), HttpStatus.OK);
    }
}
