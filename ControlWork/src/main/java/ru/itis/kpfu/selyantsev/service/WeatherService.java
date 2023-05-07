package ru.itis.kpfu.selyantsev.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class WeatherService {
    private static final String key = "6faded995f07a67bd6431a5176bb4640";

    public String checkWeatherInCurrentCity(String city) throws Exception{
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + key);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String input;
        while ((input = bufferedReader.readLine()) != null) {
            if (input.contains("temp")) {
                return input;
            }
        }
        return null;
    }
}
