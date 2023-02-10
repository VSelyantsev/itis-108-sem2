package ru.itis.kpfu.selyantsev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.kpfu.selyantsev.exception.AccountNotFoundException;
import ru.itis.kpfu.selyantsev.model.User;
import ru.itis.kpfu.selyantsev.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController{
    private final UserRepository userRepository;

    @GetMapping("/user/{userId}/{name}/{email}")
    public User createAccount(@PathVariable(required = false) Integer userId,
                                @PathVariable(required = false) String name,
                                @PathVariable(required = false) String email) {
        User user = User.builder()
                .name(name)
                .email(email)
                .build();
        userRepository.save(user);
        return user;
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
        updatedUser.setName(name);
        updatedUser.setEmail(email);
        userRepository.save(updatedUser);
        return updatedUser;
    }

    @GetMapping(value = {"/users/{id}", "users"})
    public Iterable<User> user(@PathVariable(required = false) Optional<Integer> id) {
        return id.map(integer -> userRepository.findAllById(List.of(integer)))
                .orElseGet(userRepository::findAll);
    }
}
