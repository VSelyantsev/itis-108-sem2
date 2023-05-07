package ru.itis.kpfu.selyantsev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageRequestDto {
    private String message;
    private String name;
}
