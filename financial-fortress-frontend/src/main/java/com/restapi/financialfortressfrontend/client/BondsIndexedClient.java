package com.restapi.financialfortressfrontend.client;

import com.restapi.financialfortressfrontend.domain.dto.BondsIndexedInvestmentResponse;
import com.restapi.financialfortressfrontend.domain.dto.BondsIndexedResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BondsIndexedClient {

    private final static String API_ROOT = "http://localhost:8080/v1/inflation";

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

    public List<BondsIndexedResponse> getAllInflationValues() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "/value").build();
        try {
            BondsIndexedResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(BondsIndexedResponse[].class).block();

            List<BondsIndexedResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            Collections.sort(responseList, Comparator.comparing(BondsIndexedResponse::getDate));

            return responseList;
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    public BondsIndexedInvestmentResponse getActualInvestInfo() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT+ "/invest").build();
        try {
            BondsIndexedInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(BondsIndexedInvestmentResponse[].class).block();

            List<BondsIndexedInvestmentResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());

            if(!responseList.isEmpty()) {
                return responseList.get(responseList.size() - 1);
            }

        } catch (RestClientException e) {
            e.printStackTrace();
            return new BondsIndexedInvestmentResponse();
        }
        return new BondsIndexedInvestmentResponse();
    }

    public List<BondsIndexedInvestmentResponse> getBondInvestmentValues() {
        WebClient webClient = WebClient.builder().baseUrl(API_ROOT+ "/invest").build();
        try {
            BondsIndexedInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(BondsIndexedInvestmentResponse[].class).block();

            List<BondsIndexedInvestmentResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            Collections.sort(responseList, Comparator.comparing(BondsIndexedInvestmentResponse::getDate));

            return responseList;
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }
}
