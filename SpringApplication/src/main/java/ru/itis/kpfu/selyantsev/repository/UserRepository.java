package ru.itis.kpfu.selyantsev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.selyantsev.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
