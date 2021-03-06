package com.pharmeasy.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class LocationResponsedDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "pincode")
    private Long pincode;

    @JsonProperty(value = "city")
    private List<CityResponse> cityResponse;
}
