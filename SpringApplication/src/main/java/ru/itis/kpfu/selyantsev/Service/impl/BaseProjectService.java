package ru.itis.kpfu.selyantsev.Service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.Service.ProjectService;
import ru.itis.kpfu.selyantsev.model.newModel.Project;
import ru.itis.kpfu.selyantsev.repository.newRepository.ProjectRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BaseProjectService implements ProjectService {
    private final ProjectRepository projectRepository;
    @Override
    public List<Project> findALl() {
        return projectRepository.findAll();
    }
    @Override
    public Project findProjectById(Integer id) {
        return projectRepository.findProjectById(id);
    }
    @Override
    public void deleteProjectByName(String projectName) {
        projectRepository.deleteProjectByProjectName(projectName);
    }
}
