package com.restapi.financialfortressfrontend.config;

import com.restapi.financialfortressfrontend.domain.dto.BondsIndexedResponse;

import java.util.Comparator;

public class BondsIndexedResponseComparator implements Comparator<BondsIndexedResponse> {

    @Override
    public int compare(final BondsIndexedResponse response1, final BondsIndexedResponse response2) {
        return response1.date.compareTo(response2.date);
    }
}
