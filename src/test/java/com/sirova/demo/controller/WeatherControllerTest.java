package com.sirova.demo.controller;

import com.sirova.demo.model.Weather;
import com.sirova.demo.service.WeatherServiceImpl;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherControllerTest {

    @MockBean
    private WeatherServiceImpl weatherService;

    @Autowired
    private WeatherController weatherController;

    @Test
    void getTotalPlayersShouldReturnHttpStatus200AndTotalInBodyWhenGetPlayersByCompetitionGreaterThan0() throws NotFoundException {
        Weather weather = new Weather();
        weather.setId(1L);
        weather.setTemperature(25.7F);
        when(weatherService.getWeatherById(1L)).thenReturn(weather);
        Weather response = weatherController.getWeatherById(1L);
        assertEquals(25.7F, response.getTemperature(), "Temperate is ok");
    }

}
