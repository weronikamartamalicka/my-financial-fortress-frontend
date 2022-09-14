package com.restapi.financialfortressfrontend.config;

import com.restapi.financialfortressfrontend.domain.dto.BondsIndexedInvestmentResponse;

import java.util.Comparator;

public class BondsIndexedInvestmentResponseComparator implements Comparator<BondsIndexedInvestmentResponse> {

    @Override
    public int compare(final BondsIndexedInvestmentResponse response1, final BondsIndexedInvestmentResponse response2) {
        return response1.date.compareTo(response2.date);
    }
}
