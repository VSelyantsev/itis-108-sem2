package ru.itis.kpfu.selyantsev.Service;

import ru.itis.kpfu.selyantsev.dto.request.UserEntityRequest;
import ru.itis.kpfu.selyantsev.dto.response.UserEntityResponseDto;
import ru.itis.kpfu.selyantsev.dto.response.UserResponseDto;
import ru.itis.kpfu.selyantsev.model.newModel.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserEntityService {

    List<UserEntityResponseDto> findAll();

    Optional<UserEntityResponseDto> findById(Integer id);

    UserEntityResponseDto create(UserEntityRequest userEntityRequest, String url);

    boolean verify(String verificationCode);

    void sendVerificationEmail(String mail, String name, String code, String url);

    Optional<UserEntityResponseDto> getUserByEmail(String email);
}
