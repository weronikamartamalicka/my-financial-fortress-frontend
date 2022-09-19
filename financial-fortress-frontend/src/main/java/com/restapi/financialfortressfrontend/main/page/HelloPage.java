package com.restapi.financialfortressfrontend.main.page;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
@StyleSheet("/css/style.css")
public class HelloPage extends HorizontalLayout {

    public HelloPage() {

        add(new HelloPage2());

        Image image = new Image("https://finansowaforteca.pl/wp-content/webp-express/" +
                "webp-images/uploads/2022/09/FinForteca_mockup_3-low-res-2022-new.jpg.webp",
                "Cannot load image");

        add(image);
        setHeightFull();
    }

    private class HelloPage2 extends VerticalLayout {

        public HelloPage2() {
            add(new H1("BUILD YOUR FINANCIAL FORTRESS"));
            add(new H3("Do you want to invest, but don't know where to start?" +
                    " Do you feel resistance to dealing with your finances? Are you looking" +
                    " more and more closely at your financial future at the same time knowing " +
                    "that you need substantive support to take matters into your own hands?"));
            add(new H4("Instead of trying to \"learn how to invest\" build a step-by-step " +
                    "Financial Fortress with the My Financial Fortress app based on the knowledge" +
                    " from Marcin Iwuć's bestseller, which will effectively protect your and your family's wealth!"));

            Button buttonCreateInvest = new Button("WANT TO START");
            buttonCreateInvest.addClickListener(e -> {
                buttonCreateInvest.getUI().ifPresent(ui -> ui.navigate("home2"));
            });
            Button buttonAnalyzeInvest = new Button("I HAVE MY FORTRESS");
            buttonAnalyzeInvest.addClickListener(e -> {
                buttonAnalyzeInvest.getUI().ifPresent(ui -> ui.navigate("home"));
            });

            add(buttonCreateInvest);
            add(buttonAnalyzeInvest);
        }
    }
}
