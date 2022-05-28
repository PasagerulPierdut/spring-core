package com.accenture.springcore.model.Dto;

import com.accenture.springcore.model.TransactionType;

import javax.validation.constraints.Min;

public class SortCriteriaInfo {

    private Integer id;
    private Integer userId;
    private TransactionType transactionType;
    private Double minAmount;
    private Double maxAmount;
    private String startTime;
    private String endTime;
    private Integer pageNo;
    private Integer pageSize;
    private String sortBy;

    public SortCriteriaInfo() {
    }

    public SortCriteriaInfo(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
    }

    public SortCriteriaInfo(Integer id, Integer userId, TransactionType transactionType, Double minAmount, Double maxAmount, String startTime, String endTime, Integer pageNo, Integer pageSize, String sortBy) {
        this.id = id;
        this.userId = userId;
        this.transactionType = transactionType;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public String toString() {
        return "SortCriteriaInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", transactionType=" + transactionType +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", sortBy='" + sortBy + '\'' +
                '}';
    }
}
