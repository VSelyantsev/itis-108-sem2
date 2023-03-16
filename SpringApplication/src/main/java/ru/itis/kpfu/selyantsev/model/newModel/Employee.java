package ru.itis.kpfu.selyantsev.model.newModel;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.kpfu.selyantsev.model.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Set;

@Entity(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Employee extends UserEntity {

    @Column(name = "employee_fio", nullable = true)
    private String employeeFio;

    @Column(name = "employee_job_title", length = 20, nullable = true)
    private String employeeJobTitle;

    @Email
    @Column(name = "employee_email", nullable = true)
    private String employeeEmail;

    @Column(name = "employee_password", nullable = true)
    private String employeePassword;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "projects_employees",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id")
    )
    private List<Project> projects;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;
}
