package com.bin404.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class AccountHistory {
    private LocalDateTime historyDate;
    private List<AccountOperation> accountOperations;

    public AccountHistory(LocalDateTime now, List<AccountOperation> accountOperations) {
        this.historyDate = now;
        this.accountOperations = accountOperations;
    }

    public LocalDateTime getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(LocalDateTime historyDate) {
        this.historyDate = historyDate;
    }

    public List<AccountOperation> getAccountOperations() {
        return accountOperations;
    }

    public void setAccountOperations(List<AccountOperation> accountOperations) {
        this.accountOperations = accountOperations;
    }
}
