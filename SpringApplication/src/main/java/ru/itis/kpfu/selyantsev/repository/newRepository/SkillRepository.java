package ru.itis.kpfu.selyantsev.repository.newRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.selyantsev.model.newModel.Skill;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    List<Skill> findAll();
    Skill findSkillBySkillNameAndSkillDefinition(String skillName, String skillDefinition);
    void deleteSkillBySkillDefinition(String skillDefinition);
}
