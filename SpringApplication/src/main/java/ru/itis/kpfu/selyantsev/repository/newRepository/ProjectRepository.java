package ru.itis.kpfu.selyantsev.repository.newRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.selyantsev.model.newModel.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAll();
    Project findProjectById(Integer id);
    void deleteProjectByProjectName(String name);

}
