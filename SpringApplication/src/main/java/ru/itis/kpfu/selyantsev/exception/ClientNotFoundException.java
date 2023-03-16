package ru.itis.kpfu.selyantsev.exception;

import java.util.Optional;

public class ClientNotFoundException extends NotFoundException {
    public ClientNotFoundException(String clientEmail) {
        super(String.format("Client with this email = %s not found", clientEmail));
    }
}
