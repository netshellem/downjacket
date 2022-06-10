package com.samplemgt.downjacket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JacketAttributeResp {
    @JsonProperty("text")
    public String text;

    @JsonProperty("value")
    public String value;
}
