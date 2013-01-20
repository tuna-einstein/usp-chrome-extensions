package com.usp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TabCloudWidget extends Composite {

	private static TabCloudWidgetUiBinder uiBinder = GWT
			.create(TabCloudWidgetUiBinder.class);

	interface TabCloudWidgetUiBinder extends UiBinder<Widget, TabCloudWidget> {
	}

	@UiField FlowPanel mainPanel;
	@UiField TextBox receiverEmailId;
	@UiField TextBox subjectText;
	
	@UiField FormPanel formPanel;
	@UiField Button submitButton;
	@UiField FlowPanel responsePanel;
	@UiField Image busyImage;
	@UiField Image thanksImage;
	@UiField Anchor manageTabsAnchor;
	
	private FlexTable table = new FlexTable();
	private final TabsService service = new TabsService();
	private JsArrayString tabUrls;
	private JsArrayString tabTitles;

	public TabCloudWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setMethod(FormPanel.METHOD_POST);
		formPanel.setAction("www.google.com");
		formPanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				Window.alert(event.getResults());
			}
		});
		mainPanel.add(table);
		responsePanel.setVisible(false);
	}

	@UiHandler("submitButton")
	public void onClickSubmit(ClickEvent e) {
		formPanel.setVisible(false);
		busyImage.setVisible(true);
		List<String> urls = getSelectedUrls();
		List<String> titles = getSelectedTitles();
		
		String body = "receiverEmail=" + receiverEmailId.getText() + "&";
		body = body + "subjectText=" + subjectText.getText() + "&";
		for (String tab : urls) {
			body = body + "url=" + encodeURIComponent(tab) + "&";
		}
		
		for (String title: titles) {
			body = body + "title=" + title + "&";
		}
		
		RequestCallback callback = new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {
				if (200 != response.getStatusCode()) {
					return;
				}
				
				if (response.getText().indexOf("@") == -1) {
					Window.open(response.getText(), "", "");
				} else {
					busyImage.setVisible(false);
					thanksImage.setVisible(true);
				}
			}

			@Override
			public void onError(Request request, Throwable exception) {
				// TODO Auto-generated method stub
			}
		};
		
		try {
			service.sendTabUrls(body, callback);
		} catch (RequestException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public List<String> getSelectedUrls() {
		ArrayList<String> urls = new ArrayList<String>();
		int rowCount = table.getRowCount();
		for (int i = 0 ; i < rowCount; i++) {
			CheckBox checkBox = (CheckBox)table.getWidget(i, 1);
			if (checkBox.getValue()) {
				urls.add(tabUrls.get(i));
			}

		}
		return urls;
	}
	

	public List<String> getSelectedTitles() {
		ArrayList<String> urls = new ArrayList<String>();
		int rowCount = table.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			CheckBox checkBox = (CheckBox)table.getWidget(i, 1);
			TextBox textBox = (TextBox) table.getWidget(i, 0);
			if (checkBox.getValue()) {
				urls.add(textBox.getText());
			}

		}
		return urls;
	}
	
	
	public static native String encodeURIComponent(String msg) /*-{
	return encodeURIComponent(msg);
	}-*/;

	public void setTabs(JsArrayString tabUrls, JsArrayString tabTitles) {
		this.tabUrls = tabUrls;
		this.tabTitles = tabTitles;
		table.clear();
		Label tabUrlsLabel = new Label("Tab Urls");
		tabUrlsLabel.setStylePrimaryName("textBold");
	//	table.setWidget(0, 0, tabUrlsLabel);
//		final CheckBox all = new CheckBox();
//		all.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				if (all.getValue()) {
//					int rowCount = table.getRowCount();
//					for (int i = 1 ; i < rowCount; i++) {
//						((CheckBox)table.getWidget(i, 1)).setValue(true);
//					}
//				}
//			}
//		});
//
//		table.setWidget(0, 1, all);
		table.setCellPadding(10);

		int length = tabUrls.length();
		for (int i = 0; i < length ; i ++) {
			table.setWidget(i, 0, getTab(tabTitles.get(i)));
			table.setWidget(i,  1, getCheckBox());

		}

	}

	private Widget getCheckBox() {
		CheckBox cb = new CheckBox();
		cb.setName("checkBox");
		cb.setValue(true);
		return cb;
	}

	private Widget getTab(String tab) {
		TextBox box = new TextBox();
		box.setName("url");
		box.setText(tab);
		box.setVisibleLength(60);
		box.setStyleName("textBoxNoBorder");
		box.setStylePrimaryName("textBold");
		return box;
	}

	public static native JsArrayString fetchUrls() /*-{
	return $wnd.urls;
	}-*/;

	public static native void openUrl(String url) /*-{
	 $wnd.openUrl(url);
	}-*/;
	
	public static native String getPopupUrl() /*-{
	 return chrome.extension.getURL("Gwt_test.html");
	}-*/;
	
	@UiHandler("manageTabsAnchor")
	public void onClickManageTabsAnchor(ClickEvent e) {
		Window.open("http://usp046.appspot.com", "", "");
	}
}
