package com.restapi.financialfortressfrontend.config;

import com.restapi.financialfortressfrontend.domain.dto.MarketResponse;

import java.util.Comparator;

public class MarketResponseComparator implements Comparator<MarketResponse> {

    @Override
    public int compare(final MarketResponse response1, final MarketResponse response2) {
        return response1.date.compareTo(response2.date);
    }
}
