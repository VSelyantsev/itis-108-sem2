package ru.itis.kpfu.selyantsev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.kpfu.selyantsev.Service.impl.BaseClientService;
import ru.itis.kpfu.selyantsev.Service.impl.BaseEmployeeService;
import ru.itis.kpfu.selyantsev.Service.impl.BaseProjectService;
import ru.itis.kpfu.selyantsev.Service.impl.BaseSkillService;
import ru.itis.kpfu.selyantsev.model.newModel.Client;
import ru.itis.kpfu.selyantsev.model.newModel.Project;
import ru.itis.kpfu.selyantsev.model.newModel.Skill;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final BaseClientService clientService;
    private final BaseEmployeeService employeeService;
    private final BaseProjectService projectService;
    private final BaseSkillService skillService;


    @GetMapping("/service/clients")
    public List<Client> getAllClientServices() {
        return clientService
                .findAll()
                .stream()
                .toList();
    }

    @GetMapping("service/employee/delete/{jobTitle}")
    public void deleteEmployeeService(@PathVariable(required = false) String jobTitle) {
        employeeService.deleteEmployeeByJobTitle(jobTitle);
    }

    @GetMapping("service/project/{projectId}")
    public Project getProjectById(@PathVariable(required = false) Integer projectId) {
        return projectService.findProjectById(projectId);
    }

    @GetMapping("service/skill/{skillName}/{skillDefinition}")
    public Skill getSkillByNameAndDefinition(@PathVariable(required = false) String skillName,
                                             @PathVariable(required = false) String skillDefinition) {
        return skillService.findSkillByNameAndDefinition(skillName, skillDefinition);
    }
}
