package com.samplemgt.downjacket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomJacketReq {

    @JsonProperty("keyword")
    public String keyword;

    @JsonProperty("startDate")
    public String startDate;

    @JsonProperty("endDate")
    public String endDate;

    @JsonProperty("status")
    public String status;

    @JsonProperty("dateCriteria")
    public String dateCriteria;

}
