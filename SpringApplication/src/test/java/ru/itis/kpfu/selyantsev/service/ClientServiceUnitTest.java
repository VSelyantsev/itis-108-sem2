package ru.itis.kpfu.selyantsev.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.kpfu.selyantsev.Application;
import ru.itis.kpfu.selyantsev.Service.impl.BaseClientService;
import ru.itis.kpfu.selyantsev.model.newModel.Client;
import ru.itis.kpfu.selyantsev.repository.newRepository.ClientRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(BaseClientService.class)
@ContextConfiguration(classes = Application.class)
public class ClientServiceUnitTest {

    @Autowired
    private BaseClientService clientService;
    @MockBean
    private ClientRepository clientRepository;

    @Test
    public void contextLoads() throws Exception {
        assertThat(clientService).isNotNull();
    }

    @Test
    public void testFindAllClients_thenReturningListOfClients() throws Exception {
        when(clientRepository.findAll()).thenReturn(List.of());
        List<Client> expectedClientList = this.clientService.findAll();

        assertEquals(List.of(), expectedClientList);
        verify(this.clientRepository, times(1)).findAll();
    }

    @Test
    public void testFindClientByName_thenReturningClient() {
        String clientName = "Vladislav";
        clientService.findClientByName(clientName);
        verify(this.clientRepository, times(1)).findClientByClientName(clientName);
    }

    @Test
    public void testDeleteClientById() throws Exception {
        int clientId = 1;
        clientService.deleteClientById(clientId);
        verify(this.clientRepository, times(1)).deleteClientById(clientId);
    }

}
