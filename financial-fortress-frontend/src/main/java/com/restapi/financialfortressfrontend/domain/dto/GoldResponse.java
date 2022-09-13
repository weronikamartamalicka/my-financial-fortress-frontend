package com.restapi.financialfortressfrontend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoldResponse {

    @JsonProperty("date")
    public LocalDateTime date;

    @JsonProperty("TYPE")
    public String type;

    @JsonProperty("oneCoinPrice")
    public double oneCoinPrice;
}
