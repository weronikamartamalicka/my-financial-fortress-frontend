package com.restapi.financialfortressfrontend.main.page;

import com.restapi.financialfortressfrontend.boards.BondsIndexedBoard;
import com.restapi.financialfortressfrontend.charts.BondsIndexedChart;
import com.restapi.financialfortressfrontend.client.BondsIndexedClient;
import com.restapi.financialfortressfrontend.domain.dto.BondsIndexedInvestmentResponse;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class BondsIndexedMainPage extends VerticalLayout {

    private BondsIndexedClient bondsIndexedClient;
    private BondsIndexedBoard bondsIndexedBoard;
    private BondsIndexedChart bondsIndexedChart;

    @Autowired
    public BondsIndexedMainPage(BondsIndexedClient bondsIndexedClient, BondsIndexedBoard bondsIndexedBoard,
                                BondsIndexedChart bondsIndexedChart) {
        this.bondsIndexedClient = bondsIndexedClient;
        this.bondsIndexedBoard = bondsIndexedBoard;
        this.bondsIndexedChart = bondsIndexedChart;

        add(bondsIndexedBoard.getBoard());
        add(bondsIndexedChart.getInflationChart());
        add(new BondsInvestInfo());
    }

    private class BondsInvestInfo extends HorizontalLayout {

        public BondsInvestInfo() {
            BondsIndexedInvestmentResponse investment = bondsIndexedClient.getActualInvestInfo();
            if(Optional.ofNullable(bondsIndexedClient.getActualInvestInfo()).isPresent()) {
                Icon icon = new Icon(VaadinIcon.INSTITUTION);
                icon.setSize("20");
                Span type = new Span("Bonds investment type : " + investment.type);
                type.getElement().getThemeList().add("badge success pill");
                Span quantity = new Span("Bonds quantity: " + investment.quantity);
                quantity.getElement().getThemeList().add("badge contrast pill");
                Span redemptionDate = new Span("Redemption date: " + investment.redemptionDate);
                redemptionDate.getElement().getThemeList().add("badge contrast pill");
                //Span interestRate = new Span("First year interest rate: " + investment.firstYearInterestRate
                        //.multiply(BigDecimal.valueOf(100)) + "%");
                //interestRate.getElement().getThemeList().add("badge contrast pill");
                //Span interestRate2 = new Span("Interest rate: " + investment.interestRate
                        //.multiply(BigDecimal.valueOf(100)) + "%");
                //interestRate2.getElement().getThemeList().add("badge contrast pill");

                add(icon);
                add(type);
                add(quantity);
                add(redemptionDate);
                //add(interestRate);
                //add(interestRate2);
            }
        }
    }
}
