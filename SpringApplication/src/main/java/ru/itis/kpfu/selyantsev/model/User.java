package ru.itis.kpfu.selyantsev.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id", nullable = false)
    private Integer userId;

    @Column(name = "account_name", nullable = false)
    private String username;

    @Column(name = "account_email", nullable = false)
    private String userEmail;

    @Column(name = "date_of_birth", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @OneToOne(mappedBy = "user")
    private Passport passport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
