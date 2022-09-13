package com.restapi.financialfortressfrontend.boards;

import com.restapi.financialfortressfrontend.VisualStatistics;
import com.restapi.financialfortressfrontend.charts.EmergingMarketChart;
import com.restapi.financialfortressfrontend.client.EmergingMarketClient;
import com.restapi.financialfortressfrontend.domain.dto.EmergingInvestmentResponse;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.board.Row;
import com.vaadin.flow.component.charts.Chart;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Board getBoard() {
        Board board = new Board();
        Chart chart = emergingMarketChart.getChart();

        Row rootRow = new Row();
        rootRow.add(chart, 2);

        List<EmergingInvestmentResponse> response = new ArrayList<>(emergingMarketClient.getEmergingInvestmentValues());
        EmergingInvestmentResponse first = response.get(0);
        EmergingInvestmentResponse last = response.get(response.size() - 1);
        EmergingInvestmentResponse previous = response.get(response.size() - 2);

        Row nestedRow = new Row(new VisualStatistics("Entire change",
                last.entireValuation.subtract(first.entireValuation).toString() + "zł",
                last.entireValuation.subtract(first.entireValuation)
                        .divide(first.entireValuation, 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100)).toString() + "%"
        ),
                new Row(new VisualStatistics("Last change",
                        last.entireValuation.subtract(previous.entireValuation).toString() + "zł",
                        last.entireValuation.subtract(previous.entireValuation)
                                .divide(previous.entireValuation, 4, RoundingMode.HALF_UP)
                                .multiply(BigDecimal.valueOf(100)).toString() + "%"
                )));
        rootRow.addNestedRow(nestedRow);
        board.add(rootRow);

        return board;
    }
}
