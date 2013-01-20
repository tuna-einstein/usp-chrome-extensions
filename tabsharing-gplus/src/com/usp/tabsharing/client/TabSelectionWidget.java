package com.usp.tabsharing.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TabSelectionWidget extends Composite {

	private static TabSelectionWidgetUiBinder uiBinder = GWT
			.create(TabSelectionWidgetUiBinder.class);

	interface TabSelectionWidgetUiBinder extends
	UiBinder<Widget, TabSelectionWidget> {
	}

	@UiField FlowPanel mainPanel;
	@UiField TextBox subjectText;
	@UiField Button submitButton;
	@UiField Button mailButton;
	@UiField Button copyToClipBoardButton;
	@UiField Label errorLabel;
	@UiField Image busyImage;
	@UiField Image thanksImage;
	@UiField CheckBox selectAllCheckbox;

	private HTML html;
	private FlexTable table = new FlexTable();
	private final TabsService service = new TabsService();
	private JsArrayString tabUrls;
	private JsArrayString tabTitles;

	public TabSelectionWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		mainPanel.add(table);
		busyImage.setVisible(true);
	}
	
	private String createMailToUrlFromBody(String body) {
		return "mailto:?Subject=" +  encodeURIComponent("Check these awesome urls:)") +
				"&body=" +  body;
	}

	private String createGplusShareUrlFromKey(String key) {
		UrlBuilder builder = new UrlBuilder();
		builder.setProtocol("http");
		builder.setHost("usp046.appspot.com");
		builder.setPath("getSharedUrls");
		builder.setParameter("key", key);
		return createGplusShareUrlFromUrl(builder.buildString());
	}

	private String createGplusShareUrlFromUrl(String url) {
		UrlBuilder builder = new UrlBuilder();
		builder.setProtocol("https");
		builder.setHost("plus.google.com");
		builder.setPath("share");
		builder.setParameter("url", url);
		return builder.buildString();
	}

	@UiHandler("copyToClipBoardButton")
	public void onClickCopyToClipBoard(ClickEvent e) {
		String temp ="";
		List<String> urls = getSelectedUrls();
		for (String url : urls) {
			temp = temp + url + "\n";
		}
		copyToClipBoard(temp);
	}

	@UiHandler("selectAllCheckbox")
	public void onSelectAllCheckbox(ClickEvent e) {
		int rowCount = table.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			CheckBox checkBox = (CheckBox)table.getWidget(i, 1);
			checkBox.setValue(selectAllCheckbox.getValue());
		}
	}

	private String getBodyForEmail() {
		List<String> urls = getSelectedUrls();
		String newLine = "%0D%0A";
		if (urls.isEmpty()) {
			errorLabel.setVisible(true);
			return null;
		}
		
		String body = "";
		for (String tab : urls) {
			body = body + encodeURIComponent(tab) + newLine;
		}
		return body;
	}
	
	private String getHeader() {
		errorLabel.setVisible(false);

		List<String> urls = getSelectedUrls();
		if (urls.isEmpty()) {
			errorLabel.setVisible(true);
			return null;
		}
		
		if (urls.size() == 1) {
			Window.open(createGplusShareUrlFromUrl(urls.get(0)), "", "");
			return null;
		}

		hideFormElements();
		busyImage.setVisible(true);

		List<String> titles = getSelectedTitles();
		String body = "subjectText=" + subjectText.getText() + "&";
		for (String tab : urls) {
			body = body + "url=" + encodeURIComponent(tab) + "&";
		}
		for (String title: titles) {
			body = body + "title=" + title + "&";
		}
		return body;
	}
	
	private RequestCallback getCallback() {
		return new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {
				if (200 != response.getStatusCode()) {
					busyImage.setVisible(false);
					showFormElements();
					Window.alert(response.getText());
					return;
				}

				String key = response.getText();
				busyImage.setVisible(false);
				thanksImage.setVisible(true);
				Window.open(createGplusShareUrlFromKey(key), "", "");
			}

			@Override
			public void onError(Request request, Throwable exception) {
				// TODO Auto-generated method stub
				busyImage.setVisible(false);
				showFormElements();
			}
		};
	}
	
	@UiHandler("mailButton")
	public void onClickMailButton(ClickEvent e) {
		String body = getBodyForEmail();
		if (body == null) {
			return;
		}
		Window.open(createMailToUrlFromBody(body), "", "");
	}

	@UiHandler("submitButton")
	public void onClickSubmit(ClickEvent e) {
		String body = getHeader();
		if (body == null) {
			return;
		}
		service.sendTabUrls(body, getCallback());
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

	public static native void copyToClipBoard(String str) /*-{
	copyToClipBoard(str);
	}-*/;

	public void setTabs(JsArrayString tabUrls, JsArrayString tabTitles,
			String current_tab_url) {
		this.tabUrls = tabUrls;
		this.tabTitles = tabTitles;
		bringTheCurrentUrlToTop(current_tab_url);
		table.clear();
		Label tabUrlsLabel = new Label("Tab Urls");
		tabUrlsLabel.setStylePrimaryName("textBold");

		table.setCellPadding(10);

		int length = tabUrls.length();
		table.setWidget(0, 0, getTab(tabTitles.get(0)));
		table.setWidget(0,  1, getCheckBox(true));
		for (int i = 1; i < length ; i ++) {
			table.setWidget(i, 0, getTab(tabTitles.get(i)));
			table.setWidget(i,  1, getCheckBox(false));
		}

		mainPanel.setVisible(true);
		busyImage.setVisible(false);

	}

	private void bringTheCurrentUrlToTop(String currentUrl) {
		for (int i = 0; i < tabUrls.length() ; i ++) {
			if(tabUrls.get(i).equalsIgnoreCase(currentUrl)) {
				String temp = tabUrls.get(i);
				tabUrls.set(i, tabUrls.get(0));
				tabUrls.set(0, temp);

				temp = tabTitles.get(i);
				tabTitles.set(i, tabTitles.get(0));
				tabTitles.set(0, temp);
				break;
			}
		}
	}

	private Widget getCheckBox(boolean isChecked) {
		CheckBox cb = new CheckBox();
		cb.setName("checkBox");
		cb.setValue(isChecked);
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

	private void hideFormElements() {
		mainPanel.setVisible(false);
		submitButton.setVisible(false);
	}

	private void showFormElements() {
		mainPanel.setVisible(true);
		submitButton.setVisible(true);
	}

}
