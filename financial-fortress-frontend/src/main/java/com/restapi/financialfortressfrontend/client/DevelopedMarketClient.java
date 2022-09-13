package com.restapi.financialfortressfrontend.client;

import com.restapi.financialfortressfrontend.domain.dto.MarketInvestmentResponse;
import com.restapi.financialfortressfrontend.domain.dto.MarketResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class DevelopedMarketClient {

    private final static String API_ROOT = "http://localhost:8080/v1/developed";

    public Set<MarketResponse> getAllDevelopedValues() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "/value").build();
        try {
            MarketResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(MarketResponse[].class).block();

            List<MarketResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            HashSet<MarketResponse> marketSet = new HashSet<>(responseList);

            return marketSet;
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

    }

    public MarketInvestmentResponse getActualInvestInfo() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT+ "/invest").build();
        try {
            MarketInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(MarketInvestmentResponse[].class).block();

            return Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .get(response.length - 1);

        } catch (RestClientException e) {
            e.printStackTrace();
            return new MarketInvestmentResponse();
        }
    }

    public Set<MarketInvestmentResponse> getDevelopedInvestmentValues() {
        WebClient webClient = WebClient.builder().baseUrl(API_ROOT+ "/invest").build();
        try {
            MarketInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(MarketInvestmentResponse[].class).block();

            List<MarketInvestmentResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            HashSet<MarketInvestmentResponse> marketSet = new HashSet<>(responseList);
            return marketSet;

        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

    }
}
