package com.restapi.financialfortressfrontend.main.page;

import com.restapi.financialfortressfrontend.client.ModelPortfolioClient;
import com.restapi.financialfortressfrontend.domain.dto.InvestmentCapital;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("home2")
public class NoPortfolioHomePage extends HorizontalLayout {

    private ModelPortfolioClient modelPortfolioClient;

    @Autowired
    public NoPortfolioHomePage(ModelPortfolioClient modelPortfolioClient) {
        this.modelPortfolioClient = modelPortfolioClient;

        add(new Image("https://marciniwuc.com/wp-content/webp-express/webp-images/uploads/2021/04/Strategia-3-portfeli.jpg.webp",
                "Cannot load image"));

        add(new NoPortfolioHomePage2());
    }

    private class NoPortfolioHomePage2 extends VerticalLayout {

        public NoPortfolioHomePage2() {
            ComboBox<Long> selectCapital = new ComboBox<>("Select your capital to invest:");
            selectCapital.setItems(InvestmentCapital.SMALL.capital, InvestmentCapital.MEDIUM.capital,
                    InvestmentCapital.LARGE.capital);

            selectCapital.setWidth("100");

            Button button = new Button("Create my LONG-TERM portfolio");
            button.getElement().addEventListener("click", e -> {
                modelPortfolioClient.createPortfolio(selectCapital.getValue());
                button.getUI().ifPresent(ui -> ui.navigate("home"));
            });

            button.setHeight("50");

            add(selectCapital);
            add(button);
            add(new Image("https://marciniwuc.com/wp-content/webp-express/webp-images/uploads/2020/12/portfel-lt.jpg.webp",
                    "Cannot load image"));
        }
    }
}
