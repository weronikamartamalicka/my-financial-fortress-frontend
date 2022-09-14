package com.restapi.financialfortressfrontend.main.page;

import com.restapi.financialfortressfrontend.boards.MainPortfolioBoard;
import com.restapi.financialfortressfrontend.charts.MainPortfolioChart;
import com.restapi.financialfortressfrontend.client.ModelPortfolioClient;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PortfolioMainPage extends VerticalLayout {

    private MainPortfolioChart homeChart;
    private MainPortfolioBoard homeBoard;

    private ModelPortfolioClient modelPortfolioClient;

    @Autowired
    public PortfolioMainPage(MainPortfolioChart homeChart, MainPortfolioBoard homeBoard,
                             ModelPortfolioClient modelPortfolioClient) {
        this.homeChart = homeChart;
        this.homeBoard = homeBoard;
        this.modelPortfolioClient = modelPortfolioClient;

        BigDecimal entireValue = modelPortfolioClient.getActualPortfolioValues().entireValue;

        add(new Text("Entire Value : " + entireValue + " zł"));
        add(homeChart.getChart());
        add(homeBoard.getBoard());
    }
}

