package com.bin404.domain.service;

import com.bin404.domain.exceptions.IllegalOperationException;
import com.bin404.domain.exceptions.UnknownAccountException;
import com.bin404.domain.model.Account;
import com.bin404.domain.model.AccountHistory;
import com.bin404.domain.model.OperationType;
import com.bin404.domain.port.AccountPort;

import java.math.BigDecimal;
import java.util.UUID;

public class StandardAccountServiceImpl implements AccountService {
    AccountPort accountRepository;

    public StandardAccountServiceImpl(AccountPort accountRepository) {
        this.accountRepository = accountRepository;
    }

    private Account getAccount(UUID accountId) throws UnknownAccountException {
        return accountRepository.findById(accountId).orElseThrow(() -> new UnknownAccountException("Account does not exists"));
    }

    @Override
    public void addOperation(UUID accountId, OperationType operationType, BigDecimal amount) throws UnknownAccountException, IllegalOperationException {
        Account account = getAccount(accountId);
        account.addOperation(operationType, amount);
        accountRepository.save(account);
    }

    @Override
    public AccountHistory getHistory(UUID accountId) throws UnknownAccountException {
        Account account = getAccount(accountId);
        return account.getHistory();
    }
}
