package ru.itis.kpfu.selyantsev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.model.LogEntity;
import ru.itis.kpfu.selyantsev.repository.LogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;
    public List<LogEntity> collectData() {
        return logRepository.findAll();
    }
}
