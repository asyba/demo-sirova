package com.sirova.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "weather")
@Getter
@Setter
public class WeatherDto extends GenericObject {

    private Float temperature;
    private String cityName;
    private String stateName;
    private Float latitude;
    private Float longitude;
    private Date dateRecorded;
    private Date dateUpdated;

}
