package ru.itis.kpfu.selyantsev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.selyantsev.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
