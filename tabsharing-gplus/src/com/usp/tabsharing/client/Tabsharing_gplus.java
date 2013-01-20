package com.usp.tabsharing.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tabsharing_gplus implements EntryPoint {

	TabsService service = new TabsService();

	final TabSelectionWidget widget = new TabSelectionWidget();

	public static native JsArrayString fetchUrls() /*-{
	return urls;
	}-*/;


	public static native JsArrayString fetchTitles() /*-{
	return tab_titles;
	}-*/;

	public static native String fetchCurrentTabUrl() /*-{
	return current_tab_url;
	}-*/;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel.get("umasankar").add(widget);
		new Timer() {
			public void run() {
				if (fetchUrls().length()<=0) {
					this.schedule(1000);
					return;
				}
				widget.setTabs(fetchUrls(), fetchTitles(),
						fetchCurrentTabUrl());
			}
		}.schedule(100);
	}
}
