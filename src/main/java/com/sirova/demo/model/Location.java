package com.sirova.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Location {

    private String cityName;
    private String stateName;
    private Float latitude;
    private Float longitude;

}
