package com.restapi.financialfortressfrontend;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
public class VisualStatistics extends VerticalLayout {

    public VisualStatistics(String category, String valueChange, String change) {
        Span title = new Span(category);
        title.getElement().getThemeList().add("badge primary");
        H4 value = new H4(valueChange);
        Span percent;

        if(Integer.parseInt(valueChange.substring(0, valueChange.length() - 5)) >= 0) {
            percent = new Span(createIcon(VaadinIcon.ARROW_UP), new Span(change));
            percent.getElement().getThemeList().add("success primary");
        } else {
            percent = new Span(createIcon(VaadinIcon.ARROW_DOWN), new Span(change));
            percent.getElement().getThemeList().add("error primary");
        }

        add(title, value, percent);
    }

    private Icon createIcon(VaadinIcon vaadinIcon) {
        Icon icon = vaadinIcon.create();
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }
}
