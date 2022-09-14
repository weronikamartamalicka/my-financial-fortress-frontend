package com.restapi.financialfortressfrontend.client;

import com.restapi.financialfortressfrontend.domain.dto.GoldInvestmentResponse;
import com.restapi.financialfortressfrontend.domain.dto.GoldResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class GoldClient {

    private final static String API_ROOT = "http://localhost:8080/v1/gold/";

    public void updateValues() {
        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "/value").build();
        try {
            webClient.post()
                    .retrieve()
                    .bodyToMono(Void.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    public Set<GoldResponse> getAllGoldValues() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "value").build();
        try {
            GoldResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(GoldResponse[].class).block();

            List<GoldResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            HashSet<GoldResponse> portfolioSet = new HashSet<>(responseList);

            return portfolioSet;
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

    }

    public GoldInvestmentResponse getActualInvestInfo() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "invest").build();
        try {
            GoldInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(GoldInvestmentResponse[].class).block();

            return Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .get(response.length - 1);

        } catch (RestClientException e) {
            e.printStackTrace();
            return new GoldInvestmentResponse();
        }

    }
}
