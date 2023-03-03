package ru.itis.kpfu.selyantsev.Service;

import ru.itis.kpfu.selyantsev.model.newModel.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> findAll();
    Skill findSkillByNameAndDefinition(String skillName, String skillDefinition);
    void deleteSkillByDefinition(String skillDefinition);
}
