package ru.itis.kpfu.selyantsev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.kpfu.selyantsev.dto.request.CreateUserRequestDto;
import ru.itis.kpfu.selyantsev.dto.response.UserResponseDto;
import ru.itis.kpfu.selyantsev.exception.AccountNotFoundException;
import ru.itis.kpfu.selyantsev.model.User;
import ru.itis.kpfu.selyantsev.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final UserRepository userRepository;
    @GetMapping("/view")
    public ModelAndView showVisualization() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index.html");
        model.addObject(new CreateUserRequestDto());
        return model;
    }

    @PostMapping("/user/create")
    public UserResponseDto createAccount(@Valid @ModelAttribute("userRequestDto") CreateUserRequestDto userRequestDto) {
        return UserResponseDto.fromEntity(userRepository.save(
                User.builder()
                        .username(userRequestDto.getName())
                        .userEmail(userRequestDto.getEmail())
                        .dateOfBirth(userRequestDto.getDateOfBirth())
                        .build()
        ));
    }

    @GetMapping("/user/delete/{userId}")
    public void deleteAccount(@PathVariable(required = false) Optional<Integer> userId) {
        if (userId.isPresent()) {
            userRepository.deleteById(userId.get());
        } else {
            throw new AccountNotFoundException(userId);
        }
    }

    @GetMapping("user/update/{id}/{name}/{email}")
    public User updateAccount(@PathVariable(required = false) Integer id,
                              @PathVariable(required = false) String name,
                              @PathVariable(required = false) String email) {
        User updatedUser = userRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException(Optional.of(id)));
        updatedUser.setUsername(name);
        updatedUser.setUserEmail(email);
        userRepository.save(updatedUser);
        return updatedUser;
    }


    @GetMapping(value = "/users/{userId}")
    public UserResponseDto findUserById(@PathVariable(required = false) Integer userId) {
        return UserResponseDto.fromEntity(
                userRepository.findById(userId)
                        .orElseThrow(() -> new AccountNotFoundException(Optional.of(userId)))
        );
    }

    @GetMapping(value = "/users")
    public List<UserResponseDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::fromEntity)
                .collect(Collectors.toList());
    }


}
