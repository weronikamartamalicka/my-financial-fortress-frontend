package com.restapi.financialfortressfrontend.main.page;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("home")
public class HomePage extends AppLayout {

    private PortfolioMainPage portfolioMainPage;
    private GoldMainPage goldMainPage;
    @Autowired
    public HomePage(PortfolioMainPage portfolioMainPage, GoldMainPage goldMainPage) {
        this.portfolioMainPage = portfolioMainPage;
        this.goldMainPage = goldMainPage;

        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("MyFinancialFortressApp");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        Tabs tabs = getTabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        setContent(portfolioMainPage);

        addToDrawer(tabs);
        addToNavbar(toggle, title);

        setPrimarySection(Section.DRAWER);
    }

    private Tabs getTabs() {

        Tab home = new Tab(VaadinIcon.WALLET.create(), new Span("Home"));
        home.getElement().addEventListener("click", event -> {
            remove(goldMainPage);
            setContent(portfolioMainPage);
        });
        Tab gold = new Tab(VaadinIcon.PIGGY_BANK_COIN.create(), new Span("Gold"));
        gold.getElement().addEventListener("click", e -> {
            remove(portfolioMainPage);
            setContent(goldMainPage);
        });
        Tab bondsQuoted = new Tab(VaadinIcon.DIPLOMA.create(), new Span("BondsQuoted"));
        Tab bondsIndexed = new Tab(VaadinIcon.INSTITUTION.create(), new Span("BondsIndexed"));
        Tab emergingMarket = new Tab(VaadinIcon.BUILDING_O.create(), new Span("EmergingMarketETF"));
        Tab developedMarket = new Tab(VaadinIcon.BUILDING.create(), new Span("DevelopedMarketETF"));

        return new Tabs(home, gold, bondsQuoted, bondsIndexed, emergingMarket, developedMarket);
    }
}
