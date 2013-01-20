urls = new Array();
tab_titles = new Array();

chrome.tabs.getAllInWindow(null, function(tabs){
	urls[0]="byee.com";
    for (var i = 0; i < tabs.length; i++) {
    urls[i]=tabs[i].url;
    tab_titles[i]=tabs[i].title;
    }
});

function openUrl(url) {
	chrome.tabs.create({'url': url});
}

function showNotification(url) {
	var notification = webkitNotifications.createHTMLNotification("http://usp046.appspot.com/getUnNotifiedTabs");
	notification.show();
	}
	

function getUrls(tabs) {
	var i = 0;
	tabs.forEach(function(tab) {
		urls[i] = tab.url;
		i = i + 1;
	});
}