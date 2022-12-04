package com.bin404.mapper;


import com.bin404.entity.AccountEntity;
import com.bin404.model.Account;
import org.mapstruct.factory.Mappers;

public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    AccountEntity AcountToAcountEntity(Account account);
    Account AcountEntityToAccount(AccountEntity accountEntity);
}
