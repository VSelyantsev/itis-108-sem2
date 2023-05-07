package ru.itis.kpfu.selyantsev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.kpfu.selyantsev.aspect.annotation.CollectData;
import ru.itis.kpfu.selyantsev.model.LogEntity;
import ru.itis.kpfu.selyantsev.service.LogService;
import ru.itis.kpfu.selyantsev.service.WeatherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final WeatherService weatherService;
    private final LogService logService;

    @CollectData
    @GetMapping("weather/{city}")
    public String hitWeather(@PathVariable String city) throws Exception {
        return weatherService.checkWeatherInCurrentCity(city);
    }

    @GetMapping("collectData")
    public ResponseEntity<List<LogEntity>> collectData() {
        return ResponseEntity.ok(logService.collectData());
    }
}
