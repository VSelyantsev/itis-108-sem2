package ru.itis.kpfu.selyantsev.Service;


import ru.itis.kpfu.selyantsev.model.newModel.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findEmployeeByFioAndJobTitle(String fio, String jobTitle);

    void deleteEmployeeByJobTitle(String jobTitle);
}
