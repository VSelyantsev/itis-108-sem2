package ru.itis.kpfu.selyantsev.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.kpfu.selyantsev.Application;
import ru.itis.kpfu.selyantsev.Service.impl.BaseUserEntityService;
import ru.itis.kpfu.selyantsev.config.MailConfig;
import ru.itis.kpfu.selyantsev.dto.response.UserEntityResponseDto;
import ru.itis.kpfu.selyantsev.repository.newRepository.UserEntityRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(BaseUserEntityService.class)
@ContextConfiguration(classes = Application.class)
public class UserEntityServiceUnitTest {

    @Autowired
    private BaseUserEntityService userEntityService;

    @MockBean
    private UserEntityRepository userEntityRepository;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private MailConfig mailConfig;

    @Test
    public void contextLoads() throws Exception {
        userEntityService = new BaseUserEntityService(
                userEntityRepository,
                javaMailSender,
                mailConfig
        );
        assertThat(userEntityService).isNotNull();
    }

    @Test
    public void testFindAllUsers_thenReturnListOfUsers() throws Exception {
        when(userEntityRepository.findAll()).thenReturn(List.of());
        List<UserEntityResponseDto> expectedUsersList = this.userEntityService.findAll();

        assertEquals(List.of(), expectedUsersList);
        verify(this.userEntityRepository, times(1)).findAll();
    }

    @Test
    public void testFindUserById_thenReturningUser() throws Exception {
        int userId = 1;
        this.userEntityService.findById(userId);
        verify(this.userEntityRepository, times(1)).findById(userId);
    }
}
