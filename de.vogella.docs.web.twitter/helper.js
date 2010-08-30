function clearSubtree(node) {
    while (node.firstChild)
        node.removeChild(node.firstChild);
}

function relative_time(time_value) {
    var values = time_value.split(' ');
    var parsed_date = Date.parse(values[1] + ' ' + values[2] + ', ' + values[5] + ' ' + values[3]);
    var date = new Date(parsed_date);
    var relative_to = (arguments.length > 1) ? arguments[1] : new Date();
    var delta = parseInt((relative_to.getTime() - parsed_date) / 1000);
    var r = '';

    function formatTime(date) {
        var hour = date.getHours();
        var min = date.getMinutes() + '';
        if (min.length == 1)
            min = '0' + min;
        return hour + ':' + min + '&nbsp;Time';
    }

    function formatDate(date) {
        var day = date.getDate(),
            mon = date.getMonth()+1,
            thisyear = (new Date()).getFullYear(),
            year = date.getFullYear();
        return day + '.' + mon + '.' + ((thisyear != year)? year : '');
    }

    delta += 60*relative_to.getTimezoneOffset();
    if (delta < 5) {
        r = 'less then 5 seconds ago';
    } else if (delta < 30) {
        r = 'half a minute ago';
    } else if (delta < 60) {
        r = 'less then a minute ago';
    } else if (delta < 120) {
        r = 'around a minute ago';
    } else if (delta < (45*60)) {
        r = 'before ' + (parseInt(delta/60)).toString() + ' minutes';
    } else if (delta < (2*90*60)) {
        r = 'around an hour ago';
    } else if (delta < (24*60*60)) {
        r = 'before approx. ' + (parseInt(delta/3600)).toString() + ' hours';
    } else {
        var sOffset = -60*relative_to.getTimezoneOffset();
        var tzDate = new Date(date.getTime() + 1000*sOffset);
        r = 'at ' + formatDate(tzDate) + ' around ' + formatTime(tzDate);
    }
    return r;
}


function linkify(s) {
    var entities = {
        '"' : '&quot;',
        '&' : '&amp;',
        '<' : '&lt;',
        '>' : '&gt;'
    };
    return s.replace(/(http:\/\/twit(goo|pic).com\/)([A-Za-z0-9]+) - /g, function(d, m1, m2, m3) {
        return '<p><a target="_blank" href="' + m1 + m3 + '"><img src="' + m1 + 'show/mini/' + m3 + '"></a></p>';
    }).replace(/((href|src)=")?([A-Za-z]+:\/\/[A-Za-z0-9-_]+\.[A-Za-z0-9-_:%&\?\/.=]+)/g, function(m1, m2, m3, m4) {
        return (m2=='href="' || m2=='src="')? (m2 + m4) : '<a target="_blank" href="' + m4 + '">' + m4 + '</a>';
    }).replace(/(^|[^\w])(@[\d\w\-]+)/g, function(d, m1, m2) {
        return m1 + '@<a target="_blank" href="http://twitter.com/' + m2.substr(1) + '">' + m2.substr(1) + '</a>';
    }).replace(/"&<>/, function (m) {
        return entities[m];
    }).replace(/(^|[^\w])(#[\d\wäöüÄÖÜáéíóúàèìòùß]+)/g, function(d, m1, m2) {
        return m1 + '#<a target="_blank" href="http://hashtags.org/tag/' + m2.substr(1) + '/messages">' + m2.substr(1) + '</a>';
    }).replace(/(^|[^\w])(L:([^ ,\.]+))/g, function(d, m1, m2) {
        return m1 + '<a target="_blank" href="http://maps.google.com/maps?q=' + m2.substr(2) + '">' + m2.substr(2) + '</a>';
    });
}
