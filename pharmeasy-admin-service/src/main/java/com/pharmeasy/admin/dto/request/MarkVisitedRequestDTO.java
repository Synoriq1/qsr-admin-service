package com.pharmeasy.admin.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class MarkVisitedRequestDTO {

    @JsonProperty(value = "is_visited")
    private Boolean isVisited;

    @JsonProperty(value = "claim_date")
    private Boolean claimDate;

}
