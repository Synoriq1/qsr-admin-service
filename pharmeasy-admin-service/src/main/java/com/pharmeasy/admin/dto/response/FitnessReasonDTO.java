package com.pharmeasy.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class FitnessReasonDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "status")
    private String status;

//    @JsonProperty(value = "new_entry")
//    private Long newEntry;
}
