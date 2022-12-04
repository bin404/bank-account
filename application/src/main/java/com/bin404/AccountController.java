package com.bin404;

import com.bin404.exceptions.IllegalOperationException;
import com.bin404.exceptions.UnknownAccountException;
import com.bin404.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // TODO - changer les retours de codes
    @PostMapping(value = "/{accountId}/operations")
    void addOperation(@PathVariable UUID accountId, @RequestParam AddOperationRequest request) {
        try {
            accountService.addOperation(accountId, request.getOperationType(), request.getAmount());
        } catch (IllegalOperationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "", e);
        } catch (UnknownAccountException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "", e);
        }
    }

    @GetMapping(value = "/{accountId}/history")
    void getHistory(@PathVariable UUID accountId) {
        try {
            accountService.getHistory(accountId);
        } catch (IllegalOperationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "", e);
        } catch (UnknownAccountException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "", e);
        }
    }
}
