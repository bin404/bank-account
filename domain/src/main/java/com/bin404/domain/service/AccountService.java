package com.bin404.domain.service;

import com.bin404.domain.exceptions.IllegalOperationException;
import com.bin404.domain.exceptions.UnknownAccountException;
import com.bin404.domain.model.AccountHistory;
import com.bin404.domain.model.OperationType;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountService {
    void addOperation(UUID accountId, OperationType operationType, BigDecimal amount) throws IllegalOperationException, UnknownAccountException;

    AccountHistory getHistory(UUID accountId) throws IllegalOperationException, UnknownAccountException;
}
