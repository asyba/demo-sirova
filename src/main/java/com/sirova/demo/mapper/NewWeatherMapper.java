package com.sirova.demo.mapper;

import com.sirova.demo.dto.WeatherDto;
import com.sirova.demo.model.NewWeatherRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface NewWeatherMapper {

    NewWeatherMapper INSTANCE = Mappers.getMapper(NewWeatherMapper.class);
    @Mappings({
            @Mapping(target="temperature", source="temperature"),
            @Mapping(target="cityName", source="location.cityName"),
            @Mapping(target="stateName", source="location.stateName"),
            @Mapping(target="latitude", source="location.latitude"),
            @Mapping(target="longitude", source="location.longitude"),
            @Mapping(target="id", ignore=true),
            @Mapping(target="dateRecorded", ignore=true),
            @Mapping(target="dateUpdated", ignore=true)
    })

    WeatherDto weatherRequestToWeatherDto(NewWeatherRequest weather);

}
