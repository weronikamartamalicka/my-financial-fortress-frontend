package com.restapi.financialfortressfrontend.client;

import com.restapi.financialfortressfrontend.domain.dto.PortfolioValuesResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class ModelPortfolioClient {
    private final static String API_ROOT = "http://localhost:8080/v1/";

    public void deletePortfolio() {
        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "portfolio").build();
        try {
            webClient.delete()
                    .retrieve()
                    .bodyToMono(Void.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    public void createPortfolio(Long capital) {
        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "portfolio/" + capital).build();
        try {
            webClient.post()
                    .retrieve()
                    .bodyToMono(Void.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    public PortfolioValuesResponse getActualPortfolioValues() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "portfolio").build();
        try {
            PortfolioValuesResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(PortfolioValuesResponse[].class).block();
            int size = response.length;
            List<PortfolioValuesResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());

            if(!responseList.isEmpty()) {
                return responseList.get(responseList.size() - 1);
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            return new PortfolioValuesResponse();
        }
        return new PortfolioValuesResponse();
    }

    public List<PortfolioValuesResponse> getAllPortfolioValues() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "portfolio").build();
        try {
            PortfolioValuesResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(PortfolioValuesResponse[].class).block();

            List<PortfolioValuesResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            Collections.sort(responseList, Comparator.comparing(PortfolioValuesResponse::getDate));

            return responseList;
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
