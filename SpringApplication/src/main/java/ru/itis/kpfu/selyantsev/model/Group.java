package ru.itis.kpfu.selyantsev.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

    @Column(nullable = false, length = 16)
    private String groupName;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<User> userList;

    @ManyToMany
    @JoinTable(
            name = "groups_subjects",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "groupId"),
            inverseJoinColumns = @JoinColumn(name = "subject_Id",referencedColumnName = "subjectId")
    )
    private List<Subject> subjectList;

}
