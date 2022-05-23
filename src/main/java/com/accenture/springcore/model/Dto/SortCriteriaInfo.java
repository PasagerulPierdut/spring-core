package com.accenture.springcore.model.Dto;

import com.accenture.springcore.model.TransactionType;
import lombok.Builder;

@Builder
public class SortCriteriaInfo {

    private Integer id;
    private Integer userId;
    private TransactionType transactionType;
    private Double minAmount;
    private Double maxAmount;
    private String startTime;
    private String endTime;
    private boolean confirmed;
    private Integer pageNo;
    private Integer pageSize;
    private String sortBy;

    public SortCriteriaInfo() {
    }

    public SortCriteriaInfo(Integer id, Integer userId, TransactionType transactionType, Double minAmount, Double maxAmount, String startTime, String endTime, boolean confirmed, Integer pageNo, Integer pageSize, String sortBy) {
        this.id = id;
        this.userId = userId;
        this.transactionType = transactionType;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.confirmed = confirmed;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
