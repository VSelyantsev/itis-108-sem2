package ru.itis.kpfu.selyantsev.model.newModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity(name = "skills")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String skillName;

    @Column(nullable = false)
    private String skillDefinition;

    @OneToMany(mappedBy = "skill")
    private List<Employee> employeeSkills;
}
