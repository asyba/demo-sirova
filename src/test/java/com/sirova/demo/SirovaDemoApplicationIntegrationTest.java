package com.sirova.demo;

import com.sirova.demo.controller.WeatherController;
import com.sirova.demo.model.Weather;
import com.sirova.demo.repository.WeatherRepository;
import com.sirova.demo.service.WeatherServiceImpl;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class SirovaDemoApplicationIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeatherRepository weatherRepository;

    @MockBean
    private WeatherServiceImpl weatherService;

    @Test
    void whenAllWeathersThenReturnCorrectResponse() throws Exception {
        List<Weather> weatherResponseList = new ArrayList<>();
        Weather weatherResponse = new Weather();
        weatherResponse.setId(1L);
        weatherResponse.setTemperature(25.50F);
        weatherResponseList.add(weatherResponse);
        when(weatherService.getAllWeather()).thenReturn(weatherResponseList);
        mvc.perform(get("/weather/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].temperature", Is.is(25.50)));
    }

}
