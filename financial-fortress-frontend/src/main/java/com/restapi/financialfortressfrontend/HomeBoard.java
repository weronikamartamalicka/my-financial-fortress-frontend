package com.restapi.financialfortressfrontend;

import com.restapi.financialfortressfrontend.client.ModelPortfolioClient;
import com.restapi.financialfortressfrontend.domain.PortfolioValuesResponse;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.vaadin.flow.component.charts.model.style.SolidColor.BEIGE;

@Route("board")
@Component
public class HomeBoard extends VerticalLayout {
    private ModelPortfolioClient modelPortfolioClient;

    @Autowired
    public HomeBoard(ModelPortfolioClient modelPortfolioClient) {
        this.modelPortfolioClient = modelPortfolioClient;

    }

    public Board getBoard() {
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();
        PlotOptionsPie options = new PlotOptionsPie();
        options.setInnerSize("0");
        options.setSize("75%");
        options.setCenter("50%", "50%");
        conf.setPlotOptions(options);

        PortfolioValuesResponse portfolioResponse = modelPortfolioClient.getActualPortfolioValues();

        DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("Gold", portfolioResponse.goldValue));
        series.add(new DataSeriesItem("EmergingMarketETF", portfolioResponse.emergingMarketValue, BEIGE));
        series.add(new DataSeriesItem("DevelopedMarketETF", portfolioResponse.developedMarketValue));
        series.add(new DataSeriesItem("InflationIndexedBonds", portfolioResponse.bondsIndexedValue));
        series.add(new DataSeriesItem("BondsQuotedOnTheMarket", portfolioResponse.bondsQuotedValue));
        conf.addSeries(series);

        Options3d options3d = new Options3d();
        options3d.setEnabled(true);
        options3d.setAlpha(30);
        options3d.setBeta(10);
        options3d.setDepth(135);
        options3d.setViewDistance(100);
        conf.getChart().setOptions3d(options3d);

        options.setDepth(70);

        Board board = new Board();
        board.addRow(
                new ExampleIndicator("Current users", "745", "+33.7"),
                new ExampleIndicator("View events", "54.6k", "-112.45"),
                new ExampleIndicator("Conversion rate", "18%", "+3.9"),
                new ExampleIndicator("Custom metric", "-123.45")
        );
        board.addRow(new ExampleChart());
        board.addRow(chart);

        return board;
    }
}
