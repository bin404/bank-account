package com.bin404.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class AccountOperation {
    private UUID acountOperationID;
    private LocalDateTime date;
    private OperationTypeEntity operationType;
    private BigDecimal amount;
    private BigDecimal balance;
}
