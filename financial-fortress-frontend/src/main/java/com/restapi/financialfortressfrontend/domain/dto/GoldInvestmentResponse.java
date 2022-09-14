package com.restapi.financialfortressfrontend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoldInvestmentResponse {

    @JsonProperty("type")
    public String type;

    @JsonProperty("quantity")
    public BigDecimal quantity;

    @JsonProperty("entireValuation")
    public BigDecimal entireValuation;


}
