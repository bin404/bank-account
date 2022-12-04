package com.bin404.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class AccountOperation {
    private UUID acountOperationID;
    private LocalDateTime date;
    private OperationType operationType;
    private BigDecimal amount;
    private BigDecimal balance;

    public AccountOperation(AccountOperationBuilder builder) {
        this.acountOperationID = UUID.randomUUID();
        this.date = LocalDateTime.now();
        this.operationType = builder.operationType;
        this.amount = builder.amount;
        this.balance = builder.balance;
    }

    public static class AccountOperationBuilder {

        private OperationType operationType;
        private BigDecimal amount;
        private BigDecimal balance;

        public AccountOperationBuilder(OperationType type, BigDecimal amount, BigDecimal balance) {
            this.operationType = type;
            this.amount = amount;
            this.balance = balance;
        }

        public AccountOperation build() {
            return new AccountOperation(this);
        }
    }

    public UUID getAcountOperationID() {
        return acountOperationID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
