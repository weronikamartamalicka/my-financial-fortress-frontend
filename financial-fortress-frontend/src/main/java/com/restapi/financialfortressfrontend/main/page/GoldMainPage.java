package com.restapi.financialfortressfrontend.main.page;

import com.restapi.financialfortressfrontend.boards.GoldBoard;
import com.restapi.financialfortressfrontend.client.GoldClient;
import com.restapi.financialfortressfrontend.domain.dto.GoldInvestmentResponse;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoldMainPage extends VerticalLayout {
    private GoldBoard goldBoard;
    private GoldClient goldClient;

    @Autowired
    public GoldMainPage(GoldBoard goldBoard, GoldClient goldClient) {
        this.goldBoard = goldBoard;
        this.goldClient = goldClient;

        add(goldBoard.getBoard());
        add(new GoldInvestInfo());
    }

    private class GoldInvestInfo extends HorizontalLayout {

        public GoldInvestInfo() {
            GoldInvestmentResponse investment = goldClient.getActualInvestInfo();
            Icon icon = new Icon(VaadinIcon.PIGGY_BANK_COIN);
            icon.setSize("20");
            Span type = new Span("Gold investment type : coin " + investment.type);
            type.getElement().getThemeList().add("badge success pill");
            Span quantity = new Span("Coins quantity: " + investment.quantity);
            quantity.getElement().getThemeList().add("badge contrast pill");

            add(icon);
            add(type);
            add(quantity);
        }
    }
}
