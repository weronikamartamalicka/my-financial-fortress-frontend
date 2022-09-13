package com.restapi.financialfortressfrontend.boards;

import com.restapi.financialfortressfrontend.VisualStatistics;
import com.restapi.financialfortressfrontend.charts.GoldValuesChart;
import com.restapi.financialfortressfrontend.client.GoldClient;
import com.restapi.financialfortressfrontend.domain.dto.GoldResponse;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.board.Row;
import com.vaadin.flow.component.charts.Chart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GoldBoard extends Board {

    private GoldClient goldClient;
    private GoldValuesChart goldValuesChart;

    @Autowired
    public GoldBoard(GoldClient goldClient, GoldValuesChart goldValuesChart) {
        this.goldClient = goldClient;
        this.goldValuesChart = goldValuesChart;
    }

    public Board getBoard() {
        Board board = new Board();
        Chart chart = goldValuesChart.getChart();

        Row rootRow = new Row();
        rootRow.add(chart, 2);

        List<GoldResponse> response = new ArrayList<>(goldClient.getAllGoldValues());
        GoldResponse first = response.get(0);
        GoldResponse last = response.get(response.size() - 1);
        GoldResponse previous = response.get(response.size() - 2);

        Row nestedRow = new Row(new VisualStatistics("Entire change",
                Double.toString(last.oneCoinPrice - first.oneCoinPrice) + "zł",
                Double.toString(100 * ((last.oneCoinPrice - first.oneCoinPrice) / first.oneCoinPrice)) + "%"
        ),
                new Row(new VisualStatistics("Last change",
                        Double.toString(last.oneCoinPrice - previous.oneCoinPrice) + "zł",
                        Double.toString(100 * ((last.oneCoinPrice - previous.oneCoinPrice) / previous.oneCoinPrice)) + "%"
                )
                ));
        rootRow.addNestedRow(nestedRow);
        board.add(rootRow);

        return board;
    }
}
