package ru.itis.kpfu.selyantsev.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.selyantsev.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getUserByUserEmail(String email);
    Page<User> findAll(Pageable pageable);
    @Query(value = "select * from users u where u.account_name like ?1", nativeQuery = true)
    List<User> findAllByUsername(String name);
    @Query(value = "select u from User u where u.userEmail = :email")
    List<User> findAllByUserEmail(String email);
    @Query(value = "select u from User u where u.username = :name and u.userId = :id")
    User findUserByUsernameAndUserId(@Param("name") String name, @Param("id") Integer id);
    @Query(value = "select u from User u where u.userId in :ids")
    List<User> findAllByIds(@Param("ids") List<Integer> ids);
}
