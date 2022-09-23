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

    public void updateValues() {
        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "/value").build();
        try {
            webClient.post()
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

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
            Collections.sort(responseList, Comparator.comparing(MarketResponse::getDate));
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

            List<MarketInvestmentResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());

            if(!responseList.isEmpty()) {
                return responseList.get(responseList.size() - 1);
            }

        } catch (RestClientException e) {
            e.printStackTrace();
            return new MarketInvestmentResponse();
        }
        return new MarketInvestmentResponse();
    }

    public List<MarketInvestmentResponse> getDevelopedInvestmentValues() {
        WebClient webClient = WebClient.builder().baseUrl(API_ROOT+ "/invest").build();
        try {
            MarketInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(MarketInvestmentResponse[].class).block();

            List<MarketInvestmentResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            Collections.sort(responseList, Comparator.comparing(MarketInvestmentResponse::getDate));

            return responseList;
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
