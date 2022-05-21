package com.accenture.springcore.repository;

import com.accenture.springcore.model.Transaction;
import com.accenture.springcore.model.TransactionType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDao {

    private final EntityManager entityManager;

    public TransactionDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

   public List<Transaction> findAllByCriteria(Integer id, Integer userId, String product,
                                        TransactionType transactionType, double minAmount, double maxAmount,
                                        LocalDateTime startDateTime, LocalDateTime endDateTime, Boolean confirmed) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);

        Root<Transaction> origin = criteriaQuery.from(Transaction.class);
        List<Predicate> predicates = new ArrayList<>();
        if (id != null) {
            predicates.add(criteriaBuilder.equal(origin.get("id"), id));
        }
        if (userId != null) {
            predicates.add(criteriaBuilder.equal(origin.get("userId"), userId));
        }
        if (product != null) {
            predicates.add(criteriaBuilder.like(origin.get("product"), "%" + product + "%"));
        }
        if (transactionType != null && (transactionType.equals(TransactionType.SELL) || transactionType.equals(TransactionType.BUY))) {

            predicates.add(criteriaBuilder.equal(origin.get("transactionType"), transactionType));
        }
        if ((Double)minAmount != null && minAmount > 0) {
            predicates.add(criteriaBuilder.greaterThan(origin.get("amount"), minAmount));
        }
        if((Double)maxAmount != null && maxAmount > 0) {
            predicates.add(criteriaBuilder.lessThan(origin.get("amount"), maxAmount));
        }
        if ((startDateTime != null && endDateTime == null) && startDateTime.isBefore(LocalDateTime.now()))  {
            predicates.add(criteriaBuilder.between(origin.get("createdAt"), startDateTime, LocalDateTime.now()));
        }
        if((startDateTime != null) && (endDateTime != null) && (endDateTime.isAfter(startDateTime))) {
            predicates.add(criteriaBuilder.between(origin.get("createdAt"), startDateTime, endDateTime));
        }
        if (confirmed != null) {
            predicates.add(criteriaBuilder.equal(origin.get("confirmed"), confirmed));
        }
        //TODO clarify "confirmed/ unconfirmed" adding option
        Predicate[] searchParams = new Predicate[predicates.size()];
        predicates.toArray(searchParams);
        criteriaQuery.where(searchParams);

         TypedQuery<Transaction> q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
}
