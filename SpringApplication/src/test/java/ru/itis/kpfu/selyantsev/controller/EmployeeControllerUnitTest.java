package ru.itis.kpfu.selyantsev.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.kpfu.selyantsev.Application;
import ru.itis.kpfu.selyantsev.Service.EmployeeService;
import ru.itis.kpfu.selyantsev.dto.request.EmployeeRequestDto;
import ru.itis.kpfu.selyantsev.util.mapper.EmployeeMapper;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@ContextConfiguration(classes = Application.class)
public class EmployeeControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testSaveEmployee_thenReturningStatusIsOk() throws Exception{
        mockMvc.perform(post("/employee/save")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user").roles("ADMIN"))
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAllEmployees() throws Exception {
        EmployeeRequestDto employee = new EmployeeRequestDto();
        employee.setEmployeeEmail("selVladEmployee775@mail.ru");
        employee.setEmployeePassword("12345678");
        given(employeeService.findAll()).willReturn(List.of(EmployeeMapper.toEntity(employee)));

        mockMvc.perform(get("/employee/findAll")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        String jobTitle = "somethingNeverMind";

        mockMvc.perform(get("/employee/deleteByJobTitle/{employeeJobTitle}", jobTitle)
                        .with(user("user").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isOk());

        verify(employeeService, times(1)).deleteEmployeeByEmployeeJobTitle(jobTitle);
    }
}
