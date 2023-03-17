package ru.itis.kpfu.selyantsev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.kpfu.selyantsev.dto.request.ClientRequestDto;
import ru.itis.kpfu.selyantsev.dto.request.EmployeeRequestDto;

@Controller
public class BaseMainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/client/sign_up")
    public String clientSignUp(Model model) {
        model.addAttribute("clientRequestDto", new ClientRequestDto());
        return "sign_up_for_client";
    }

    @GetMapping("/employee/sign_up")
    public String employeeSignUp(Model model) {
        model.addAttribute("employeeRequestDto", new EmployeeRequestDto());
        return "sign_up_for_employee";
    }

    @GetMapping("/profile/client")
    public String clientUpdate(Model model) {
        model.addAttribute("clientRequestDto", new ClientRequestDto());
        return "personal_area_for_client";
    }


    @GetMapping("/profile/employee")
    public String employeeUpdate(Model model) {
        model.addAttribute("employeeRequestDto", new EmployeeRequestDto());
        return "personal_area_for_employee";
    }
    // also add sign up for admin
}
