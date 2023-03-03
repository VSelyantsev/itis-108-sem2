package ru.itis.kpfu.selyantsev.repository.newRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.selyantsev.model.newModel.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAll();
    Client findClientByClientName(String clientName);
    void deleteClientByIdNotNull(Integer id);

}
