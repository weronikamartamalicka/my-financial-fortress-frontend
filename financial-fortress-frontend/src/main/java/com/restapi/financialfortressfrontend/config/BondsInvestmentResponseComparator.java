package com.restapi.financialfortressfrontend.config;

import com.restapi.financialfortressfrontend.domain.dto.BondsInvestmentResponse;

import java.util.Comparator;

public class BondsInvestmentResponseComparator implements Comparator<BondsInvestmentResponse> {

    @Override
    public int compare(final BondsInvestmentResponse response1, final BondsInvestmentResponse response2) {
        return response1.date.compareTo(response2.date);
    }
}
