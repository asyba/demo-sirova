package com.sirova.demo.service;

import com.sirova.demo.model.NewWeatherRequest;
import com.sirova.demo.model.Weather;
import javassist.NotFoundException;

import java.util.List;

public interface WeatherService {

    List<Weather> getAllWeather() throws NotFoundException;

    Weather getWeatherById(Long id) throws NotFoundException;

    List<Weather> getAllWeatherByLocation(Float latitude, Float longitude) throws NotFoundException;

    Weather addNewWeather(NewWeatherRequest weatherRequest);

    void modifyWeather(Weather weatherRequest) throws NotFoundException;

    void deleteAllWeather();

}
