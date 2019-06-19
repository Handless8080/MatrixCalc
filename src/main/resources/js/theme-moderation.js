var messageId;

var xhr = new XMLHttpRequest();
xhr.onreadystatechange = function() {
    if (xhr.readyState === XMLHttpRequest.DONE) {
        if (xhr.responseText === 'false') {
            alert('Запрос отклонен');
        } else if (xhr.responseText != 'true') {
            window.location.href = 'http://localhost:8080/login';
        } else {
            document.getElementById('div' + messageId).parentNode.removeChild(document.getElementById('div' + messageId));
        }
    }
}

document.addEventListener('DOMContentLoaded', function() {
    var btns = document.getElementsByName('message-delete-btn');

    for (var i = 0; i < btns.length; i++) {
        btns[i].addEventListener('click', deleteMessage)
    }
});

function deleteMessage() {
    messageId = this.id;
    var csrf = document.getElementById('csrf_').value;

    xhr.open('POST', '/delete-message/' + messageId, true);
    xhr.setRequestHeader('X-CSRF-Token', csrf);
    xhr.send();
}