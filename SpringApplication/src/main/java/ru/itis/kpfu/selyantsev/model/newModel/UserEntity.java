package ru.itis.kpfu.selyantsev.model.newModel;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.kpfu.selyantsev.model.Role;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "user_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = true)
    private Integer id;

    @Column(nullable = true)
    private String userEntityEmail;

    @Column(nullable = true)
    private String userEntityPassword;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_entity_role",
            joinColumns = @JoinColumn(name = "user_entity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    private boolean enabled;

    @Column(length = 64)
    private String verificationCode;
}
