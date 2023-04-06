package ru.itis.kpfu.selyantsev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.kpfu.selyantsev.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntityRequest {

    @Email
    @NotBlank(message = "Email shouldn't be blank")
    private String userEmail;

    @NotBlank(message = "Password shouldn't be blank")
    private String userPassword;

    private String verificationCode;
}
