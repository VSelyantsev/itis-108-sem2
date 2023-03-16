package ru.itis.kpfu.selyantsev.exception;

import java.util.Optional;

public class EmployeeNotFoundException extends NotFoundException{
    public EmployeeNotFoundException(String employeeEmail) {
        super(String.format("Employee with this email = %s not found", employeeEmail));
    }
}
