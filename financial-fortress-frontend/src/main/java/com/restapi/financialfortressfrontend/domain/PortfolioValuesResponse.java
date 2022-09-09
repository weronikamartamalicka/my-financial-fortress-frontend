package com.restapi.financialfortressfrontend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PortfolioValuesResponse {

    @JsonProperty("goldValue")
    public BigDecimal goldValue;

    @JsonProperty("bondsQuotedValue")
    public BigDecimal bondsQuotedValue;

    @JsonProperty("bondsIndexedValue")
    public BigDecimal bondsIndexedValue;

    @JsonProperty("developedMarketValue")
    public BigDecimal developedMarketValue;

    @JsonProperty("emergingMarketValue")
    public BigDecimal emergingMarketValue;

}
