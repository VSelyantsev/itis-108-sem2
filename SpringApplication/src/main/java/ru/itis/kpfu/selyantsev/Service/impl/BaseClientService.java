package ru.itis.kpfu.selyantsev.Service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.Service.ClientService;
import ru.itis.kpfu.selyantsev.model.newModel.Client;
import ru.itis.kpfu.selyantsev.repository.newRepository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseClientService implements ClientService {
    private final ClientRepository clientRepository;
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
    @Override
    public Client findClientByName(String clientName) {
        return clientRepository.findClientByClientName(clientName);
    }
    @Override
    public void deleteClientById(Integer id) {
        clientRepository.deleteClientByIdNotNull(id);
    }
}
