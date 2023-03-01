package ru.itis.kpfu.selyantsev.dto.response;

import lombok.Builder;
import lombok.Data;
import ru.itis.kpfu.selyantsev.model.User;

import java.time.LocalDate;

@Data
@Builder
public class UserResponseDto {

    private Integer id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .name(user.getUsername())
                .email(user.getUserEmail())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }

}
