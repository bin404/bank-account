package com.bin404;


import com.bin404.model.OperationType;

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
