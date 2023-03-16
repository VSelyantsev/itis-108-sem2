package ru.itis.kpfu.selyantsev.Service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.Service.UserService;
import ru.itis.kpfu.selyantsev.dto.request.CreateUserRequestDto;
import ru.itis.kpfu.selyantsev.dto.response.UserResponseDto;
import ru.itis.kpfu.selyantsev.model.User;
import ru.itis.kpfu.selyantsev.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BaseUserService implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserResponseDto.builder()
                        .id(user.getUserId())
                        .name(user.getUsername())
                        .email(user.getUserEmail())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto create(CreateUserRequestDto requestDto) {
        return UserResponseDto.fromEntity(
                userRepository.save(
                        User.builder()
                                .username(requestDto.getName())
                                .userEmail(requestDto.getEmail())
                                .dateOfBirth(requestDto.getDateOfBirth())
                                .build()
                )
        );
    }
}
