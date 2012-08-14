package com.usp.tabcloud.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.usp.tabcloud.client.JavaScriptObjects.TabsInfoJSO;

public class TabsViewWidget extends Composite {

	private static TabsViewWidgetUiBinder uiBinder = GWT
			.create(TabsViewWidgetUiBinder.class);

	interface TabsViewWidgetUiBinder extends UiBinder<Widget, TabsViewWidget> {
	}

	interface CssStyle extends CssResource {
		String tabStyle();
	}
	
	private Widget parentWidget;

	@UiField FlowPanel mainPanel;
	@UiField CssStyle style;
	@UiField Anchor openAllTabs;
	@UiField Anchor deleteAllTabs;
	private TabsService service;
	private TabsInfoJSO tabs;
	public TabsViewWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		service = new TabsService();
	}

	public void setTabs(TabsInfoJSO tabs) {
		this.tabs = tabs;
		mainPanel.clear();
		for (int i = 0; i < tabs.getUrls().length(); i++) {
			Anchor l = new Anchor(tabs.getUrls().get(i));
			l.setStylePrimaryName(style.tabStyle());
			l.setHref(tabs.getUrls().get(i));
			l.setTarget("_blank");
			mainPanel.add(l);
		}
	}

	public void setParentWidget(Widget widget) {
	    this.parentWidget = widget;
	}
	
	@UiHandler("openAllTabs")
	public void onClickOpenAllTabs(ClickEvent e) {
	    for (int i = 0; i < tabs.getUrls().length(); i++) {
	        Window.open(tabs.getUrls().get(i), "", "");
        }
	}
	
	@UiHandler("deleteAllTabs")
    public void onClickDeleteAllTabs(ClickEvent e)  {
	    deleteAllTabs.setVisible(false);
        try {
            
            service.deleteTabsInfo(tabs.getKeyAsString(),
                new RequestCallback() {
                    
                    public void onResponseReceived(Request request, Response response) {
                        deleteAllTabs.setVisible(true);
                        if (200 != response.getStatusCode()) {
                            return;
                        }
                       
                        hide();
                        
                    }
                    
                    public void onError(Request request, Throwable exception) {
                        // TODO Auto-generated method stub
                        deleteAllTabs.setVisible(true);  
                    }
                });
        } catch (RequestException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
	
	private final void hide() {
	    this.parentWidget.removeFromParent();
	}
}
