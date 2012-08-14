package com.usp.tabcloud.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class Main1 implements EntryPoint {
    private final TabsService service = new TabsService();
    final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.PX);
    final TopPanelWidget topPane = new TopPanelWidget();
    final ContentPaneWidget contentPane = new ContentPaneWidget();
    final LeftPaneWidget leftPane = new LeftPaneWidget(contentPane.contentPanel);
    Resources resources = GWT.create(Resources.class);
    final Image busyImage = new Image();
    private void init() {
        dockLayoutPanel.addNorth(topPane, 80);
        dockLayoutPanel.addWest(leftPane, 250);
        dockLayoutPanel.add(contentPane);
        dockLayoutPanel.setSize("1000px", "700px");
        RootPanel.get("umasankar").remove(busyImage);
        RootPanel.get("umasankar").add(dockLayoutPanel);
    }

    public void onModuleLoad() {
        busyImage.setResource(resources.logo());
        RootPanel.get("umasankar").add(busyImage);
        getLoggedInUser();
    }
    
    public void getLoggedInUser() {
        RequestCallback c = new RequestCallback () {

            public void onResponseReceived(Request request, Response response) {
                if (200 != response.getStatusCode()) {
                    return;
                }
               
                String json = response.getText();
                if (json.startsWith("http")) {
                    Window.Location.assign(json);
                } else {
                    topPane.setOwnerEmail(json);
                    init();
                }

            }
            public void onError(Request request, Throwable exception) {
                // TODO Auto-generated method stub
            }
        };
        service.getLoggedInUser(c);
    }
}
