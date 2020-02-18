package com.sirova.demo.mapper;

import com.sirova.demo.dto.WeatherDto;
import com.sirova.demo.model.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface WeatherInverseMapper {

    WeatherInverseMapper INSTANCE = Mappers.getMapper(WeatherInverseMapper.class);
    @Mappings({
            @Mapping(target="temperature", source="temperature"),
            @Mapping(target="location.cityName", source="cityName"),
            @Mapping(target="location.stateName", source="stateName"),
            @Mapping(target="location.latitude", source="latitude"),
            @Mapping(target="location.longitude", source="longitude"),
            @Mapping(target="id", source="id")
    })
    Weather weatherDtoToToWeather(WeatherDto weather);
}
