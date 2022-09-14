package com.restapi.financialfortressfrontend.boards;

import com.restapi.financialfortressfrontend.VisualStatistics;
import com.restapi.financialfortressfrontend.client.ModelPortfolioClient;
import com.restapi.financialfortressfrontend.domain.dto.PortfolioValuesResponse;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.board.Row;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Component
public class MainPortfolioBoard extends Board {

    private ModelPortfolioClient modelPortfolioClient;

    @Autowired
    public MainPortfolioBoard(ModelPortfolioClient modelPortfolioClient) {
        this.modelPortfolioClient = modelPortfolioClient;
    }

    public Board getBoard() {
        Chart chart = new Chart(ChartType.LINE);
        Board board = new Board();

        Configuration conf = chart.getConfiguration();
        PlotOptionsPie options = new PlotOptionsPie();
        DataSeries series = new DataSeries("Whole portfolio value");
        Tooltip tooltip = new Tooltip();

        tooltip.setValueDecimals(1);
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);

        XAxis xaxis = new XAxis();

        modelPortfolioClient.getAllPortfolioValues().stream()
                .forEach(portfolioValuesResponse -> {
                    series.add(new DataSeriesItem(portfolioValuesResponse.date.toString()
                            , portfolioValuesResponse.entireValue));
                    xaxis.addCategory(portfolioValuesResponse.date.toString());
                });

        conf.addSeries(series);
        conf.addxAxis(xaxis);
        chart.setVisibilityTogglingDisabled(true);

        conf.setTitle("Change in your portfolio value");
        options.setInnerSize("0");
        options.setSize("75%");
        options.setCenter("50%", "50%");
        conf.setPlotOptions(options);

        Row rootRow = new Row();
        Row nestedRow = new Row();

        List<PortfolioValuesResponse> response = new ArrayList<>(modelPortfolioClient.getAllPortfolioValues());
        if(!response.isEmpty()) {
            PortfolioValuesResponse first = response.get(0);
            PortfolioValuesResponse last = response.get(response.size() - 1);

            nestedRow = new Row(new VisualStatistics("Entire change",
                    last.entireValue.subtract(first.entireValue).toString() + "zł",
                    last.entireValue.subtract(first.entireValue)
                            .divide(first.entireValue, 2, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100))
                            .toString() + "%"
            ));

            if(response.size() != 1) {
                PortfolioValuesResponse previous = response.get(response.size() - 2);

                nestedRow = new Row(new VisualStatistics("Entire change",
                        last.entireValue.subtract(first.entireValue).toString() + "zł",
                        last.entireValue.subtract(first.entireValue)
                                .divide(first.entireValue, 2, RoundingMode.HALF_UP)
                                .multiply(BigDecimal.valueOf(100))
                                .toString() + "%"
                ),
                        new Row(new VisualStatistics("Last change",
                                last.entireValue.subtract(previous.entireValue).toString() + "zł",
                                last.entireValue.subtract(previous.entireValue)
                                        .divide(previous.entireValue, 2, RoundingMode.HALF_UP)
                                        .multiply(BigDecimal.valueOf(100))
                                        .toString() + "%"
                        )
                        ));
            }

        }

        rootRow.addNestedRow(nestedRow);
        rootRow.add(chart, 2);
        board.add(rootRow);

        return board;
    }
}
