package com.usp.tabcloud.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TopPanelWidget extends Composite {

        private static TopPanelWidgetUiBinder uiBinder = GWT
                        .create(TopPanelWidgetUiBinder.class);

        interface TopPanelWidgetUiBinder extends UiBinder<Widget, TopPanelWidget> {
        }
        
        @UiField Label ownerEmail;
        
        
        public TopPanelWidget() {
                initWidget(uiBinder.createAndBindUi(this));
        }
        
        public void setOwnerEmail( String ownerEmail) {
            this.ownerEmail.setText(ownerEmail);
        }
      
        
        @UiHandler("logout")
        public void clickHandleLogout(ClickEvent event) {
            Window.Location.replace("/logoutURL");
        }
}