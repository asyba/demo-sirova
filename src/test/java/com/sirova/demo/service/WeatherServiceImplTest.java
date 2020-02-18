package com.sirova.demo.service;

import com.sirova.demo.dto.WeatherDto;
import com.sirova.demo.model.NewWeatherRequest;
import com.sirova.demo.model.Weather;
import com.sirova.demo.repository.WeatherRepository;
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
public class WeatherServiceImplTest {

    @MockBean
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherServiceImpl weatherService;

    @Test
    void getAddNewWeatherReturnWeather() {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setTemperature(11.1F);
        WeatherDto weatherDtoSaved = new WeatherDto();
        weatherDto.setId(1L);
        weatherDto.setTemperature(11.1F);
        when(weatherRepository.save(weatherDto)).thenReturn(weatherDtoSaved);
        NewWeatherRequest weatherRequest = new NewWeatherRequest();
        weatherRequest.setTemperature(11.F);
        Weather weatherResponse = weatherService.addNewWeather(weatherRequest);
        assertEquals(weatherRequest.getTemperature(), weatherResponse.getTemperature());
    }

}
