package com.restapi.financialfortressfrontend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketInvestmentResponse {
    @JsonProperty("date")
    public LocalDateTime date;

    @JsonProperty("type")
    public String type;

    @JsonProperty("quantity")
    public BigDecimal quantity;

    @JsonProperty("entireValuation")
    public BigDecimal entireValuation;
}
