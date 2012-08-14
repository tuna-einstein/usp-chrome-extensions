package com.usp.tabcloud.client;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;

public class TabsService {
	
	public void getReceivedTabs(RequestCallback callback) throws RequestException {
		String url = "getReceivedTabs";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
		builder.sendRequest(null, callback);
	}
	
	public void getLoggedInUser(RequestCallback callback) {
        String url = "getLoggedInUser";
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
        try {
            builder.sendRequest(null, callback);
        } catch (RequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public void getSentTabs(RequestCallback callback) throws RequestException {
		String url = "getSentTabs";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
		builder.sendRequest(null, callback);
	}
	
	public void deleteTabsInfo(String key, RequestCallback callback) throws RequestException {
        String url = "deleteTabInfo";
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
        builder.setHeader("Content-type", "application/x-www-form-urlencoded");
        String data = "keyAsString=" + key;
        builder.sendRequest(data, callback);
    }

	public void redirectToUrl(String url) {
	    Window.Location.assign(url);
	}
}
