package ru.itis.kpfu.selyantsev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.kpfu.selyantsev.Service.ClientService;
import ru.itis.kpfu.selyantsev.dto.request.ClientRequestDto;
import ru.itis.kpfu.selyantsev.model.newModel.Client;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/client/save")
    public String saveClient(@ModelAttribute("clientRequestDto") ClientRequestDto clientRequestDto) {
        clientService.saveClient(clientRequestDto);
        return "sign_up_success";
    }

    @PostMapping("/client/update")
    public String updateClient(@ModelAttribute("clientRequestDto") ClientRequestDto clientRequestDto) {
        clientService.updateClient(clientRequestDto);
        return "update_success";
    }

    @GetMapping("client/findAll")
    public List<Client> getClients() {
        return clientService.findAll();
    }

    @GetMapping("client/findByName/")
    public Client getClientByName(String clientName) {
        return clientService.findClientByName(clientName);
    }

    @GetMapping("client/deleteById/{id}")
    public void deleteClientById(@PathVariable Integer id) {
        clientService.deleteClientById(id);
    }
}
