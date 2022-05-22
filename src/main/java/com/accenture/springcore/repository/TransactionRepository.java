package com.accenture.springcore.repository;

import com.accenture.springcore.model.Transaction;
import com.accenture.springcore.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction, Integer> {

    //TODO finish it

    /**
     * For demonstration purposes,
     *
     * @param id
     * @param userId
     * @param type
     * @param amount
     * @param createdAt
     * @param confirmed
     * @return
     */
//    @Query(value ="SELECT * FROM transactions t WHERE (:id IS NULL OR t.id= :id) AND (:userId IS NULL OR t.userId= :userId) AND (:transactionType IS NULL OR t.transactionType= :transactionType)AND (:maxAmount IS NULL OR t.amount < :maxAmount) AND (:minAmount IS NULL OR t.amount > : minAmount) AND (:createdAt IS )) ", nativeQuery = true)
//            List<Transaction> findAll(Integer id,  Integer userId, TransactionType transactionType, @Param("maxAmount") double maxAmount,
//                                      @Param("minAmount") double minAmount,
//                                      LocalDateTime createdAt, boolean confirmed);

    List<Transaction> findAll();

}
