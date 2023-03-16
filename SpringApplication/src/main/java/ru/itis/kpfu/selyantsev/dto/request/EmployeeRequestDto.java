package ru.itis.kpfu.selyantsev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequestDto {

    @NotBlank(message = "Fio shouldn't be blank")
    @Size(min = 5, max = 100)
    private String employeeFio;

    @NotBlank(message = "Job Title shouldn't be blank")
    @Size(min = 2, max = 20)
    private String employeeJobTitle;

    @NotBlank
    @Email(message = "Email should be correct")
    private String employeeEmail;

    @Size(min = 4, max = 12)
    @NotBlank(message = "Password shouldn't be blank")
    private String employeePassword;
}
