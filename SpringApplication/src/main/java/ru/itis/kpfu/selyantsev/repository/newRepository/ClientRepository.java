package ru.itis.kpfu.selyantsev.repository.newRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.selyantsev.dto.request.ClientRequestDto;
import ru.itis.kpfu.selyantsev.model.newModel.Client;
import ru.itis.kpfu.selyantsev.model.newModel.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findClientById(Integer clientId);
    List<Client> findAll();
    Client findClientByClientName(String clientName);
    void deleteClientById(Integer id);
}
