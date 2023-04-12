package ru.itis.kpfu.selyantsev.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.kpfu.selyantsev.Application;
import ru.itis.kpfu.selyantsev.Service.impl.BaseEmployeeService;
import ru.itis.kpfu.selyantsev.model.newModel.Employee;
import ru.itis.kpfu.selyantsev.repository.newRepository.EmployeeRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(BaseEmployeeService.class)
@ContextConfiguration(classes = Application.class)
public class EmployeeServiceUnitTest {

    @Autowired
    private BaseEmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void contextLoads() throws Exception {
        assertThat(employeeService).isNotNull();
    }

    @Test
    public void testFindAllEmployees_thenReturningListOfEmployees() throws Exception {
        when(employeeRepository.findAll()).thenReturn(List.of());
        List<Employee> expectedEmployeeList = this.employeeService.findAll();

        assertEquals(List.of(), expectedEmployeeList);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testFindEmployeeByFio_thenReturningEmployee() throws Exception {
        String fio = "Selyantsev Vladislav";
        this.employeeService.findEmployeeByFio(fio);
        verify(this.employeeRepository, times(1)).findEmployeeByEmployeeFio(fio);
    }

    @Test
    public void testDeleteByEmployeeJobTitle_thenReturnNothing() throws Exception {
        String employeeJobTitle = "JobTitle";
        employeeService.deleteEmployeeByEmployeeJobTitle(employeeJobTitle);
        verify(this.employeeRepository, times(1)).deleteEmployeeByEmployeeJobTitle(employeeJobTitle);
    }

}
