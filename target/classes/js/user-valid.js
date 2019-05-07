const minForName = 3;
const minForPassword = 5;
const regexp = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

function validNickname() {
    valid();
    validation('nickname-input', minForName);
}

function validUsername() {
    valid();
    validation('username-input', minForName);
}

function validPassword() {
    valid();
    validation('password-input', minForPassword);

    var passwordConfirm = document.getElementById('password2-input');

    if (passwordConfirm) {
        validPasswordConfirm();
    }
}

function validPasswordConfirm() {
    var password = document.getElementById('password-input'),
        passwordConfirm = document.getElementById('password2-input');

    if (password.value != passwordConfirm.value) {
        passwordConfirm.classList.add('is-invalid');
    } else {
        passwordConfirm.classList.remove('is-invalid');
    }

    valid();
}

function validEmail() {
    var email = document.getElementById('email-input');

    if (email.value == '' || regexp.test(email.value)) {
        email.classList.remove('is-invalid');
    } else {
        email.classList.add('is-invalid');
    }

    valid();
}

function valid() {
    var username = document.getElementById('username-input'),
        nickname = document.getElementById('nickname-input'),
        password = document.getElementById('password-input'),
        passwordConfirm = document.getElementById('password2-input'),
        email = document.getElementById('email-input'),
        button = document.getElementById('btn');

    if (!username) {
        username = {};
        username.value = "123";
    }

    if (!nickname) {
        nickname = {};
        nickname.value = "123";
    }

    if (!passwordConfirm) {
        passwordConfirm = {};
        passwordConfirm.value = password.value;
    }

    if (username.value.length < minForName || nickname.value.length < minForName || password.value.length < minForPassword ||
            password.value != passwordConfirm.value || (email && email.value != '' && !regexp.test(email.value))) {
        btn.disabled = true;
    } else {
        btn.disabled = false;
    }
}