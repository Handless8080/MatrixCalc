function setOperator() {
    var operator = this.innerHTML;
    var tables = document.getElementById('matr-count').value;
    var select = document.getElementById('operators');
    var header = document.getElementById('header');

    select.innerHTML = operator;
    var char = getOperator();

    while (header.firstChild) {
        header.removeChild(header.firstChild);
    }

    for (var i = 1; i < tables; i++) {
        var colSize = document.getElementById('ctd' + i);
        var rowSize = document.getElementById('rtd' + i);

        if (colSize && rowSize) {
            colSize.remove();
            rowSize.remove();
        }
    }

    switch (char) {
        case "+":
        case "-":
            while (header.firstChild) {
                header.removeChild(header.firstChild);
            }
            break;
        case "*":
            var th = document.createElement('th');
            th.scope = "col";
            th.innerHTML = "Номер матрицы";
            header.appendChild(th);
            var number1 = createMatrixHeader(1);
            header.appendChild(number1);
            for (var i = 1; i < tables; i++) {
                var number = createMatrixHeader(parseInt(i, 10) + 1);
                header.appendChild(number);
                createSizeInput('col', i);
                createSizeInput('row', i);
            }
            break;
        case "^":
            break;
        case "^":
            break;
        case "^":
            break;
        case "^":
            break;

    }
    if (char != "+" && char != "-") {

    } else {



    }

    for (var i = 0; i < tables - 1; i++) {
        var op = document.getElementById('op' + i);
        op.innerHTML = getOperator() == "+" || getOperator == "-" ? char : "";
    }
}

function getOperator() {
    var operator = document.getElementById('operators').innerHTML;
    var char;

    switch(operator) {
        case 'Сложение':
            char = '+';
            break;
        case 'Вычитание':
            char = '-';
            break;
        case 'Умножение':
            char = '*';
            break;
        case 'Возведение в степень':
            char = '^';
            break;
        case 'Найти определитель':
            char = 'd';
            break;
        case 'Найти обратную':
            char = '-1';
            break;
        case 'Транспонировать':
            char = 't';
            break;
        case 'Найти ранг':
            char = 'm';
    }
    return char;
}