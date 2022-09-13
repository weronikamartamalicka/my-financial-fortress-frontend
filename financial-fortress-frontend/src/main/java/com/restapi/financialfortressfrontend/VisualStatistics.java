package com.restapi.financialfortressfrontend;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class VisualStatistics extends VerticalLayout {

    public VisualStatistics(String category, String subcategory, String change) {
        Paragraph title = new Paragraph(category);
        H4 subTitle = new H4(subcategory);
        H5 number = new H5(change);

        add(title, subTitle, number);
    }
}
