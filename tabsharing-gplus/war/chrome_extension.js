urls = new Array();
tab_titles = new Array();
var current_tab_url;

chrome.tabs.getSelected(null, function (tab) {
    current_tab_url = tab.url;
});

chrome.tabs.getAllInWindow(null, function(tabs){
	urls[0]="byee.com";
    for (var i = 0; i < tabs.length; i++) {
    urls[i]=tabs[i].url;
    tab_titles[i]=tabs[i].title;
    }
});

function copyToClipBoard(str) {
	
	var backgroundPageWindow = chrome.extension.getBackgroundPage();
	
	var tempNode = backgroundPageWindow.document.getElementById("temp");

	tempNode.value = str;
	
	tempNode.select();
	backgroundPageWindow.document.execCommand("copy", false, null);
}
