package ru.itis.kpfu.selyantsev.Service;


import ru.itis.kpfu.selyantsev.model.newModel.Client;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client findClientByName(String clientName);

    void deleteClientById(Integer id);
}
