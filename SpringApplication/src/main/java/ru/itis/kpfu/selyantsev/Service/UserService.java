package ru.itis.kpfu.selyantsev.Service;

import ru.itis.kpfu.selyantsev.dto.request.CreateUserRequestDto;
import ru.itis.kpfu.selyantsev.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> findAll();

    UserResponseDto create(CreateUserRequestDto requestDto);

}
