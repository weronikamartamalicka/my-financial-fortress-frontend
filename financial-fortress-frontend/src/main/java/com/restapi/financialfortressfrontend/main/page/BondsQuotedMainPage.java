package com.restapi.financialfortressfrontend.main.page;

import com.restapi.financialfortressfrontend.boards.BondsQuotedBoard;
import com.restapi.financialfortressfrontend.client.BondsQuotedClient;
import com.restapi.financialfortressfrontend.domain.dto.BondsInvestmentResponse;
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
public class BondsQuotedMainPage extends VerticalLayout {

    private BondsQuotedBoard board;
    private BondsQuotedClient bondsQuotedClient;

    @Autowired
    public BondsQuotedMainPage(BondsQuotedBoard board, BondsQuotedClient bondsQuotedClient) {
        this.board = board;
        this.bondsQuotedClient = bondsQuotedClient;

        add(board.getBoard());
        add(new BondsInvestInfo());
    }

    private class BondsInvestInfo extends HorizontalLayout {

        public BondsInvestInfo() {
            BondsInvestmentResponse investment = bondsQuotedClient.getActualInvestInfo();
            Icon icon = new Icon(VaadinIcon.DIPLOMA);
            icon.setSize("20");
            Span type = new Span("Bonds investment type : " + investment.type);
            type.getElement().getThemeList().add("badge success pill");
            Span quantity = new Span("Bonds quantity: " + investment.quantity);
            quantity.getElement().getThemeList().add("badge contrast pill");
            Span redemptionDate = new Span("Redemption date: " + investment.redemptionDate);
            redemptionDate.getElement().getThemeList().add("badge contrast pill");
            Span faceValue = new Span("Face value: " + investment.faceValue + "zł");
            faceValue.getElement().getThemeList().add("badge contrast pill");

            add(icon);
            add(type);
            add(quantity);
            add(redemptionDate);
            add(faceValue);
        }
    }
}
