package com.pharmeasy.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityResponse {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "slug")
    private String slug;
}
