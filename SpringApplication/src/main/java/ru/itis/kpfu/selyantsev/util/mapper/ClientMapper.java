package ru.itis.kpfu.selyantsev.util.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.kpfu.selyantsev.dto.request.ClientRequestDto;
import ru.itis.kpfu.selyantsev.model.newModel.Client;

public class ClientMapper {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static Client toEntity(ClientRequestDto clientRequestDto) {
        return Client.builder()
                .clientName(clientRequestDto.getClientName())
                .clientEmail(clientRequestDto.getClientEmail())
                .clientPassword(encoder.encode(
                        clientRequestDto.getClientPassword()
                ))
                .userEntityEmail(clientRequestDto.getClientEmail())
                .userEntityPassword(
                        encoder.encode(clientRequestDto.getClientPassword())
                )
                .build();
    }
}
