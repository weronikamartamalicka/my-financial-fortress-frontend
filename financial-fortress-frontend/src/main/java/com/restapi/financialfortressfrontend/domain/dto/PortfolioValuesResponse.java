package com.restapi.financialfortressfrontend.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PortfolioValuesResponse {

    @JsonProperty("date")
    public Date date;

    @JsonProperty("entireValue")
    public double entireValue;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortfolioValuesResponse that = (PortfolioValuesResponse) o;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
