package ru.itis.kpfu.selyantsev.Service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.Service.SkillService;
import ru.itis.kpfu.selyantsev.model.newModel.Skill;
import ru.itis.kpfu.selyantsev.repository.newRepository.SkillRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BaseSkillService implements SkillService {
    private final SkillRepository skillRepository;
    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }
    @Override
    public Skill findSkillByNameAndDefinition(String skillName, String skillDefinition) {
        return skillRepository.findSkillBySkillNameAndSkillDefinition(skillName, skillDefinition);
    }
    @Override
    public void deleteSkillByDefinition(String skillDefinition) {
        skillRepository.deleteSkillBySkillDefinition(skillDefinition);
    }
}
