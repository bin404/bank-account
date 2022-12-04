package com.bin404.port;

import com.bin404.model.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountPort {
    Optional<Account> findById(UUID accountUUID);
    void save(Account account);
}
