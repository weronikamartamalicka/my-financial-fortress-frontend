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
                    .bodyToMono(Void.class)
                    .block();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    public List<GoldResponse> getAllGoldInvestmentValues() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "invest").build();
        try {
            GoldResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(GoldResponse[].class).block();

            List<GoldResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            Collections.sort(responseList, Comparator.comparing(GoldResponse::getDate));

            return responseList;
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public GoldInvestmentResponse getActualInvestInfo() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "invest").build();
        try {
            GoldInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(GoldInvestmentResponse[].class).block();

            List<GoldInvestmentResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());

            if(!responseList.isEmpty()) {
                return responseList.get(responseList.size() - 1);
            }

        } catch (RestClientException e) {
            e.printStackTrace();
            return new GoldInvestmentResponse();
        }
        return new GoldInvestmentResponse();
    }
}
