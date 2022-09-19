package com.restapi.financialfortressfrontend.charts;

import com.restapi.financialfortressfrontend.client.BondsIndexedClient;
import com.restapi.financialfortressfrontend.client.BondsQuotedClient;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BondsIndexedChart extends Chart {

    private BondsIndexedClient bondsIndexedClient;

    @Autowired
    public BondsIndexedChart(BondsIndexedClient bondsIndexedClient) {
        this.bondsIndexedClient = bondsIndexedClient;
    }

    public Chart getChart() {
        Chart chart = new Chart(ChartType.LINE);

        Configuration conf = chart.getConfiguration();
        PlotOptionsPie options = new PlotOptionsPie();
        DataSeries series = new DataSeries("Inflation indexed bonds value");
        Tooltip tooltip = new Tooltip();

        tooltip.setValueDecimals(1);
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);

        XAxis xaxis = new XAxis();

        bondsIndexedClient.getBondInvestmentValues().stream()
                .forEach(bondsResponse -> {
                    xaxis.addCategory(bondsResponse.date.toString());
                    series.add(new DataSeriesItem("entireValuation", bondsResponse.entireValuation));
                });

        conf.addSeries(series);
        conf.addxAxis(xaxis);
        chart.setVisibilityTogglingDisabled(true);

        conf.setTitle("Inflation indexed bonds values change");
        options.setInnerSize("0");
        options.setSize("75%");
        options.setCenter("50%", "50%");
        conf.setPlotOptions(options);

        return chart;
    }

    public Chart getInflationChart() {
        Chart chart = new Chart(ChartType.LINE);

        Configuration conf = chart.getConfiguration();
        PlotOptionsPie options = new PlotOptionsPie();
        DataSeries series1 = new DataSeries("Inflation value");
        DataSeries series2 = new DataSeries("Interest rate value");
        Tooltip tooltip = new Tooltip();

        tooltip.setValueDecimals(1);
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);

        XAxis xaxis = new XAxis();

        bondsIndexedClient.getAllInflationValues().stream()
                .forEach(bondsResponse -> {
                    xaxis.addCategory(bondsResponse.date.toString());
                    series1.add(new DataSeriesItem("inflation", bondsResponse.valuation.multiply(BigDecimal.valueOf(100))));
                    series2.add(new DataSeriesItem("interestRate", bondsResponse.interestsValuation
                            .multiply(BigDecimal.valueOf(100))));
                });

        conf.addSeries(series1);
        conf.addSeries(series2);
        conf.addxAxis(xaxis);
        chart.setVisibilityTogglingDisabled(true);

        conf.setTitle("Interest rate change");
        options.setInnerSize("0");
        options.setSize("75%");
        options.setCenter("50%", "50%");
        conf.setPlotOptions(options);

        return chart;
    }
}
