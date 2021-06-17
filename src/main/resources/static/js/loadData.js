var globalTopic = null;
var globalUsername = null;

function loadData() {
    var url_string = window.location.href;
	var url = new URL(url_string);
	globalUsername = url.searchParams.get("name");
	globalTopic = url.searchParams.get("topic");
}

