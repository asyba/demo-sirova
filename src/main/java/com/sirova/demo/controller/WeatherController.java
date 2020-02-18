package com.sirova.demo.controller;

import com.sirova.demo.model.NewWeatherRequest;
import com.sirova.demo.model.Weather;
import com.sirova.demo.service.WeatherService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Slf4j
public class WeatherController {

    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @RequestMapping(value = "/weather/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Weather> getAllWeather() throws NotFoundException {
        return weatherService.getAllWeather();
    }

    @RequestMapping(value = "/weather/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Weather getWeatherById(@PathVariable Long id) throws NotFoundException {
        return weatherService.getWeatherById(id);
    }

    @RequestMapping(value = "/weather", method = RequestMethod.GET, params = {"lat", "lon"})
    @ResponseStatus(HttpStatus.OK)
    public List<Weather> getAllWeatherByLocation(@RequestParam Float lat,
                                                         @RequestParam Float lon) throws NotFoundException {
        return weatherService.getAllWeatherByLocation(lat, lon);
    }

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Weather addNewWeather(@RequestBody NewWeatherRequest weather) {
        return weatherService.addNewWeather(weather);
    }

    @RequestMapping(value = "/weather", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void modifyWeather(@RequestBody Weather weather) throws NotFoundException {
        weatherService.modifyWeather(weather);
    }

    @RequestMapping(value = "/erase", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllWeather() {
        weatherService.deleteAllWeather();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Weather> handleNotFoundException() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
