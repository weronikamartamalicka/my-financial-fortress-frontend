package com.restapi.financialfortressfrontend.domain.dto;

import lombok.Getter;

@Getter
public enum InvestmentCapital {

    SMALL(20000L), MEDIUM(50000L), LARGE(80000L);
    public Long capital;

    InvestmentCapital(Long capital) {
        this.capital = capital;
    }
}
