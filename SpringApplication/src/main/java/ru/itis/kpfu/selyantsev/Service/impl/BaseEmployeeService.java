package ru.itis.kpfu.selyantsev.Service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.Service.EmployeeService;
import ru.itis.kpfu.selyantsev.model.newModel.Employee;
import ru.itis.kpfu.selyantsev.repository.newRepository.EmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseEmployeeService implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeByFioAndJobTitle(String fio, String jobTitle) {
        return employeeRepository.findEmployeeByFioAndJobTitle(fio, jobTitle);
    }

    @Override
    public void deleteEmployeeByJobTitle(String jobTitle) {
        employeeRepository.deleteEmployeeByJobTitle(jobTitle);
    }
}
