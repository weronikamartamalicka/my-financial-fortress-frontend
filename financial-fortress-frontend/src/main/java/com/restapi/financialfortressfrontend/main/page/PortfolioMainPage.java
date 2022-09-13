package com.restapi.financialfortressfrontend.main.page;

import com.restapi.financialfortressfrontend.boards.MainPortfolioBoard;
import com.restapi.financialfortressfrontend.charts.MainPortfolioChart;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PortfolioMainPage extends VerticalLayout {

    private MainPortfolioChart homeChart;
    private MainPortfolioBoard homeBoard;

    @Autowired
    public PortfolioMainPage(MainPortfolioChart homeChart, MainPortfolioBoard homeBoard) {
        this.homeChart = homeChart;
        this.homeBoard = homeBoard;

        add(new Text("Invested Capital : 50 000 zł"));
        add(homeChart.getChart());
        add(homeBoard.getBoard());
    }
}

