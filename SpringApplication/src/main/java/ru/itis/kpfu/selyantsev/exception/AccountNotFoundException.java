package ru.itis.kpfu.selyantsev.exception;

import java.util.Optional;

public class AccountNotFoundException extends NotFoundException {
    public AccountNotFoundException(Optional<Integer> accountId) {
        super(String.format("Account with this id = %s not found", accountId));
    }
}
