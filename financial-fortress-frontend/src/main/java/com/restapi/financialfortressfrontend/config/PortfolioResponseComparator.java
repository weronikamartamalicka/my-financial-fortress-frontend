package com.restapi.financialfortressfrontend.config;

import com.restapi.financialfortressfrontend.domain.dto.PortfolioValuesResponse;

import java.util.Comparator;

public class PortfolioResponseComparator implements Comparator<PortfolioValuesResponse> {

    @Override
    public int compare(final PortfolioValuesResponse response1, final PortfolioValuesResponse response2) {
        return response1.date.compareTo(response2.date);
    }
}
