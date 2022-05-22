package com.accenture.springcore.repository;

import com.accenture.springcore.model.Transaction;
import com.accenture.springcore.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDao {

    private EntityManager entityManager;

    @Autowired
    public TransactionDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Transaction> findAllByCriteria(Integer id, Integer userId,
                                               TransactionType transactionType, Double minAmount, Double maxAmount,
                                               LocalDateTime startTime, LocalDateTime endTime, Boolean confirmed) {

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
        if (transactionType != null && (transactionType.equals(TransactionType.SELL) || transactionType.equals(TransactionType.BUY))) {

            predicates.add(criteriaBuilder.equal(origin.get("transactionType"), transactionType));
        }
        if (minAmount != null && minAmount.doubleValue() > 0.0) {
            predicates.add(criteriaBuilder.greaterThan(origin.get("amount"), minAmount));
        }
        if (maxAmount != null && maxAmount.doubleValue() > 0.0) {
            predicates.add(criteriaBuilder.lessThan(origin.get("amount"), maxAmount));
        }
        if ((startTime != null) && (endTime == null) && (startTime.isBefore(LocalDateTime.now()))) {
            predicates.add(criteriaBuilder.between(origin.get("createdAt"), startTime, LocalDateTime.now()));
        }
        if ((startTime != null) && (endTime != null) && (endTime.isAfter(startTime))) {
            predicates.add(criteriaBuilder.between(origin.get("createdAt"), startTime, endTime));
        }
        if (confirmed != null) {
            predicates.add(criteriaBuilder.equal(origin.get("confirmed"), confirmed));
        }
        Predicate[] searchParams = new Predicate[predicates.size()];
        predicates.toArray(searchParams);
        criteriaQuery.where(searchParams);

        TypedQuery<Transaction> q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
}

