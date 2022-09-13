package com.restapi.financialfortressfrontend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BondsInvestmentResponse {

    @JsonProperty("date")
    public LocalDateTime date;
    @JsonProperty("type")
    public String type;
    @JsonProperty("quantity")
    public BigDecimal quantity;

    @JsonProperty("FACE_VALUE")
    public BigDecimal faceValue;

    @JsonProperty("redemptionDate")
    public LocalDate redemptionDate;

    @JsonProperty("entireValuation")
    public BigDecimal entireValuation;

}
