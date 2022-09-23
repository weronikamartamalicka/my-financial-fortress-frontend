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
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import java.util.Optional;

@SpringComponent
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BondsIndexedMainPage extends VerticalLayout {

    private final BondsIndexedClient bondsIndexedClient;
    private final BondsIndexedBoard bondsIndexedBoard;
    private final BondsIndexedChart bondsIndexedChart;

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

                add(icon);
                add(type);
                add(quantity);
                add(redemptionDate);
            }
        }
    }
}
