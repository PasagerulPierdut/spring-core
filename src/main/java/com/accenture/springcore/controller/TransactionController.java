package com.accenture.springcore.controller;

import com.accenture.springcore.exception.customExceptions.EntityNotFoundException;
import com.accenture.model.Product;
import com.accenture.model.Transaction;
import com.accenture.springcore.model.Dto.SortCriteriaInfo;
import com.accenture.springcore.model.TransactionType;
import com.accenture.springcore.service.TransactionService;
import com.accenture.springcore.utils.validator.ValidTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.GONE;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService transactionService;

    private final JmsTemplate jmsTemplate;

    public TransactionController(TransactionService transactionService, JmsTemplate jmsTemplate) {
        this.transactionService = transactionService;
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll(@Valid SortCriteriaInfo sortCriteriaInfo) {
        return new ResponseEntity<>(transactionService.findAll(sortCriteriaInfo), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getOneByID(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(transactionService.getOneById(id));
    }

    @PostMapping
    public ResponseEntity<Transaction> addNewTransaction(
            @RequestBody @ValidTransaction Transaction transaction) {
        jmsTemplate.convertAndSend("TransactionQueue", transaction);
        System.out.println("sending a transaction");
        return new ResponseEntity<>(transactionService.addNew(transaction), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> replaceTransaction(
            @PathVariable("id") Integer id, @RequestBody @ValidTransaction Transaction transaction)
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
