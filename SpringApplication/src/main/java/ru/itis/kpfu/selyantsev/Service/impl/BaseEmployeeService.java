package ru.itis.kpfu.selyantsev.Service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.Service.EmployeeService;
import ru.itis.kpfu.selyantsev.dto.request.EmployeeRequestDto;
import ru.itis.kpfu.selyantsev.model.Role;
import ru.itis.kpfu.selyantsev.model.newModel.Employee;
import ru.itis.kpfu.selyantsev.repository.newRepository.EmployeeRepository;
import ru.itis.kpfu.selyantsev.util.mapper.EmployeeMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BaseEmployeeService implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public void saveEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee newEmployee = EmployeeMapper.toEntity(employeeRequestDto);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.builder()
                        .name("ROLE_EMPLOYEE")
                        .build());
        newEmployee.setRoles(roles);
        employeeRepository.save(newEmployee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeByFio(String fio) {
        return employeeRepository.findEmployeeByEmployeeFio(fio);
    }

    @Override
    public void deleteEmployeeByEmployeeJobTitle(String jobTitle) {
        employeeRepository.deleteEmployeeByEmployeeJobTitle(jobTitle);
    }
}
