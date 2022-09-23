package com.restapi.financialfortressfrontend.charts;

import com.restapi.financialfortressfrontend.client.GoldClient;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoldValuesChart extends Chart {
    private GoldClient goldClient;
    @Autowired
    public GoldValuesChart(GoldClient goldClient) {
        this.goldClient = goldClient;
    }

    public Chart getChart() {
        Chart chart = new Chart(ChartType.LINE);

        Configuration conf = chart.getConfiguration();
        PlotOptionsPie options = new PlotOptionsPie();
        DataSeries series = new DataSeries("Gold value");
        Tooltip tooltip = new Tooltip();

        tooltip.setValueDecimals(1);
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);

        XAxis xaxis = new XAxis();

        goldClient.getAllGoldInvestmentValues().stream()
                .forEach(goldResponse -> {
                    series.add(new DataSeriesItem("Gold Coin Investment", goldResponse.entireValuation));
                    xaxis.addCategory(goldResponse.date.toString());
                });

        conf.addSeries(series);
        conf.addxAxis(xaxis);
        chart.setVisibilityTogglingDisabled(true);

        conf.setTitle("Gold values change");
        options.setInnerSize("0");
        options.setSize("75%");
        options.setCenter("50%", "50%");
        conf.setPlotOptions(options);

        return chart;
    }
}
