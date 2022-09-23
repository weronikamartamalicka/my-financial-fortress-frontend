package com.restapi.financialfortressfrontend.charts;

import com.restapi.financialfortressfrontend.client.EmergingMarketClient;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmergingMarketChart extends Chart{
    private EmergingMarketClient emergingMarketClient;

    @Autowired
    public EmergingMarketChart(EmergingMarketClient emergingMarketClient) {
        this.emergingMarketClient = emergingMarketClient;
    }

    public Chart getChart() {
        Chart chart = new Chart(ChartType.LINE);

        Configuration conf = chart.getConfiguration();
        PlotOptionsPie options = new PlotOptionsPie();
        DataSeries series = new DataSeries("Emerging market ETF value");
        Tooltip tooltip = new Tooltip();

        tooltip.setValueDecimals(1);
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);

        XAxis xaxis = new XAxis();

        emergingMarketClient.getEmergingInvestmentValues().stream()
                .forEach(bondsResponse -> {
                    series.add(new DataSeriesItem("EmergingMarketETF", bondsResponse.entireValuation));
                    xaxis.addCategory(bondsResponse.date.toString());
                });

        conf.addSeries(series);
        conf.addxAxis(xaxis);
        chart.setVisibilityTogglingDisabled(true);

        conf.setTitle("Emerging market ETF values change");
        options.setInnerSize("0");
        options.setSize("75%");
        options.setCenter("50%", "50%");
        conf.setPlotOptions(options);

        return chart;
    }
}
