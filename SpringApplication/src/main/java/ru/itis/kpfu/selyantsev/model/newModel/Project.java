package ru.itis.kpfu.selyantsev.model.newModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String projectName;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    private List<Employee> employees;

}
