package com.restapi.financialfortressfrontend;

import com.restapi.financialfortressfrontend.client.ModelPortfolioClient;
import com.restapi.financialfortressfrontend.domain.PortfolioValuesResponse;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.vaadin.flow.component.charts.model.style.SolidColor.BEIGE;

@Component
public class MainPortfolioChart extends Chart {
    private ModelPortfolioClient modelPortfolioClient;

    @Autowired
    public MainPortfolioChart(ModelPortfolioClient modelPortfolioClient) {
        this.modelPortfolioClient = modelPortfolioClient;

    }

    public Chart getChart() {
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();
        PlotOptionsPie options = new PlotOptionsPie();

        conf.setTitle("Current breakdown of your portfolio into financial instruments");
        options.setInnerSize("0");
        options.setSize("75%");
        options.setCenter("50%", "50%");
        conf.setPlotOptions(options);

        Tooltip tooltip = new Tooltip();
        tooltip.setValueDecimals(1);
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);
        conf.setPlotOptions(plotOptions);

        PortfolioValuesResponse portfolioResponse = modelPortfolioClient.getActualPortfolioValues();

        DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("Gold", portfolioResponse.goldValue));
        series.add(new DataSeriesItem("EmergingMarketETF", portfolioResponse.emergingMarketValue, BEIGE));
        series.add(new DataSeriesItem("DevelopedMarketETF", portfolioResponse.developedMarketValue));
        series.add(new DataSeriesItem("InflationIndexedBonds", portfolioResponse.bondsIndexedValue));
        series.add(new DataSeriesItem("BondsQuotedOnTheMarket", portfolioResponse.bondsQuotedValue));
        conf.addSeries(series);
        chart.setVisibilityTogglingDisabled(true);

        Options3d options3d = new Options3d();
        options3d.setEnabled(true);
        options3d.setAlpha(30);
        options3d.setBeta(10);
        options3d.setDepth(135);
        options3d.setViewDistance(100);
        conf.getChart().setOptions3d(options3d);

        options.setDepth(70);

        return chart;
    }
}
