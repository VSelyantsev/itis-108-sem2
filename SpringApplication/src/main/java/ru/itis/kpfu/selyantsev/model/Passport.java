package ru.itis.kpfu.selyantsev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "passports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer passportId;

    @Column(nullable = false)
    private Integer passportSeries;

    @Column(nullable = false)
    private Integer passportNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
