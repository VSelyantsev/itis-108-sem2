package ru.itis.kpfu.selyantsev.Service;

import ru.itis.kpfu.selyantsev.model.newModel.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findALl();
    Project findProjectById(Integer id);
    void deleteProjectByName(String projectName);

}
