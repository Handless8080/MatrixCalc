document.addEventListener('DOMContentLoaded', function() {
    var url = window.location.href;

    var link1 = document.getElementById('0');

    if (url == link1.href) {
        return;
    }

    link1.classList.remove('btn-dark', 'text-light');
    link1.classList.add('btn-outline-dark');

    for (var i = 1; i < 6; i++) {
        var tempLink = document.getElementById(i);
        if (url == tempLink.href) {
            tempLink.classList.remove('btn-outline-dark');
            tempLink.classList.add('btn-dark', 'text-light');
            return;
        }
    }

    link1.classList.add('btn-dark', 'text-light');
    link1.classList.remove('btn-outline-dark');
});
