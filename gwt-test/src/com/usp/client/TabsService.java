package com.usp.client;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;

public class TabsService {
	public void sendTabUrls(String data,
			RequestCallback callback) throws RequestException {
		String url = "http://usp046.appspot.com/sendUrls";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
		builder.setHeader("Content-type", "application/x-www-form-urlencoded");
		builder.sendRequest(data, callback);
	}
	
	public void isReceivedNewTabs(RequestCallback callback)  {
		String url = "http://usp046.appspot.com/isReceivedNewTabs";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
		try {
			builder.sendRequest(null, callback);
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
