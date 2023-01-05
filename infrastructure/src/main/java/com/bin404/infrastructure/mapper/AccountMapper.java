package com.bin404.infrastructure.mapper;


import com.bin404.domain.model.Account;
import com.bin404.infrastructure.entity.AccountEntity;
import org.mapstruct.factory.Mappers;

public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    AccountEntity AcountToAcountEntity(Account account);
    Account AcountEntityToAccount(AccountEntity accountEntity);
}
