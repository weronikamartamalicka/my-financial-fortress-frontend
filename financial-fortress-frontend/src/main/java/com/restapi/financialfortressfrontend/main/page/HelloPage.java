package com.restapi.financialfortressfrontend.main.page;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class HelloPage extends VerticalLayout {

    public HelloPage() {
        add(new Text("Have you created your investment yet?"));
        add(new Image("https://finansowaforteca.pl/wp-content/webp-express/" +
                "webp-images/uploads/2022/09/FinForteca_mockup_3-low-res-2022-new.jpg.webp",
                "Cannot load image"));
        add(new HelloPage2());
    }

    private class HelloPage2 extends HorizontalLayout {

        public HelloPage2() {
            Button buttonCreateInvest = new Button("No, I have not! Let's create one!");
            buttonCreateInvest.addClickListener(e -> {
                buttonCreateInvest.getUI().ifPresent(ui -> ui.navigate("home2"));
            });
            Button buttonAnalyzeInvest = new Button("Yup! I want to have a look at my portfolio");
            buttonAnalyzeInvest.addClickListener(e -> {
                buttonAnalyzeInvest.getUI().ifPresent(ui -> ui.navigate("home"));
            });

            add(buttonCreateInvest);
            add(buttonAnalyzeInvest);
        }
    }
}
