package com.bin404.domain.model;


import com.bin404.domain.exceptions.IllegalOperationException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Account {
    private UUID uuid;
    private BigDecimal balance;
    private List<AccountOperation> accountOperations = new ArrayList<>();

    public synchronized void addOperation(OperationType operationType, BigDecimal amount) throws IllegalOperationException {
        checkIsPositive(amount);
        this.calculateNewBalance(operationType, amount);
        AccountOperation operation = new AccountOperation.AccountOperationBuilder(operationType, amount, this.balance).build();
        accountOperations.add(operation);
    }

    private void calculateNewBalance(OperationType operationType, BigDecimal amount) throws IllegalOperationException {
        if (OperationType.DEPOSIT.equals(operationType)) {
            balance = balance.add(amount);
        } else if (OperationType.WITHDRAWAL.equals(operationType)) {
            this.checkWithdrawalAgainstAmount(amount);
            balance = balance.subtract(amount);
        } else {
            throw new IllegalOperationException("Requested operation type is not supported");
        }
    }

    private void checkIsPositive(BigDecimal amount) throws IllegalOperationException {
        if (amount.compareTo(amount.abs()) != 0) {
            throw new IllegalOperationException("Amount must be positive");
        }
    }

    private void checkWithdrawalAgainstAmount(BigDecimal amount) throws IllegalOperationException {
        if (balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalOperationException("Withdrawal amount exceed balance");
        }
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<AccountOperation> getAccountOperations() {
        return accountOperations;
    }

    public AccountHistory getHistory() {
        return new AccountHistory(LocalDateTime.now(), this.accountOperations.stream().sorted((o1, o2) -> o2.getDate().
                compareTo(o1.getDate())).limit(20).collect(Collectors.toList()));
    }
}
