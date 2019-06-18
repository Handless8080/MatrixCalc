var moder_xhr = new XMLHttpRequest();
moder_xhr.onreadystatechange = function() {
    if (moder_xhr.readyState === XMLHttpRequest.DONE) {
        if (moder_xhr.responseText === 'false') {
            alert('Запрос отклонен');
        } else if (moder_xhr.responseText != 'true') {
            window.location.href = 'http://localhost:8080/login';
        }
    }
}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('change-moder').addEventListener('click', changeModer);
});

function changeModer() {
    var csrf = document.getElementById('csrf_').value;
    var userId = document.getElementById('user_id').value;

    moder_xhr.open('POST', '/change-moder/' + userId, true);
    moder_xhr.setRequestHeader('X-CSRF-Token', csrf);
    moder_xhr.send();

    var btnText = this;

    if (btnText.innerHTML === 'Отобрать права модератора') {
        btnText.innerHTML = 'Дать права модератора';
    } else {
        btnText.innerHTML = 'Отобрать права модератора';
    }
}