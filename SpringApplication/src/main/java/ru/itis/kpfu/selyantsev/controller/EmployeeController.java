package ru.itis.kpfu.selyantsev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.kpfu.selyantsev.Service.EmployeeService;
import ru.itis.kpfu.selyantsev.dto.request.EmployeeRequestDto;
import ru.itis.kpfu.selyantsev.model.newModel.Employee;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("employee/save")
    public String saveEmployee(@ModelAttribute("employeeRequestDto") EmployeeRequestDto employeeRequestDto) {
        employeeService.saveEmployee(employeeRequestDto);
        return "sign_up_success";
    }

    @GetMapping("employee/findAll")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("employee/findByFio/{employeeFio}")
    public Employee getEmployeeByFio(@PathVariable String employeeFio) {
        return employeeService.findEmployeeByFio(employeeFio);
    }

    @GetMapping("employee/deleteByJobTitle/{employeeJobTitle}")
    public void deleteEmployeeByJobTitle(@PathVariable String employeeJobTitle) {
        employeeService.deleteEmployeeByEmployeeJobTitle(employeeJobTitle);
    }

}
