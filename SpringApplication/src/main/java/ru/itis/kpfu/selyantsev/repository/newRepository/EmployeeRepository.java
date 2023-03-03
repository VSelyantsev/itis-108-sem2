package ru.itis.kpfu.selyantsev.repository.newRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.selyantsev.model.newModel.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAll();
    Employee findEmployeeByFioAndJobTitle(String fio, String jobTitle);
    void deleteEmployeeByJobTitle(String jobTitle);
}
