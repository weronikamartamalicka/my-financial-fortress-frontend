package com.restapi.financialfortressfrontend;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

@Route("home")
public class HomePage extends AppLayout {

    public HomePage() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("MyFinancialFortressApp");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        Tab home = new Tab(
                VaadinIcon.WALLET.create(),
                new Span("Home")
        );
        Tab gold = new Tab(
                VaadinIcon.PIGGY_BANK_COIN.create(),
                new Span("Gold")
        );
        Tab bondsQuoted = new Tab(
                VaadinIcon.DIPLOMA.create(),
                new Span("BondsQuoted")
        );

        Tab bondsIndexed = new Tab(
                VaadinIcon.INSTITUTION.create(),
                new Span("BondsIndexed")
        );

        Tab emergingMarket = new Tab(
                VaadinIcon.BUILDING_O.create(),
                new Span("EmergingMarketETF")
        );

        Tab developedMarket = new Tab(
                VaadinIcon.BUILDING.create(),
                new Span("DevelopedMarketETF")
        );

        Tabs tabs = new Tabs(home, gold, bondsQuoted, bondsIndexed, emergingMarket, developedMarket);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        Chart chart = new Chart(ChartType.PIE);
        Configuration conf = chart.getConfiguration();
        PlotOptionsPie options = new PlotOptionsPie();
        options.setInnerSize("0");
        options.setSize("75%");
        options.setCenter("50%", "50%");
        conf.setPlotOptions(options);

        DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("Gold", 4900));
        series.add(new DataSeriesItem("EmergingMarketETF", 12100));
        conf.addSeries(series);

        Options3d options3d = new Options3d();
        options3d.setEnabled(true);
        options3d.setAlpha(10);
        options3d.setBeta(30);
        options3d.setDepth(135);
        options3d.setViewDistance(100);
        conf.getChart().setOptions3d(options3d);

//        Frame frame = new Frame();
//        Back back=new Back();
//        back.setColor(SolidColor.BEIGE);
//        back.setSize(1);
//        frame.setBack(back);
//        options3d.setFrame(frame);

        options.setDepth(50);

        setContent(chart);

        addToDrawer(tabs);
        addToNavbar(toggle, title);

        setPrimarySection(Section.DRAWER);
    }
}
