var theme_xhr = new XMLHttpRequest();
theme_xhr.onreadystatechange = function() {
    if (theme_xhr.readyState === XMLHttpRequest.DONE) {
        if (theme_xhr.responseText === 'false') {
            alert('Тема не найдена, либо вы уже изменили ей рейтинг');
        } else if (theme_xhr.responseText != 'true') {
            window.location.href = 'http://localhost:8080/login';
        }
    }
}

var message_xhr = new XMLHttpRequest();
message_xhr.onreadystatechange = function() {
    if (message_xhr.readyState === XMLHttpRequest.DONE) {
        if (message_xhr.responseText === 'false') {
            alert('Сообщение не найдено, либо вы уже изменили ему рейтинг');
        } else if (message_xhr.responseText != 'true') {
            window.location.href = 'http://localhost:8080/login';
        }
    }
}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('theme-rate-sub').addEventListener('click', changeThemeRate);
    document.getElementById('theme-rate-add').addEventListener('click', changeThemeRate);

    var add = document.getElementsByName('message-rate-add');
    var sub = document.getElementsByName('message-rate-sub');

    for (var i = 0; i < add.length && i < sub.length; i++) {
        add[i].addEventListener('click', changeMessageRate);
        sub[i].addEventListener('click', changeMessageRate);
    }
});

function changeThemeRate() {
    var themeId = document.getElementById('theme-id').value;
    var operator = this.innerHTML;
    var csrf = document.getElementById('csrf_').value;

    var bool = true;
    if (operator == '-') {
        bool = false;
    }

    theme_xhr.open('POST', '/change-theme-rate/' + themeId + '?add=' + bool, true);
    theme_xhr.setRequestHeader('X-CSRF-Token', csrf);
    theme_xhr.send();

    var themeRate = document.getElementById('theme_rate');
    if (operator == '-') {
        themeRate.value = parseInt(themeRate.value, 10) - 1;
    } else {
        themeRate.value = parseInt(themeRate.value, 10) + 1;
    }
}

function changeMessageRate() {
    var operator = this.innerHTML;
    var messageId = this.id.replace(new RegExp('add', 'g'), '');
    messageId = messageId.replace(new RegExp('sub', 'g'), '');
    var csrf = document.getElementById('csrf_').value;

    var bool = true;
    if (operator == '-') {
        bool = false;
    }

    message_xhr.open('POST', '/change-message-rate/' + messageId + '?add=' + bool, true);
    message_xhr.setRequestHeader('X-CSRF-Token', csrf);
    message_xhr.send();

    var messageRate = document.getElementById('message_rate' + messageId);
    if (operator == '-') {
        messageRate.value = parseInt(messageRate.value, 10) - 1;
    } else {
        messageRate.value = parseInt(messageRate.value, 10) + 1;
    }
}