package com.restapi.financialfortressfrontend.client;

import com.restapi.financialfortressfrontend.domain.dto.EmergingInvestmentResponse;
import com.restapi.financialfortressfrontend.domain.dto.EmergingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class EmergingMarketClient {

    private final static String API_ROOT = "http://localhost:8080/v1/emerging";

    public Set<EmergingResponse> getAllEmergingValues() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "/value").build();
        try {
            EmergingResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(EmergingResponse[].class).block();

            List<EmergingResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            HashSet<EmergingResponse> marketSet = new HashSet<>(responseList);

            return marketSet;
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

    }

    public EmergingInvestmentResponse getActualInvestInfo() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT+ "/invest").build();
        try {
            EmergingInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(EmergingInvestmentResponse[].class).block();

            return Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .get(response.length - 1);

        } catch (RestClientException e) {
            e.printStackTrace();
            return new EmergingInvestmentResponse();
        }
    }

    public Set<EmergingInvestmentResponse> getEmergingInvestmentValues() {
        WebClient webClient = WebClient.builder().baseUrl(API_ROOT+ "/invest").build();
        try {
            EmergingInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(EmergingInvestmentResponse[].class).block();

            List<EmergingInvestmentResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            HashSet<EmergingInvestmentResponse> marketSet = new HashSet<>(responseList);
            return marketSet;

        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

    }
}
