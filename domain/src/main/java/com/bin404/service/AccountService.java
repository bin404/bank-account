package com.bin404.service;

import com.bin404.exceptions.IllegalOperationException;
import com.bin404.exceptions.UnknownAccountException;
import com.bin404.model.AccountHistory;
import com.bin404.model.OperationType;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountService {
    void addOperation(UUID accountId, OperationType operationType, BigDecimal amount) throws IllegalOperationException, UnknownAccountException;

    AccountHistory getHistory(UUID accountId) throws IllegalOperationException, UnknownAccountException;
}
