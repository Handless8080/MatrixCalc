const minForName = 5;
const minForText = 10;

document.addEventListener('DOMContentLoaded', function() {
    for (var i = 0; i < 6; i++) {
        document.getElementById(i).addEventListener('click', setCategory);
    }
});

function setCategory() {
    var category = this.innerHTML,
        select = document.getElementById('categories'),
        input = document.getElementById('category-value');

    select.innerHTML = input.value = category;
}

function listenerForName() {
    valid('name', minForName);
}

function listenerForText() {
    valid('text', minForText);
}

function valid(elementType, min) {
    validation(elementType, min)

    var button = document.getElementById('btn'),
        nameInput = document.getElementById('name'),
        textInput = document.getElementById('text');

    if (nameInput.value.length < minForName || textInput.value.length < minForText) {
        button.disabled = true;
    } else {
        button.disabled = false;
    }
}