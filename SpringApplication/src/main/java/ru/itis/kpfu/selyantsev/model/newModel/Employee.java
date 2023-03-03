package ru.itis.kpfu.selyantsev.model.newModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fio;

    @Column(nullable = true, length = 20)
    private String jobTitle;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "projects_employees",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id")
    )
    private List<Project> projects;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

}
