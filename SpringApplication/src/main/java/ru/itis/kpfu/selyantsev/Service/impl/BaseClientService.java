package ru.itis.kpfu.selyantsev.Service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.Service.ClientService;
import ru.itis.kpfu.selyantsev.dto.request.ClientRequestDto;
import ru.itis.kpfu.selyantsev.model.Role;
import ru.itis.kpfu.selyantsev.model.newModel.Client;
import ru.itis.kpfu.selyantsev.repository.newRepository.ClientRepository;
import ru.itis.kpfu.selyantsev.util.mapper.ClientMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BaseClientService implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public void saveClient(ClientRequestDto clientRequestDto) {
        Client newClient = ClientMapper.toEntity(clientRequestDto);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.builder()
                        .name("ROLE_CLIENT")
                        .build());
        newClient.setRoles(roles);
        clientRepository.save(newClient);
    }

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
        clientRepository.deleteClientById(id);
    }

    @Override
    public void updateClient(ClientRequestDto clientRequestDto) {
        Client anotherRequest = ClientMapper.toEntity(clientRequestDto);
        Client newClient = clientRepository.findClientByClientName(anotherRequest.getClientName());
        newClient.setClientEmail(anotherRequest.getClientName());
        newClient.setClientPassword(anotherRequest.getClientPassword());
        newClient.setUserEntityEmail(anotherRequest.getUserEntityEmail());
        newClient.setUserEntityPassword(anotherRequest.getClientPassword());
        clientRepository.save(newClient);
    }

}
