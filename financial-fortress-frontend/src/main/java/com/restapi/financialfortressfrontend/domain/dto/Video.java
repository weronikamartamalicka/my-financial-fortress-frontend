package com.restapi.financialfortressfrontend.domain.dto;

import com.vaadin.flow.component.*;

@Tag("video")
public class Video extends HtmlContainer implements ClickNotifier<com.vaadin.flow.component.html.Image> {

    private static final PropertyDescriptor<String, String> srcDescriptor = PropertyDescriptors
            .attributeWithDefault("src", "");

    public Video(String src) {
        setSrc(src);
        getElement().setProperty("controls", true);
    }

    public Video() {
        super();
        getElement().setProperty("controls", true);
    }

    public String getSrc() {
        return get(srcDescriptor);
    }

    public void setSrc(String src) {
        set(srcDescriptor, src);
    }
}
