package com.usp.tabcloud.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.usp.tabcloud.client.JavaScriptObjects.TabsInfoJSO;

public class TabsStackPanelWidget extends Composite {

    private static TabsStackPanelWidgetUiBinder uiBinder = GWT
            .create(TabsStackPanelWidgetUiBinder.class);

    interface TabsStackPanelWidgetUiBinder extends
    UiBinder<Widget, TabsStackPanelWidget> {
    }

    @UiField FlowPanel mainPanel;
    @UiField Image busyImage;
    private final TabsService service;

    public TabsStackPanelWidget() {
        initWidget(uiBinder.createAndBindUi(this));
        service = new TabsService();
    }

    public void getReceivedTabs() throws RequestException {
        busyImage.setVisible(true);
        mainPanel.setVisible(false);
        RequestCallback c = new RequestCallback () {

            public void onResponseReceived(Request request, Response response) {
                if (200 != response.getStatusCode()) {
                    return;
                }
                mainPanel.clear();
                String json = response.getText();
                if (json.startsWith("http")) {
                    Window.Location.assign(json);
                }
                
                JsArray<TabsInfoJSO> tabsInfoArray =
                        JavaScriptObjects.TabsInfoJSO.asArrayOfTabsInfoJSO(json);
                for (int i= 0 ; i< tabsInfoArray.length(); i++) {
                    addReceivedTabInfo(tabsInfoArray.get(i));
                }
                busyImage.setVisible(false);
                mainPanel.setVisible(true);
            }

            public void onError(Request request, Throwable exception) {
                // TODO Auto-generated method stub

            }

        };
        service.getReceivedTabs(c);
    }

    public void getSentTabs() throws RequestException {

        RequestCallback c = new RequestCallback () {

            public void onResponseReceived(Request request, Response response) {
                if (200 != response.getStatusCode()) {
                    return;
                }
                mainPanel.clear();
                
                String json = response.getText();
                
                if (json.startsWith("http")) {
                    Window.Location.assign(json);
                }

                JsArray<TabsInfoJSO> tabsInfoArray =
                        JavaScriptObjects.TabsInfoJSO.asArrayOfTabsInfoJSO(json);
                for (int i= 0 ; i< tabsInfoArray.length(); i++) {
                    
                    addSentTabInfo(tabsInfoArray.get(i));
                }

            }

            public void onError(Request request, Throwable exception) {
                // TODO Auto-generated method stub

            }

        };
        service.getSentTabs(c);
    }

    private final void addSentTabInfo(TabsInfoJSO tabsInfo) {
        TabsViewWidget widget = new TabsViewWidget();
        
        widget.setTabs(tabsInfo);
        DisclosurePanel dpanel = new DisclosurePanel(tabsInfo.getDate() + " " + tabsInfo.getReceiverEmail());
        dpanel.add(widget);
        widget.setParentWidget(dpanel);
        mainPanel.add(dpanel);
    }
    
    private final void addReceivedTabInfo(TabsInfoJSO tabsInfo) {
        TabsViewWidget widget = new TabsViewWidget();
        
        widget.setTabs(tabsInfo);
        DisclosurePanel dpanel = new DisclosurePanel(tabsInfo.getDate() + " " + tabsInfo.getRequesterEmail());
        dpanel.add(widget);
        widget.setParentWidget(dpanel);
        mainPanel.add(dpanel);
    }
}
