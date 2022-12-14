package com.restapi.financialfortressfrontend.client;

import com.restapi.financialfortressfrontend.domain.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class BondsQuotedClient {

    private final static String API_ROOT = "http://localhost:8080/v1/bonds";

    public void updateValues() {
        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "/quoted/value").build();
        try {
            webClient.post()
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    public Set<BondsResponse> getAllBondsValues() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "/quoted/value").build();
        try {
            BondsResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(BondsResponse[].class).block();

            List<BondsResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            Collections.sort(responseList, Comparator.comparing(BondsResponse::getDate));
            HashSet<BondsResponse> bondsSet = new HashSet<>(responseList);

            return bondsSet;
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }

    public BondsInvestmentResponse getActualInvestInfo() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT+ "/quoted").build();
        try {
            BondsInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(BondsInvestmentResponse[].class).block();

            List<BondsInvestmentResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());

            if(!responseList.isEmpty()) {
                return responseList.get(responseList.size() - 1);
            }

        } catch (RestClientException e) {
            e.printStackTrace();
            return new BondsInvestmentResponse();
        }
        return new BondsInvestmentResponse();
    }

    public List<BondsInvestmentResponse> getBondInvestmentValues() {
        WebClient webClient = WebClient.builder().baseUrl(API_ROOT+ "/quoted").build();
        try {
            BondsInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(BondsInvestmentResponse[].class).block();

            List<BondsInvestmentResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            Collections.sort(responseList, Comparator.comparing(BondsInvestmentResponse::getDate));

            return responseList;
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
