package com.usp.tabcloud.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class Main implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        FlowPanel mainPanel = new FlowPanel();
       
        // Sent tabs Panel
        DisclosurePanel sentPanel = new DisclosurePanel();
        Anchor sentHeader = new Anchor("Sent Tabs");
        sentHeader.setStylePrimaryName("disclosureHeader");
        
        sentPanel.setHeader(sentHeader);
        
        TabsStackPanelWidget sentWidget = new TabsStackPanelWidget();
        try {
            sentWidget.getSentTabs();
        } catch (RequestException e) {
            e.printStackTrace();
        }
        sentPanel.setContent(sentWidget);
       
        mainPanel.add(sentPanel);
        
        // Received tabs Panel
        DisclosurePanel receivedPanel = new DisclosurePanel();
        Anchor receivedHeader = new Anchor("Received Tabs");
        receivedHeader.setStylePrimaryName("disclosureHeader");
        receivedPanel.setHeader(receivedHeader);
        TabsStackPanelWidget receivedWidget = new TabsStackPanelWidget();
        try {
            receivedWidget.getReceivedTabs();
        } catch (RequestException e) {
            e.printStackTrace();
        }
        receivedPanel.setContent(receivedWidget);
        mainPanel.add(receivedPanel); 
        RootPanel.get("umasankar").add(mainPanel);
        
        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element
        
    }
}