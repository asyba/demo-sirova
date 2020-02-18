package com.sirova.demo.repository;

import com.sirova.demo.dto.WeatherDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherDto, Long> {

    List<WeatherDto> findByLatitudeAndLongitude(Float latitude, Float longitude);
}
