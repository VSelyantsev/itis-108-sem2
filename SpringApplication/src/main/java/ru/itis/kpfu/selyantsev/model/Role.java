package ru.itis.kpfu.selyantsev.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "account_role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "account_role_name", nullable = false)
    private String name;

}

