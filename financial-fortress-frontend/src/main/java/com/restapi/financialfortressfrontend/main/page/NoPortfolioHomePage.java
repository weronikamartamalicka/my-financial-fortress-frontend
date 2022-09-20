package com.restapi.financialfortressfrontend.main.page;

import com.restapi.financialfortressfrontend.client.ModelPortfolioClient;
import com.restapi.financialfortressfrontend.domain.dto.InvestmentCapital;
import com.restapi.financialfortressfrontend.domain.dto.Video;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("home2")
@StyleSheet("/css/style2.css")
public class NoPortfolioHomePage extends HorizontalLayout {

    private ModelPortfolioClient modelPortfolioClient;

    @Autowired
    public NoPortfolioHomePage(ModelPortfolioClient modelPortfolioClient) {
        this.modelPortfolioClient = modelPortfolioClient;

        Video video = new Video("img/movie3.mp4");
        video.setHeight(50, Unit.PERCENTAGE);
        video.setWidth(50, Unit.PERCENTAGE);
        video.setWhiteSpace(HasText.WhiteSpace.INITIAL);
        add(video);

        setMargin(true);

        add(new NoPortfolioHomePage2());
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.END);

    }

    private class NoPortfolioHomePage2 extends VerticalLayout {

        public NoPortfolioHomePage2() {
            ComboBox<Long> selectCapital = new ComboBox<>("Select your capital to invest:");
            selectCapital.setItems(InvestmentCapital.SMALL.capital, InvestmentCapital.MEDIUM.capital,
                    InvestmentCapital.LARGE.capital);
            selectCapital.setWidth("100");

            Button button = new Button("BUILD FORTRESS");
            button.addClickListener(e -> {
                modelPortfolioClient.createPortfolio(selectCapital.getValue());
                button.getUI().ifPresent(ui -> ui.navigate("home"));
            });
            button.setHeight("50");

            H2 header = new H2("How to put together investment puzzles?");

            Image image = new Image("https://marciniwuc.com/wp-content/webp-express/webp-images/uploads/2020/12/portfel-lt.jpg.webp",
                    "Cannot load image");
            image.setHeight(70, Unit.PERCENTAGE);
            image.setWidth(70, Unit.PERCENTAGE);

            add(header);
            add(image);
            add(selectCapital);
            add(button);
        }
    }
}
