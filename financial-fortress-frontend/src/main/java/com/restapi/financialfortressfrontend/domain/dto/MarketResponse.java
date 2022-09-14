package com.restapi.financialfortressfrontend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketResponse {
    @JsonProperty("date")
    public LocalDateTime date;

    @JsonProperty("type")
    public String type;

    @JsonProperty("valuation")
    public BigDecimal valuation;

    @JsonProperty("commissionRate")
    public BigDecimal commissionRate;

    public LocalDateTime getDate() {
        return date;
    }
}
