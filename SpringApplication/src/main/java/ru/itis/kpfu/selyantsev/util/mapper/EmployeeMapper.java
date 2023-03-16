package ru.itis.kpfu.selyantsev.util.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.kpfu.selyantsev.dto.request.EmployeeRequestDto;
import ru.itis.kpfu.selyantsev.model.newModel.Employee;

public class EmployeeMapper {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static Employee toEntity(EmployeeRequestDto employeeRequestDto) {
        return Employee.builder()
                .employeeFio(employeeRequestDto.getEmployeeFio())
                .employeeEmail(employeeRequestDto.getEmployeeEmail())
                .employeePassword(
                        encoder.encode(employeeRequestDto.getEmployeePassword())
                )
                .userEntityEmail(employeeRequestDto.getEmployeeEmail())
                .userEntityPassword(encoder.encode(
                        employeeRequestDto.getEmployeePassword()
                ))
                .build();
    }
}
