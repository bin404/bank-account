package com.bin404.domain.port;

import com.bin404.domain.model.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountPort {
    Optional<Account> findById(UUID accountUUID);
    void save(Account account);
}
