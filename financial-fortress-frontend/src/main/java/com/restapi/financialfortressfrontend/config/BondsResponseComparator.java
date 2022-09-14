package com.restapi.financialfortressfrontend.config;

import com.restapi.financialfortressfrontend.domain.dto.BondsResponse;

import java.util.Comparator;

public class BondsResponseComparator implements Comparator<BondsResponse> {

    @Override
    public int compare(final BondsResponse response1, final BondsResponse response2) {
        return response1.date.compareTo(response2.date);
    }
}
