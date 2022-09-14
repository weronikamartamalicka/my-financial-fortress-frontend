package com.restapi.financialfortressfrontend.main.page;

import com.restapi.financialfortressfrontend.client.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@SpringComponent
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
        removePortfolio.addClickListener(e -> {
            modelPortfolioClient.deletePortfolio();
            removePortfolio.getUI().ifPresent(ui -> ui.navigate("home2"));
        });

        Button updateMarketValues = new Button("update market values", new Icon(VaadinIcon.BAR_CHART));
        updateMarketValues.addClickListener(e -> {
            bondsQuotedClient.updateValues();
            emergingMarketClient.updateValues();
            developedMarketClient.updateValues();
            goldClient.updateValues();
        });
        Button updateInflationIndexedBonds = new Button("update inflation indexed bonds", new Icon(VaadinIcon.BAR_CHART_H));
        updateInflationIndexedBonds.addClickListener(e -> {
            bondsIndexedClient.updateValues();
        });

        add(removePortfolio);
        add(updateMarketValues);
        add(updateInflationIndexedBonds);
    }
}
