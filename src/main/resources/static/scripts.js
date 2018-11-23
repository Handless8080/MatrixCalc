document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('1').addEventListener('click', setOperator);
    document.getElementById('2').addEventListener('click', setOperator);
    document.getElementById('3').addEventListener('click', setOperator);
    document.getElementById('4').addEventListener('click', setOperator);
    document.getElementById('5').addEventListener('click', setOperator);
    document.getElementById('6').addEventListener('click', setOperator);
    document.getElementById('7').addEventListener('click', setOperator);
    document.getElementById('8').addEventListener('click', setOperator);

    document.getElementById('btn-matr-count-more').addEventListener('click', createTable);
    document.getElementById('btn-matr-count-less').addEventListener('click', deleteTable);

    document.getElementById('btn-rows-count-more').addEventListener('click', createRow);
    document.getElementById('btn-rows-count-less').addEventListener('click', deleteRow);

    document.getElementById('btn-cols-count-more').addEventListener('click', createColumn);
    document.getElementById('btn-cols-count-less').addEventListener('click', deleteColumn);
});

function getOperator() {
    var operator = document.getElementById('operators').innerHTML;
    var char = '';

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
            break;
        case 'Найти определитель':
            break;
        case 'Найти обратную':
            break;
        case 'Транспонировать':
            break;
        case 'Найти ранг':
            break;
    }
    return char;
}

function setOperator() {
    var operator = this.innerHTML;
    var tables = document.getElementById('matr-count').value;
    var select = document.getElementById('operators');
    var header = document.getElementById('header');
    select.innerHTML = operator;
    var char = getOperator();

    if (char == "*") {
        var th = document.createElement('th');
        th.scope = "col";
        th.innerHTML = "Номер матрицы";
        header.appendChild(th);

        var cols = document
        for (var i = 0; i < tables; i++) {

        }
    } else {
        while (header.firstChild) {
            header.removeChild(header.firstChild);
        }
    }

    for (var i = 0; i < tables - 1; i++) {
        var op = document.getElementById('op' + i);
        op.innerHTML = char;
    }
};

function createTable() {
    var col = document.getElementById('col0').value;
    var row = document.getElementById('row0').value;
    var matrCount = document.getElementById('matr-count').value;

    if (matrCount < 10) {
        document.getElementById('matr-count').value++;
        document.getElementById('op' + (parseInt(matrCount, 10) - 1)).innerHTML = getOperator();

        var d = document.getElementById('inp' + matrCount)
        for (var i = 0; i < col; i++) {
            var div = document.createElement('div');
            div.classList.add('d-inline-flex');
            div.classList.add('flex-row');
            div.id = "inp" + matrCount + i;
            d.appendChild(div);

            for (var j = 0; j < row; j++) {
                var input = document.createElement('input');
                input.type = "text";
                input.id = "number" + matrCount + i + j;
                input.style.width = "50px";
                div.appendChild(input);
            }
        }
    }
}

function deleteTable() {
    var matrCount = document.getElementById('matr-count').value;

    if (matrCount > 1) {
        document.getElementById('matr-count').value--;
        document.getElementById('op' + (parseInt(matrCount, 10) - 2)).innerHTML = "";

        var d = document.getElementById('inp' + (parseInt(matrCount, 10) - 1));
        while (d.firstChild) {
            d.removeChild(d.firstChild);
        }
    }
}

function createRow() {
    var rowCount = document.getElementById('row0');

    var col = document.getElementById('col0').value;
    var row = rowCount.value;
    var matrCount = document.getElementById('matr-count').value;

    if (row < 10) {
        rowCount.value++;

        for (var t = 0; t < matrCount; t++) {
            var d = document.getElementById('inp' + t)
            var div = document.createElement('div');
            div.id = "inp" + t + row;
            div.classList.add('d-inline-flex');
            div.classList.add('flex-row');
            d.appendChild(div);

            for (i = 0; i < col; i++) {
                var input = document.createElement('input');
                input.type = "text";
                input.id = "number" + t + row + i;
                input.style.width = "50px";
                div.appendChild(input);
            }
        }
    }
}

function deleteRow() {
    var rowCount = document.getElementById('row0');

    var matrCount = document.getElementById('matr-count').value;

    if (rowCount.value > 1) {
        rowCount.value--;

        for (var t = 0; t < matrCount; t++) {
            var div = document.getElementById('inp' + t + rowCount.value);
            div.remove();
        }
    }
}

function createColumn() {
    var colCount = document.getElementById('col0');

    var col = colCount.value;
    var row = document.getElementById('row0').value;
    var matrCount = document.getElementById('matr-count').value;

    if (col < 10) {
        colCount.value++;

        for (var t = 0; t < matrCount; t++) {
            var d = document.getElementById('inp' + t)

            for (var i = 0; i < row; i++) {
                var div = document.getElementById('inp' + t + i);
                var input = document.createElement('input');
                input.type = "text";
                input.id = "number" + t + i + col;
                input.style.width = "50px";
                div.appendChild(input);
            }
        }
    }
}

function deleteColumn() {
    var colCount = document.getElementById('col0');

    var col = colCount.value;
    var row = document.getElementById('row0').value;
    var matrCount = document.getElementById('matr-count').value;

    if (col > 1) {
        colCount.value--;

        for (var t = 0; t < matrCount; t++) {
            var d = document.getElementById('inp' + t)

            for (var i = 0; i < row; i++) {
                var input = document.getElementById('number' + t + i + colCount.value);
                input.remove();
            }
        }
    }
}