package ru.itis.kpfu.selyantsev.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.kpfu.selyantsev.Application;
import ru.itis.kpfu.selyantsev.Service.ClientService;
import ru.itis.kpfu.selyantsev.dto.request.ClientRequestDto;
import ru.itis.kpfu.selyantsev.util.mapper.ClientMapper;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
@ContextConfiguration(classes = Application.class)
public class ClientControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    public void testSaveUser_thenStatusIsOk() throws Exception {
        mockMvc.perform(post("/client/save")
                .contentType(MediaType.APPLICATION_JSON)
                        .with(user("user").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAllClients() throws Exception {
        ClientRequestDto client = new ClientRequestDto();
        client.setClientEmail("selVlad775@mail.ru");
        client.setClientPassword("12345678");
        given(clientService.findAll()).willReturn(List.of(ClientMapper.toEntity(client)));

        mockMvc.perform(get("/client/findAll")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isOk());
    }

}
