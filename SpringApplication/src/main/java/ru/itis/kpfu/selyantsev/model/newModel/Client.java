package ru.itis.kpfu.selyantsev.model.newModel;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.kpfu.selyantsev.model.Role;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Client extends UserEntity {

    @Column(length = 20, nullable = true)
    private String clientName;

    @Column(nullable = true)
    private String clientEmail;

    @Column(nullable = true)
    private String clientPassword;

    @OneToOne(mappedBy = "client")
    private Employee employee;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "client_role",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;
}
