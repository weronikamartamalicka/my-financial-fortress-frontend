package com.restapi.financialfortressfrontend.client;

import com.restapi.financialfortressfrontend.domain.dto.BondsIndexedInvestmentResponse;
import com.restapi.financialfortressfrontend.domain.dto.BondsIndexedResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class BondsIndexedClient {

    private final static String API_ROOT = "http://localhost:8080/v1/inflation";

    public Set<BondsIndexedResponse> getAllInflationValues() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "/value").build();
        try {
            BondsIndexedResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(BondsIndexedResponse[].class).block();

            List<BondsIndexedResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            HashSet<BondsIndexedResponse> inflationSet = new HashSet<>(responseList);

            return inflationSet;
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

    }

    public BondsIndexedInvestmentResponse getActualInvestInfo() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT+ "/invest").build();
        try {
            BondsIndexedInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(BondsIndexedInvestmentResponse[].class).block();

            return Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .get(response.length - 1);

        } catch (RestClientException e) {
            e.printStackTrace();
            return new BondsIndexedInvestmentResponse();
        }
    }

    public Set<BondsIndexedInvestmentResponse> getBondInvestmentValues() {
        WebClient webClient = WebClient.builder().baseUrl(API_ROOT+ "/invest").build();
        try {
            BondsIndexedInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(BondsIndexedInvestmentResponse[].class).block();

            List<
                    BondsIndexedInvestmentResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            HashSet<BondsIndexedInvestmentResponse> inflationSet = new HashSet<>(responseList);
            return inflationSet;

        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

    }
}
