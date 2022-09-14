package com.restapi.financialfortressfrontend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BondsIndexedInvestmentResponse {

    @JsonProperty("date")
    public LocalDateTime date;

    @JsonProperty("type")
    public String type;

    @JsonProperty("quantity")
    public BigDecimal quantity;

    @JsonProperty("firstYearInterestRate")
    public BigDecimal firstYearInterestRate;

    @JsonProperty("interestRate")
    public BigDecimal interestRate;

    @JsonProperty("redemptionDate")
    public LocalDate redemptionDate;

    @JsonProperty("price")
    public BigDecimal price;

    @JsonProperty("entireValuation")
    public BigDecimal entireValuation;

    public LocalDateTime getDate() {
        return date;
    }
}
