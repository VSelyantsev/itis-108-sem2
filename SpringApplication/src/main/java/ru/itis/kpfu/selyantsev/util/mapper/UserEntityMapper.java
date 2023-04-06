package ru.itis.kpfu.selyantsev.util.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.kpfu.selyantsev.dto.request.UserEntityRequest;
import ru.itis.kpfu.selyantsev.dto.response.UserEntityResponseDto;
import ru.itis.kpfu.selyantsev.model.newModel.UserEntity;

public class UserEntityMapper {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static UserEntity toEntity(UserEntityRequest userEntityRequest) {
        return UserEntity.builder()
                .userEntityEmail(userEntityRequest.getUserEmail())
                .verificationCode(userEntityRequest.getVerificationCode())
                .userEntityPassword(
                        encoder.encode(userEntityRequest.getUserPassword())
                )
                .build();
    }


    public static UserEntityResponseDto toResponse(UserEntity userEntity) {
        return UserEntityResponseDto.builder()
                .userEmailDto(userEntity.getUserEntityEmail())
                .verificationCode(userEntity.getVerificationCode())
                .role(userEntity.getRoles())
                .build();
    }
}
