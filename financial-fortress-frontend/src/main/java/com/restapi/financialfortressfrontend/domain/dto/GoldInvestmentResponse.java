package com.restapi.financialfortressfrontend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoldInvestmentResponse {

    @JsonProperty("type")
    public String type;

    @JsonProperty("quantity")
    public double quantity;

    @JsonProperty("entireValuation")
    public double entireValuation;
}
