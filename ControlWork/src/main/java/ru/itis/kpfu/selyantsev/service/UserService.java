package ru.itis.kpfu.selyantsev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.model.Role;
import ru.itis.kpfu.selyantsev.model.User;
import ru.itis.kpfu.selyantsev.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void saveUser(User user) {
        String password = user.getPassword();
        String encodedPassword = encoder.encode(password);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        userRepository.save(user);
    }
}
