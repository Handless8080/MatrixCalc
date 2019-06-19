document.addEventListener('DOMContentLoaded', function() {
    var url = window.location.href;

    var link;

    if (url.includes('lalg')) {
        link = document.getElementById('1');
    } else if (url.includes('geom')) {
        link = document.getElementById('2');
    } else if (url.includes('dmath')) {
        link = document.getElementById('3');
    } else if (url.includes('theor')) {
        link = document.getElementById('4');
    } else if (url.includes('mathstat')) {
        link = document.getElementById('5');
    } else {
        link = document.getElementById('0');
    }

    for (var i = 0; i < 6; i++) {
        var tempLink = document.getElementById(i);
        tempLink.classList.add('btn-outline-dark');
        tempLink.classList.remove('btn-dark', 'text-light');
    }

    link.classList.add('btn-dark', 'text-light');
    link.classList.remove('btn-outline-dark');
});
