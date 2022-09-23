package com.restapi.financialfortressfrontend.main.page;

import com.restapi.financialfortressfrontend.client.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ManageInvestMainPage extends VerticalLayout {

    private final ModelPortfolioClient modelPortfolioClient;
    private final BondsIndexedClient bondsIndexedClient;
    private final BondsQuotedClient bondsQuotedClient;
    private final EmergingMarketClient emergingMarketClient;
    private final DevelopedMarketClient developedMarketClient;
    private final GoldClient goldClient;


    @Autowired
    public ManageInvestMainPage(ModelPortfolioClient modelPortfolioClient, BondsIndexedClient bondsIndexedClient,
                                BondsQuotedClient bondsQuotedClient, EmergingMarketClient emergingMarketClient,
                                DevelopedMarketClient developedMarketClient, GoldClient goldClient) {
        this.modelPortfolioClient = modelPortfolioClient;
        this.bondsIndexedClient = bondsIndexedClient;
        this.bondsQuotedClient = bondsQuotedClient;
        this.emergingMarketClient = emergingMarketClient;
        this.developedMarketClient = developedMarketClient;
        this.goldClient = goldClient;

        Button removePortfolio = new Button("close investment", new Icon(VaadinIcon.CLOSE_CIRCLE_O));
        removePortfolio.addClickListener(clickEvent -> {
            modelPortfolioClient.deletePortfolio();
            Notification.show("Portfolio has been removed");
            removePortfolio.getUI().ifPresent(ui -> ui.navigate("home2"));
        });

        Button updateMarketValues = new Button("update market values", new Icon(VaadinIcon.BAR_CHART));
        updateMarketValues.addClickListener(clickEvent -> {
            bondsQuotedClient.updateValues();
            emergingMarketClient.updateValues();
            developedMarketClient.updateValues();
            goldClient.updateValues();
            Notification.show("Values have been updated");
            UI.getCurrent().getPage().reload();
        });

        Button updateInflationIndexedBonds = new Button("update inflation indexed bonds", new Icon(VaadinIcon.BAR_CHART_H));
        updateInflationIndexedBonds.addClickListener(clickEvent -> {
            bondsIndexedClient.updateValues();
            Notification.show("Values have been updated");
            UI.getCurrent().getPage().reload();
        });

        add(removePortfolio);
        add(updateMarketValues);
        add(updateInflationIndexedBonds);
    }
}
