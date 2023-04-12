package ru.itis.kpfu.selyantsev.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientResponseDto {
    private String clientName;
    private String clientEmail;
}
