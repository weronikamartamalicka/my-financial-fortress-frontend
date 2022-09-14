package com.restapi.financialfortressfrontend.config;

import com.restapi.financialfortressfrontend.domain.dto.MarketInvestmentResponse;

import java.util.Comparator;

public class MarketInvestmentResponseComparator implements Comparator<MarketInvestmentResponse> {

    @Override
    public int compare(final MarketInvestmentResponse response1, final MarketInvestmentResponse response2) {
        return response1.date.compareTo(response2.date);
    }
}
