package ru.itis.kpfu.selyantsev.dto.response;

import lombok.Builder;
import lombok.Getter;
import ru.itis.kpfu.selyantsev.model.Role;

import java.util.Set;

@Getter
@Builder
public class UserEntityResponseDto {
    private String userEmailDto;
    private String verificationCode;
    private Set<Role> role;
}
