package com.restapi.financialfortressfrontend.boards;

import com.restapi.financialfortressfrontend.VisualStatistics;
import com.restapi.financialfortressfrontend.charts.BondQuotedChart;
import com.restapi.financialfortressfrontend.client.BondsQuotedClient;
import com.restapi.financialfortressfrontend.domain.dto.BondsInvestmentResponse;
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
public class BondsQuotedBoard extends Board {

    private BondsQuotedClient bondsQuotedClient;
    private BondQuotedChart bondQuotedChart;

    @Autowired
    public BondsQuotedBoard(BondsQuotedClient bondsQuotedClient, BondQuotedChart bondQuotedChart) {
        this.bondsQuotedClient = bondsQuotedClient;
        this.bondQuotedChart = bondQuotedChart;
    }

    public Board getBoard() {
        Board board = new Board();
        Chart chart = bondQuotedChart.getChart();

        Row rootRow = new Row();
        rootRow.add(chart, 2);

        List<BondsInvestmentResponse> response = new ArrayList<>(bondsQuotedClient.getBondInvestmentValues());
        BondsInvestmentResponse first = response.get(0);
        BondsInvestmentResponse last = response.get(response.size() - 1);
        BondsInvestmentResponse previous = response.get(response.size() - 2);

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
