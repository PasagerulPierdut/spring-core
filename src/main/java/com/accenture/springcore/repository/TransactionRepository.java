package com.accenture.springcore.repository;

import com.accenture.model.Transaction;
import com.accenture.springcore.model.TransactionType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction, Integer> {

    @Query("SELECT t FROM Transaction t " +
            "WHERE" +
            " (:id IS NULL OR t.id = :id) AND (:userId IS NULL OR t.userId = :userId) " +
            "AND (:transactionType IS NULL OR t.transactionType = :transactionType) " +
            "AND (:maxAmount IS NULL OR t.amount < :maxAmount) " +
            "AND (:minAmount IS NULL OR t.amount > :minAmount) " +
            "AND (:startDateTime IS NULL OR (:endDateTime IS NULL OR (t.createdAt  BETWEEN :startDateTime AND :endDateTime)))")
    List<Transaction> findAll(@Param("id") Integer id, @Param("userId") Integer userId,
                              @Param("transactionType") TransactionType transactionType,
                              @Param("maxAmount") Double maxAmount,
                              @Param("minAmount") Double minAmount,
                              @Param("startDateTime") LocalDateTime startDateTime,
                              @Param("endDateTime") LocalDateTime endDateTime,
                              Pageable paging);

@Query("SELECT t FROM Transaction t WHERE t.confirmed = 'true'")
List<Transaction> findAllByConfirmationStatus();
}
