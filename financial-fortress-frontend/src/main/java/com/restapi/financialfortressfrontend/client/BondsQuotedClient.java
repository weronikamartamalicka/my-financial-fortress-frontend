package com.restapi.financialfortressfrontend.client;

import com.restapi.financialfortressfrontend.domain.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class BondsQuotedClient {

    private final static String API_ROOT = "http://localhost:8080/v1/bonds";

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

            return Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .get(response.length - 1);

        } catch (RestClientException e) {
            e.printStackTrace();
            return new BondsInvestmentResponse();
        }
    }

    public Set<BondsInvestmentResponse> getBondInvestmentValues() {
        WebClient webClient = WebClient.builder().baseUrl(API_ROOT+ "/quoted").build();
        try {
            BondsInvestmentResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(BondsInvestmentResponse[].class).block();

            List<BondsInvestmentResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            HashSet<BondsInvestmentResponse> bondsSet = new HashSet<>(responseList);
            return bondsSet;

        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

    }

    public Set<YearBondsResponse> getYearBondsValues() {

        WebClient webClient = WebClient.builder().baseUrl(API_ROOT + "/values").build();
        try {
            YearBondsResponse[] response = webClient
                    .get()
                    .retrieve()
                    .bodyToMono(YearBondsResponse[].class).block();

            List<YearBondsResponse> responseList = Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            HashSet<YearBondsResponse> yearBondsSet = new HashSet<>(responseList);

            return yearBondsSet;
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

    }
}
