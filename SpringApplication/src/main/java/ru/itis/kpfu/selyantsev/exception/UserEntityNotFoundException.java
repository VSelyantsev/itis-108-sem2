package ru.itis.kpfu.selyantsev.exception;

public class UserEntityNotFoundException extends NotFoundException{
    public UserEntityNotFoundException(String email) {
        super(String.format("User with this email: %s not found", email));
    }
}
