package com.restapi.financialfortressfrontend.config;

import com.restapi.financialfortressfrontend.domain.dto.GoldResponse;

import java.util.Comparator;

public class GoldResponseComparator implements Comparator<GoldResponse> {

    @Override
    public int compare(final GoldResponse response1, final GoldResponse response2) {
        return response1.date.compareTo(response2.date);
    }
}
