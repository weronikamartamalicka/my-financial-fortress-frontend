package com.restapi.financialfortressfrontend.main.page;

import com.restapi.financialfortressfrontend.boards.DevelopedMarketBoard;
import com.restapi.financialfortressfrontend.client.DevelopedMarketClient;
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
import org.springframework.stereotype.Component;

@SpringComponent
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DevelopedMarketMainPage extends VerticalLayout {

    private DevelopedMarketBoard developedMarketBoard;
    private DevelopedMarketClient developedMarketClient;

    @Autowired
    public DevelopedMarketMainPage(DevelopedMarketBoard developedMarketBoard, DevelopedMarketClient developedMarketClient) {
        this.developedMarketBoard = developedMarketBoard;
        this.developedMarketClient = developedMarketClient;

        add(developedMarketBoard.getBoard());
        add(new DevelopedMarketInvestInfo());
    }

    private class DevelopedMarketInvestInfo extends HorizontalLayout {

        public DevelopedMarketInvestInfo() {
            MarketInvestmentResponse investment = developedMarketClient.getActualInvestInfo();
            Icon icon = new Icon(VaadinIcon.BUILDING);
            icon.setSize("20");
            Span type = new Span("Developed market investment type : " + investment.type);
            type.getElement().getThemeList().add("badge success pill");
            Span quantity = new Span("Stocks quantity: " + investment.quantity);
            quantity.getElement().getThemeList().add("badge contrast pill");

            add(icon);
            add(type);
            add(quantity);
        }
    }
}
