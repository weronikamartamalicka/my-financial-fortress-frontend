package com.restapi.financialfortressfrontend.main.page;

import com.restapi.financialfortressfrontend.boards.EmergingMarketBoard;
import com.restapi.financialfortressfrontend.client.EmergingMarketClient;
import com.restapi.financialfortressfrontend.domain.dto.MarketInvestmentResponse;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EmergingMarketMainPage extends VerticalLayout {

    private final EmergingMarketBoard emergingMarketBoard;
    private final EmergingMarketClient emergingMarketClient;

    @Autowired
    public EmergingMarketMainPage(EmergingMarketBoard emergingMarketBoard, EmergingMarketClient emergingMarketClient) {
        this.emergingMarketBoard = emergingMarketBoard;
        this.emergingMarketClient = emergingMarketClient;

        add(emergingMarketBoard.getBoard());
        add(new EmergingMarketInvestInfo());
    }

    private class EmergingMarketInvestInfo extends HorizontalLayout {

        public EmergingMarketInvestInfo() {
            MarketInvestmentResponse investment = emergingMarketClient.getActualInvestInfo();
            Icon icon = new Icon(VaadinIcon.BUILDING_O);
            icon.setSize("20");
            Span type = new Span("Emerging market investment type : " + investment.type);
            type.getElement().getThemeList().add("badge success pill");
            Span quantity = new Span("Stocks quantity: " + investment.quantity);
            quantity.getElement().getThemeList().add("badge contrast pill");

            add(icon);
            add(type);
            add(quantity);
        }
    }
}
