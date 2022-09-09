package com.restapi.financialfortressfrontend.client;

import com.restapi.financialfortressfrontend.domain.PortfolioValuesResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Service
public class ModelPortfolioClient {
    private final static String API_ROOT = "http://localhost:8080/v1/";

    public PortfolioValuesResponse getActualPortfolioValues() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "portfolio").build();
        try {
            PortfolioValuesResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(PortfolioValuesResponse[].class).block();
            int size = response.length;
            return Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .get(size - 1);
        } catch (RestClientException e) {
            e.printStackTrace();
            return new PortfolioValuesResponse();
        }
    }
}
