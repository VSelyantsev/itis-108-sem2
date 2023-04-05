package ru.itis.kpfu.selyantsev.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.kpfu.selyantsev.Service.UserEntityService;
import ru.itis.kpfu.selyantsev.dto.request.UserEntityRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class UserEntityController {

    private final UserEntityService userEntityService;

    @PostMapping("/user/save")
    public String createUser(@ModelAttribute UserEntityRequest userEntityRequest, HttpServletRequest request) {
        String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
        userEntityService.create(userEntityRequest, url);
        return "sign_up_success";
    }

    @PostMapping("/verification")
    public String verify(@Param("code") String code) {
        if (userEntityService.verify(code)) {
            return "verification_success";
        } else {
            return "verification_failed";
        }
    }
}
