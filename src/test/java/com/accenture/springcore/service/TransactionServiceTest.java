package com.accenture.springcore.service;

import com.accenture.springcore.exception.customExceptions.EntityNotFoundException;
import com.accenture.model.Product;
import com.accenture.model.Transaction;
import com.accenture.springcore.model.TransactionType;
import com.accenture.springcore.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    TransactionRepository mockTransactionRepository;

    @InjectMocks
    TransactionService transactionService;

    @Test
    void getOneById() {
        Mockito.when(mockTransactionRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, ()->
                transactionService.getOneById(1));
        Assertions.assertEquals("The transaction with the specified ID doesn't exist.", exception.getMessage());
    }

    @Test
    void addNew() {
    }

    @Test
    void verifyIfReplaceTransactionPassesParameters() {

        Transaction source = new Transaction( 2, TransactionType.BUY, 0.5d, LocalDateTime.now().minusMonths(1L), true, new Product());
        Transaction target = new Transaction( 2, TransactionType.SELL, 1.5d, LocalDateTime.now().minusMonths(1L), true, new Product());
        Mockito.when(mockTransactionRepository.save(target)).thenReturn(target);
        Mockito.when(mockTransactionRepository.findById(2)).thenReturn(Optional.of(target));

        transactionService.replaceTransaction(2, source);

        Assertions.assertEquals(TransactionType.BUY, target.getTransactionType());
    }

    @Test
    void deleteTransaction() {
    }
}