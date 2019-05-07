function validation(elementType, min) {
    var element = document.getElementById(elementType);

    if (element.value.length >= min) {
        element.classList.remove('is-invalid');
    } else {
        element.classList.add('is-invalid');
    }
}