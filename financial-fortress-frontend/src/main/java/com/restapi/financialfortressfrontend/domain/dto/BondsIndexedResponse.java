package com.restapi.financialfortressfrontend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BondsIndexedResponse {

    @JsonProperty("type")
    public String type;

    @JsonProperty("date")
    public LocalDateTime date;

    @JsonProperty("valuation")
    public BigDecimal valuation;

    @JsonProperty("interestsValuation")
    public BigDecimal interestsValuation;

    public LocalDateTime getDate() {
        return date;
    }
}
