package ru.itis.kpfu.selyantsev.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RefreshJwtRequest {
    public String refreshToken;
}