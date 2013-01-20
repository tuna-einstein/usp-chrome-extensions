package com.usp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt_test implements EntryPoint {
	TabsService service = new TabsService();
	
	final TabCloudWidget widget = new TabCloudWidget();
		
	public static native JsArrayString fetchUrls() /*-{
	return urls;
	}-*/;
	

	public static native JsArrayString fetchTitles() /*-{
	return tab_titles;
	}-*/;
	
	public static native void showNotification(String message) /*-{
	  showNotification(message);
	}-*/;
	
	

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		//while (fetchUrls().length() <= 0 );
		final Timer t = new Timer() {
		      public void run() {
		    	  isReceivedNesTabs();
		    	  this.schedule(5000);
		      }
		    };

		    // Schedule the timer to run once in 5 seconds.
		    t.schedule(5000);
		    
		    
		RootPanel.get("umasankar").add(widget);
		
		new Timer() {
		      public void run() {
		    	  while(fetchUrls().length()<=0);
		    	  widget.setTabs(fetchUrls(), fetchTitles());
		      }
		    }.schedule(100);
		
	}
	
	public void isReceivedNesTabs() {
		service.isReceivedNewTabs(new RequestCallback() {
			
			@Override
			public void onResponseReceived(Request request, Response response) {
				 if (200 != response.getStatusCode()) {
                     return;
                 }
				 String json = response.getText();
				 if (json.startsWith("http")) {
	                    Window.Location.assign(json);
	                    return;
	                }
	              if (response.getText().startsWith("true")) {
	            	  showNotification("http://usp046.appspot.com/getUnNotifiedTabs");
	           }
	                
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
