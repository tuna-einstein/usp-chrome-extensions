package com.usp.tabsharing.client;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;

public class TabsService {
	public void sendTabUrls(String data,
			RequestCallback callback) {
		String url = "http://usp046.appspot.com/generateKey";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
		builder.setHeader("Content-type", "application/x-www-form-urlencoded");
		try {
			builder.sendRequest(data, callback);
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
