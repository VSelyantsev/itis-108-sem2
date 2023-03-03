package ru.itis.kpfu.selyantsev.model.newModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String clientName;

    @Column(nullable = false)
    private String clientEmail;

    @OneToOne(mappedBy = "client")
    private Employee employee;

}
