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
public class ClientRequestDto {

    @NotBlank(message = "Name shouldn't be blank")
    @Size(min = 4, max = 20)
    private String clientName;

    @NotBlank
    @Email(message = "Email should be correct")
    private String clientEmail;

    @NotBlank(message = "Password shouldn't be blank")
    @Size(min = 4, max = 12)
    private String clientPassword;

}
