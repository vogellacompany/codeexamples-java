// Based on a work by Remy Sharp.
// Modified Aug 2009 by Oliver Lau <ola@heise.de>
// Modified Oct 2009 by Lars Vogel <lars.vogel@gmail.com>

var tweet = {
    target: 'tweet',
    hideReplies: true,
    callback: 'twitterCallback',
    username: 'vogella',
    page: 1,
    count: 15,
    maxCount: 200,
    loaderIcon: '<img src="./img/icons/loading2.gif" style="border:0" width="16" height="16" title="Please wait ...">',
    tries: 0,
    maxTries: 10
};

tweet.render = function(obj) {
    var target = document.getElementById(this.target);
    var pageSpan = document.createElement('p');
    if (typeof(obj) == 'undefined' || obj == null) {
		if (++this.tries < this.maxTries)
			return this.get();
        pageSpan.innerHTML = 'Error during read! <input type="button" onclick="tweet.getWithLoaderIcon()" value="Try again">';
        clearSubtree(target);
        target.appendChild(pageSpan);
        return;
    }
    pageSpan.className = 'twitterPage';
    pageSpan.style.height = '16px';
    pageSpan.style.width = '16px';
    pageSpan.id = 'pageSpan';
    pageSpan.innerHTML = 'Seite&nbsp;' + this.page;
    var ul = document.createElement('ul');
    var max = (obj.length > this.count)? this.count : obj.length;
    for (var i = 0; obj[i] && (i < max); ++i) {
        var o =  obj[i];
        if (this.hideReplies && o.text.substr(0, 1) == '@')
            continue;
        var li = document.createElement('li');
        var statusSpan = document.createElement('span');
        statusSpan.className = 'twitterStatus';
        statusSpan.innerHTML = linkify(o.text);
        var timeSpan = document.createElement('span');
        timeSpan.className = 'twitterTime';
        timeSpan.innerHTML = relative_time(o.created_at);
        li.appendChild(statusSpan);
        li.appendChild(document.createElement('br'));
        li.appendChild(timeSpan);
        ul.appendChild(li);
    }
    clearSubtree(target);
    target.appendChild(pageSpan);
    target.appendChild(ul);
    if (this.page > 1) {
        var prevButton = document.createElement('input');
        prevButton.type = 'button';
        prevButton.id = 'prevButton';
        prevButton.value = '<- Forward';
        prevButton.onclick = function() { tweet.prev(); };
        prevButton.className = 'more';
        target.appendChild(prevButton);
    }
    target.appendChild(document.createTextNode(' '));
    if ((obj.length >= this.count) && (this.count < this.maxCount)) {
        var nextButton = document.createElement('input');
        nextButton.type = 'button';
        nextButton.id = 'nextButton';
        nextButton.value = 'Back in Time->';
        nextButton.onclick = function() { tweet.next(); };
        nextButton.className = 'more';
        target.appendChild(nextButton);
    }
};

tweet.get = function () {
    var user = document.getElementById('twitterUser');
    if (user)
        user.innerHTML = "Recent tweets from " + this.username;
    window[this.callback] = function(obj) { tweet.render(obj); };
    // var url = 'http://twitter.com/statuses/user_timeline/' + this.username + '.json?callback=' + this.callback + '&page=' + this.page;
    var url = 'http://www.vogella.de/twitter/twitproxy2.php?page=' + this.page + '&x=' + Math.random();
    var script = document.createElement('script');
    script.setAttribute('src', url);
    document.getElementsByTagName('head')[0].appendChild(script);
};

tweet.getWithLoaderIcon = function() {
    var target = document.getElementById(this.target);
    clearSubtree(target);
    target.innerHTML = this.loaderIcon;
    this.get();
};

tweet.disablePrevNextButtons = function() {
    var prevButton = document.getElementById('prevButton');
    if (prevButton)
        prevButton.disabled = true;
    var nextButton = document.getElementById('nextButton');
    if (nextButton)
        nextButton.disabled = true;
    var pageSpan = document.getElementById('pageSpan');
    if (pageSpan)
        pageSpan.innerHTML = this.loaderIcon;
};

tweet.proceed = function() {
    this.disablePrevNextButtons();
    this.get();
    window.scrollTo(0, 0);
    return false;
};

tweet.prev = function() {
    --this.page;
    return this.proceed();
};

tweet.next = function() {
    ++this.page;
    return this.proceed();
};
