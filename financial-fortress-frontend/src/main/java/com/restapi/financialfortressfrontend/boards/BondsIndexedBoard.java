package com.restapi.financialfortressfrontend.boards;

import com.restapi.financialfortressfrontend.VisualStatistics;
import com.restapi.financialfortressfrontend.charts.BondsIndexedChart;
import com.restapi.financialfortressfrontend.client.BondsIndexedClient;
import com.restapi.financialfortressfrontend.domain.dto.BondsIndexedInvestmentResponse;
import com.restapi.financialfortressfrontend.main.page.HomePage;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.board.Row;
import com.vaadin.flow.component.charts.Chart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class BondsIndexedBoard extends Board {
    private BondsIndexedClient bondsIndexedClient;
    private BondsIndexedChart bondsIndexedChart;

    @Autowired
    public BondsIndexedBoard(BondsIndexedClient bondsIndexedClient, BondsIndexedChart bondsIndexedChart) {
        this.bondsIndexedClient = bondsIndexedClient;
        this.bondsIndexedChart = bondsIndexedChart;
    }
    @EventListener(HomePage.class)
    public Board getBoard() {
        Board board = new Board();
        Chart chart = bondsIndexedChart.getChart();

        Row rootRow = new Row();
        rootRow.add(chart, 2);

        Row nestedRow = new Row();

        List<BondsIndexedInvestmentResponse> response = bondsIndexedClient.getBondInvestmentValues();
        if(!response.isEmpty()) {
            BondsIndexedInvestmentResponse first = response.get(0);
            BondsIndexedInvestmentResponse last = Optional.ofNullable(response.get(response.size() - 1))
                    .orElse(new BondsIndexedInvestmentResponse(LocalDateTime.now(), BigDecimal.ONE));

            nestedRow = new Row(new VisualStatistics("Entire change",
                    last.entireValuation.subtract(first.entireValuation).toString() + "zł",
                    last.entireValuation.subtract(first.entireValuation)
                            .divide(first.entireValuation, 2, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100)).toString() + "%"
            ));

            if(response.size() != 1) {
                BondsIndexedInvestmentResponse previous = Optional.ofNullable(response.get(response.size() - 2))
                        .orElse(new BondsIndexedInvestmentResponse(LocalDateTime.now(), BigDecimal.ONE));

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
