package com.sirova.demo.mapper;

import com.sirova.demo.dto.WeatherDto;
import com.sirova.demo.model.Weather;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface WeatherMapper {

    WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);
    @Mappings({
            @Mapping(target="temperature", source="temperature"),
            @Mapping(target="cityName", source="location.cityName"),
            @Mapping(target="stateName", source="location.stateName"),
            @Mapping(target="latitude", source="location.latitude"),
            @Mapping(target="longitude", source="location.longitude"),
            @Mapping(target="id", source="id"),
            @Mapping(target="dateRecorded", ignore=true),
            @Mapping(target="dateUpdated", ignore=true)
    })

    WeatherDto weatherRequestToWeatherDto(Weather weather);

    @Mappings({
            @Mapping(target="temperature", source="temperature"),
            @Mapping(target="cityName", source="location.cityName"),
            @Mapping(target="stateName", source="location.stateName"),
            @Mapping(target="latitude", source="location.latitude"),
            @Mapping(target="longitude", source="location.longitude"),
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    WeatherDto updateWeather(@MappingTarget WeatherDto weatherDto, Weather weather);

}
