package com.bin404.infrastructure.adapter;

import com.bin404.infrastructure.entity.AccountEntity;
import com.bin404.infrastructure.mapper.AccountMapper;
import com.bin404.domain.model.Account;
import org.springframework.stereotype.Component;
import com.bin404.domain.port.AccountPort;
import com.bin404.infrastructure.repository.AccountRepository;

import java.util.Optional;
import java.util.UUID;

@Component
public class AccountJpaAdapter implements AccountPort {

    private final AccountRepository repository;

    public AccountJpaAdapter(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Account> findById(UUID accountUUID) {
        Optional<AccountEntity> optional = repository.findById(accountUUID);
        return optional.map(AccountMapper.INSTANCE::AcountEntityToAccount);
    }

    @Override
    public void save(Account account) {
        AccountEntity entity = AccountMapper.INSTANCE.AcountToAcountEntity(account);
        repository.save(entity);
    }

}
