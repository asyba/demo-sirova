package com.sirova.demo.service;

import com.sirova.demo.dto.WeatherDto;
import com.sirova.demo.mapper.NewWeatherMapper;
import com.sirova.demo.mapper.WeatherInverseMapper;
import com.sirova.demo.mapper.WeatherMapper;
import com.sirova.demo.model.NewWeatherRequest;
import com.sirova.demo.model.Weather;
import com.sirova.demo.repository.WeatherRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private WeatherRepository weatherRepository;

    public WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public List<Weather> getAllWeather() throws NotFoundException {
        List<WeatherDto> weatherDtoList = weatherRepository.findAll();
        emptyData(weatherDtoList);
        List<Weather> weatherResponseList = new ArrayList<>(weatherDtoList.size());
        for (WeatherDto weatherDto : weatherDtoList) {
            weatherResponseList.add(WeatherInverseMapper.INSTANCE.weatherDtoToToWeather(weatherDto));
        }
        return weatherResponseList;
    }

    @Override
    public Weather getWeatherById(Long id) throws NotFoundException {
        Optional<WeatherDto> weatherDtoOptional = weatherRepository.findById(id);
        if(!weatherDtoOptional.isPresent()) {
            throw new NotFoundException("No Weather data");
        }
        return WeatherInverseMapper.INSTANCE.weatherDtoToToWeather(weatherDtoOptional.get());
    }

    @Override
    public List<Weather> getAllWeatherByLocation(Float latitude, Float longitude) throws NotFoundException {
        List<WeatherDto> weatherDtoList = weatherRepository.findByLatitudeAndLongitude(latitude, longitude);
        emptyData(weatherDtoList);
        List<Weather> weatherResponseList = new ArrayList<>(weatherDtoList.size());
        for (WeatherDto weatherDto : weatherDtoList) {
            weatherResponseList.add(WeatherInverseMapper.INSTANCE.weatherDtoToToWeather(weatherDto));
        }
        return weatherResponseList;
    }

    @Override
    public Weather addNewWeather(NewWeatherRequest weatherRequest) {
        WeatherDto weatherDto = NewWeatherMapper.INSTANCE.weatherRequestToWeatherDto(weatherRequest);
        Date date = new Date();
        weatherDto.setDateRecorded(date);
        weatherDto.setDateUpdated(date);
        weatherRepository.save(weatherDto);
        return WeatherInverseMapper.INSTANCE.weatherDtoToToWeather(weatherDto);
    }

    @Override
    public void modifyWeather(Weather weatherRequest) throws NotFoundException {
        Optional<WeatherDto> weatherDtoOptional = weatherRepository.findById(weatherRequest.getId());
        if(!weatherDtoOptional.isPresent()) {
            throw new NotFoundException("No Weather data");
        }
        //WeatherDto weatherDto = weatherDtoOptional.get();
        //weatherDto.setTemperature(weatherRequest.getTemperature());
        //weatherDto.setLatitude();
        //weatherDto.setLongitude();
        //weatherDto
        //weatherDto.setDateUpdated(new Date());
        weatherRepository.save(WeatherMapper.INSTANCE.updateWeather(weatherDtoOptional.get(), weatherRequest));
    }

    @Override
    public void deleteAllWeather() {
        weatherRepository.deleteAll();
    }

    private void emptyData(List<WeatherDto> weatherDtoList) throws NotFoundException {
        if (weatherDtoList.isEmpty()) {
            throw new NotFoundException("No Weather data");
        }
    }

}
