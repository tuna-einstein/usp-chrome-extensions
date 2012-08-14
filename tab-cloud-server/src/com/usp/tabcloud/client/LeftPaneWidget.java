package com.usp.tabcloud.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class LeftPaneWidget extends Composite {

        private static LeftPaneWidgetUiBinder uiBinder = GWT
                        .create(LeftPaneWidgetUiBinder.class);

        interface LeftPaneWidgetUiBinder extends UiBinder<Widget, LeftPaneWidget> {
        }

        @UiField Anchor receivedTabs;
        @UiField Anchor sentTabs;
        Anchor currentSelection;
        Widget currentWidget;
        final TabsStackPanelWidget sentWidget = new TabsStackPanelWidget();
        final TabsStackPanelWidget receivedWidget = new TabsStackPanelWidget();
        
    final FlowPanel centerContentWidget;
        
        public LeftPaneWidget(FlowPanel centerContentWidget) {
                initWidget(uiBinder.createAndBindUi(this));
                currentSelection = receivedTabs;
                currentWidget = receivedWidget;
                this.centerContentWidget = centerContentWidget;
        }

        @UiHandler("receivedTabs")
        void handleClickAddTransaction(ClickEvent e) {
                currentSelection.getElement().removeClassName("selected");
          receivedTabs.getElement().addClassName("selected");
          currentSelection = receivedTabs;
          try {
            receivedWidget.getReceivedTabs();
        } catch (RequestException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
          centerContentWidget.clear();
      centerContentWidget.add(receivedWidget);
        }
        
        @UiHandler("sentTabs")
        void handleClickViewClaims(ClickEvent e) {
          currentSelection.getElement().removeClassName("selected");
          sentTabs.getElement().addClassName("selected");
          try {
            sentWidget.getReceivedTabs();
        } catch (RequestException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
          currentSelection = sentTabs;
          centerContentWidget.clear();
          centerContentWidget.add(sentWidget);
        }
}