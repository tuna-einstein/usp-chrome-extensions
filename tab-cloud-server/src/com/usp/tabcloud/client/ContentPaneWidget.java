package com.usp.tabcloud.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class ContentPaneWidget extends Composite {

        private static ContentPaneWidgetUiBinder uiBinder = GWT
                        .create(ContentPaneWidgetUiBinder.class);

        interface ContentPaneWidgetUiBinder extends
                        UiBinder<Widget, ContentPaneWidget> {
        }

        @UiField FlowPanel contentPanel;
        public ContentPaneWidget() {
                initWidget(uiBinder.createAndBindUi(this));
        }
        
        public void addWidget(Widget w) {
           contentPanel.add(w);
        }
}