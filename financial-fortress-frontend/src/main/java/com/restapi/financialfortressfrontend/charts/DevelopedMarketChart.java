package com.restapi.financialfortressfrontend.charts;

import com.restapi.financialfortressfrontend.client.DevelopedMarketClient;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DevelopedMarketChart extends Chart {
    private DevelopedMarketClient developedMarketClient;

    @Autowired
    public DevelopedMarketChart(DevelopedMarketClient developedMarketClient) {
        this.developedMarketClient = developedMarketClient;
    }

    public Chart getChart() {
        Chart chart = new Chart(ChartType.LINE);

        Configuration conf = chart.getConfiguration();
        PlotOptionsPie options = new PlotOptionsPie();
        DataSeries series = new DataSeries("Developed market ETF value");
        Tooltip tooltip = new Tooltip();

        tooltip.setValueDecimals(1);
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);

        XAxis xaxis = new XAxis();

        developedMarketClient.getDevelopedInvestmentValues().stream()
                .forEach(bondsResponse -> {
                    series.add(new DataSeriesItem("DevelopedMarketETF", bondsResponse.entireValuation));
                    xaxis.addCategory(bondsResponse.date.toString());
                });

        conf.addSeries(series);
        conf.addxAxis(xaxis);
        chart.setVisibilityTogglingDisabled(true);

        conf.setTitle("Developed market ETF values change");
        options.setInnerSize("0");
        options.setSize("75%");
        options.setCenter("50%", "50%");
        conf.setPlotOptions(options);

        return chart;
    }
}
