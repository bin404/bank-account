package com.bin404.entity;

import jakarta.persistence.OneToMany;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class AccountEntity {
    @Id
    private UUID id;

    private BigDecimal balance;

    @OneToMany
    private List<AccountOperation> accountOperations = new ArrayList<>();

}
