package com.restapi.financialfortressfrontend.main.page;

import com.restapi.financialfortressfrontend.client.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageInvestMainPage extends VerticalLayout {

    private ModelPortfolioClient modelPortfolioClient;
    private BondsIndexedClient bondsIndexedClient;
    private BondsQuotedClient bondsQuotedClient;
    private EmergingMarketClient emergingMarketClient;
    private DevelopedMarketClient developedMarketClient;
    private GoldClient goldClient;

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
        removePortfolio.getElement().addEventListener("click", e -> {
            modelPortfolioClient.deletePortfolio();
            removePortfolio.getUI().ifPresent(ui -> ui.navigate("home2"));
        });
        Button updateMarketValues = new Button("update market values", new Icon(VaadinIcon.BAR_CHART));
        updateMarketValues.getElement().addEventListener("click", e -> {
            bondsQuotedClient.updateValues();
            emergingMarketClient.updateValues();
            developedMarketClient.updateValues();
            goldClient.updateValues();

        });
        Button updateInflationIndexedBonds = new Button("update inflation indexed bonds", new Icon(VaadinIcon.BAR_CHART_H));
        updateInflationIndexedBonds.getElement().addEventListener("click", e -> {
            bondsIndexedClient.updateValues();
        });

        add(removePortfolio);
        add(updateMarketValues);
        add(updateInflationIndexedBonds);
    }
}
