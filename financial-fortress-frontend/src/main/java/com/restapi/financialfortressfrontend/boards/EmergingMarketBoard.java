package com.restapi.financialfortressfrontend.boards;

import com.restapi.financialfortressfrontend.VisualStatistics;
import com.restapi.financialfortressfrontend.charts.EmergingMarketChart;
import com.restapi.financialfortressfrontend.client.EmergingMarketClient;
import com.restapi.financialfortressfrontend.domain.dto.MarketInvestmentResponse;
import com.restapi.financialfortressfrontend.main.page.HomePage;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.board.Row;
import com.vaadin.flow.component.charts.Chart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmergingMarketBoard extends Board {

    private EmergingMarketClient emergingMarketClient;
    private EmergingMarketChart emergingMarketChart;

    @Autowired
    public EmergingMarketBoard(EmergingMarketClient emergingMarketClient, EmergingMarketChart emergingMarketChart) {
        this.emergingMarketClient = emergingMarketClient;
        this.emergingMarketChart = emergingMarketChart;
    }
    @EventListener(HomePage.class)
    public Board getBoard() {
        Board board = new Board();
        Chart chart = emergingMarketChart.getChart();

        Row rootRow = new Row();
        rootRow.add(chart, 2);

        Row nestedRow = new Row();

        List<MarketInvestmentResponse> response = new ArrayList<>(emergingMarketClient.getEmergingInvestmentValues());
        if(!response.isEmpty()) {
            MarketInvestmentResponse first = response.get(0);
            MarketInvestmentResponse last = response.get(response.size() - 1);

            nestedRow = new Row(new VisualStatistics("Entire change",
                    last.entireValuation.subtract(first.entireValuation).toString() + "zł",
                    last.entireValuation.subtract(first.entireValuation)
                            .divide(first.entireValuation, 2, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100)).toString() + "%"
            ));

            if(response.size() != 1) {
                MarketInvestmentResponse previous = response.get(response.size() - 2);

                nestedRow = new Row(new VisualStatistics("Entire change",
                        last.entireValuation.subtract(first.entireValuation).toString() + "zł",
                        last.entireValuation.subtract(first.entireValuation)
                                .divide(first.entireValuation, 2, RoundingMode.HALF_UP)
                                .multiply(BigDecimal.valueOf(100)).toString() + "%"
                ),
                        new Row(new VisualStatistics("Last change",
                                last.entireValuation.subtract(previous.entireValuation).toString() + "zł",
                                last.entireValuation.subtract(previous.entireValuation)
                                        .divide(previous.entireValuation, 2, RoundingMode.HALF_UP)
                                        .multiply(BigDecimal.valueOf(100)).toString() + "%"
                        )));
            }

        }

        rootRow.addNestedRow(nestedRow);
        board.add(rootRow);

        return board;
    }
}
