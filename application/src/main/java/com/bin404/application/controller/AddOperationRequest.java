package com.bin404.application.controller;

import com.bin404.domain.model.OperationType;

import java.math.BigDecimal;

public class AddOperationRequest {
    private OperationType operationType;
    private BigDecimal amount;

    public OperationType getOperationType() {
        return operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
