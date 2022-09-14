package com.restapi.financialfortressfrontend.main.page;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;


@Route("home")
@SpringComponent
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HomePage extends AppLayout {

    private PortfolioMainPage portfolioMainPage;
    private GoldMainPage goldMainPage;
    private BondsQuotedMainPage bondsQuotedMainPage;
    private EmergingMarketMainPage emergingMarketMainPage;
    private DevelopedMarketMainPage developedMarketMainPage;
    private BondsIndexedMainPage bondsIndexedMainPage;
    private ManageInvestMainPage manageInvestMainPage;
    @Autowired
    public HomePage(PortfolioMainPage portfolioMainPage, GoldMainPage goldMainPage,
                    BondsQuotedMainPage bondsQuotedMainPage, EmergingMarketMainPage emergingMarketMainPage,
                    DevelopedMarketMainPage developedMarketMainPage, BondsIndexedMainPage bondsIndexedMainPage,
                    ManageInvestMainPage manageInvestMainPage) {
        this.portfolioMainPage = portfolioMainPage;
        this.goldMainPage = goldMainPage;
        this.bondsQuotedMainPage = bondsQuotedMainPage;
        this.emergingMarketMainPage = emergingMarketMainPage;
        this.developedMarketMainPage = developedMarketMainPage;
        this.bondsIndexedMainPage = bondsIndexedMainPage;
        this.manageInvestMainPage = manageInvestMainPage;

        if(getParent().isPresent()) {
            remove(getParent().get());
        }

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
            remove(getParent().get());
            setContent(portfolioMainPage);
        });
        Tab gold = new Tab(VaadinIcon.PIGGY_BANK_COIN.create(), new Span("Gold"));
        gold.getElement().addEventListener("click", e -> {
            remove(getParent().get());
            setContent(goldMainPage);
        });
        Tab bondsQuoted = new Tab(VaadinIcon.DIPLOMA.create(), new Span("BondsQuoted"));
        bondsQuoted.getElement().addEventListener("click", e -> {
            remove(getParent().get());
            setContent(bondsQuotedMainPage);
        });
        Tab bondsIndexed = new Tab(VaadinIcon.INSTITUTION.create(), new Span("BondsIndexed"));
        bondsIndexed.getElement().addEventListener("click", e -> {
            remove(getParent().get());
            setContent(bondsIndexedMainPage);
        });
        Tab emergingMarket = new Tab(VaadinIcon.BUILDING_O.create(), new Span("EmergingMarketETF"));
        emergingMarket.getElement().addEventListener("click", e -> {
            remove(getParent().get());
            setContent(emergingMarketMainPage);
        });
        Tab developedMarket = new Tab(VaadinIcon.BUILDING.create(), new Span("DevelopedMarketETF"));
        developedMarket.getElement().addEventListener("click", e -> {
            remove(getParent().get());
            setContent(developedMarketMainPage);
        });
        Tab managePortfolios = new Tab(VaadinIcon.COG_O.create(), new Span("Manage investments"));
        managePortfolios.getElement().addEventListener("click", e-> {
            remove(getParent().get());
            setContent(manageInvestMainPage);
        });

        return new Tabs(home, gold, bondsQuoted, bondsIndexed, emergingMarket, developedMarket, managePortfolios);
    }
}
