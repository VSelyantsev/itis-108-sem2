package ru.itis.kpfu.selyantsev.Service;


import ru.itis.kpfu.selyantsev.dto.request.ClientRequestDto;
import ru.itis.kpfu.selyantsev.dto.request.EmployeeRequestDto;
import ru.itis.kpfu.selyantsev.model.newModel.Employee;

import java.util.List;

public interface EmployeeService {

    void saveEmployee(EmployeeRequestDto employeeRequestDto);
//    Employee getEmployeeByEmail(String email);
    List<Employee> findAll();
    Employee findEmployeeByFio(String fio);
    void deleteEmployeeByEmployeeJobTitle(String jobTitle);

    void updateEmployee(EmployeeRequestDto employeeRequestDto);
}
