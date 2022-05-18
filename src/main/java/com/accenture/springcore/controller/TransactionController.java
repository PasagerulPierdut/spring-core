package com.accenture.springcore.controller;

import com.accenture.springcore.exception.BadRequestException;
import com.accenture.springcore.exception.EntityNotFoundException;
import com.accenture.springcore.model.Criteria;
import com.accenture.springcore.model.Transaction;
import com.accenture.springcore.model.TransactionType;
import com.accenture.springcore.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

//    @GetMapping
//    public ResponseEntity<List<Transaction>> getAllTransactions() {
//        return ResponseEntity.ok(transactionService.getAll());
//    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAll(
            @RequestParam(required = false) String product,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount) {

        return new ResponseEntity<>(transactionService.getAll(new Criteria(product, type, minAmount, maxAmount)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> returnTransactionById(@PathVariable("id") Integer id) throws EntityNotFoundException {
        return ResponseEntity.ok(transactionService.getOneById(id));
    }

//    @GetMapping("/product")
//    public ResponseEntity<List<Transaction>> getAllByProduct(@RequestParam("product") String product) throws BadRequestException {
//        return new ResponseEntity<>(transactionService.getAllFilteredByProduct(product), HttpStatus.OK);
//    }
//
//    @GetMapping("/type")
//    public ResponseEntity<List<Transaction>> getAllByType(@RequestParam("type") String type) {
//        return new ResponseEntity<>(transactionService.getAllFilteredByType(type), HttpStatus.OK);
//    }
//
//    @GetMapping("/minAmount")
//    public ResponseEntity<List<Transaction>> getAllByMinAmount(@RequestParam double minAmount) {
//        return new ResponseEntity<>(transactionService.getAllFilteredByMinAmount(minAmount), HttpStatus.OK);
//    }
//
//    @GetMapping("/maxAmount")
//    public ResponseEntity<List<Transaction>> getAllByMaxAmount(@RequestParam double maxAmount) {
//        return new ResponseEntity<>(transactionService.getAllFilteredByMaxAmount(maxAmount), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<Transaction> addNewTransaction(@RequestBody Transaction transaction) throws BadRequestException {
        return new ResponseEntity<>(transactionService.addNew(transaction), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> replaceTransaction(@RequestParam Integer id, @RequestBody Transaction transaction) throws EntityNotFoundException {
        return new ResponseEntity<>(transactionService.replaceTransaction(id, transaction), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@RequestParam Integer id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity(HttpStatus.GONE);
    }
}
