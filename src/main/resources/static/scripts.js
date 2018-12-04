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

    document.getElementById('btn-rows-count-more0').addEventListener('click', createRows);
    document.getElementById('btn-rows-count-less0').addEventListener('click', deleteRows);

    document.getElementById('btn-cols-count-more0').addEventListener('click', createColumns);
    document.getElementById('btn-cols-count-less0').addEventListener('click', deleteColumns);
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

function createSizeInput(id, i) {
    var tr = document.getElementById(id + 's');

    var select = document.createElement('td');
    select.id = id == "col" ? "ctd" + i : "rtd" + i;
    var div = document.createElement('div');
    div.classList.add('input-group');
    div.style.minWidth = "91px";

    var prependDiv = document.createElement('div');
    prependDiv.classList.add('input-group-prepend');

    var btnLess = document.createElement('button');
    btnLess.type = "button";
    btnLess.id = "btn-" + id + "s-count-less" + i;
    btnLess.classList.add('btn');
    btnLess.classList.add('btn-outline-success');
    btnLess.classList.add('btn-sm');
    btnLess.style.width = "31px";
    btnLess.innerHTML = "-";
    prependDiv.appendChild(btnLess);
    div.appendChild(prependDiv);

    var input = document.createElement('input');
    input.type = "text";
    input.id = id + i;
    input.setAttribute("readonly", "true");
    input.style.width = "31px";
    input.style.height = "31px";
    input.classList.add('form-control');
    input.classList.add('p-1');
    input.classList.add('pl-2');
    div.appendChild(input);

    var appendDiv = document.createElement('div');
    appendDiv.classList.add('input-group-append');

    var btnMore = document.createElement('button');
    btnMore.type = "button";
    btnMore.id = "btn-" + id + "s-count-more" + i;
    btnMore.classList.add('btn');
    btnMore.classList.add('btn-outline-success');
    btnMore.classList.add('btn-sm');
    btnMore.style.width = "31px";
    btnMore.innerHTML = "+";
    appendDiv.appendChild(btnMore);
    div.appendChild(appendDiv);

    select.appendChild(div);
    tr.appendChild(select);

    if (id == "col") {
        btnLess.addEventListener('click', deleteColumns);
        btnMore.addEventListener('click', createColumns);
    } else {
        btnLess.addEventListener('click', deleteRows);
        btnMore.addEventListener('click', createRows);
    }

    var col = document.getElementById('col' + (parseInt(i, 10) - 1)).value;
    input.value = col;
}

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

    if (char == "*") {
        var th = document.createElement('th');
        th.scope = "col";
        th.innerHTML = "Номер матрицы";
        header.appendChild(th);

        var number1 = document.createElement('th');
        number1.scope = "col";
        number1.innerHTML = "1";
        header.appendChild(number1);

        for (var i = 1; i < tables; i++) {
            var number = document.createElement('th');
            number.scope = "col";
            number.innerHTML = parseInt(i, 10) + 1;
            header.appendChild(number);

            createSizeInput('col', i);
            createSizeInput('row', i);
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
}

function createTable() {
    var matrCount = document.getElementById('matr-count').value;
    if (getOperator() == "*") {
        var col = document.getElementById('col' + (parseInt(matrCount, 10) - 1)).value;
        var row = 1;
    } else {
        var col = document.getElementById('col0').value;
        var row = document.getElementById('row0').value;
    }

    if (matrCount < 10) {
        if (getOperator() != "+" && getOperator() != "-") {
            createSizeInput('col', matrCount);
            createSizeInput('row', matrCount);

            var number = document.createElement('th');
            number.scope = "col";
            number.innerHTML = parseInt(matrCount, 10) + 1;
            header.appendChild(number);
        }
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
    var header = document.getElementById('header');
    var cols = document.getElementById('cols');
    var rows = document.getElementById('rows');

    if (matrCount > 1) {
        document.getElementById('matr-count').value--;
        document.getElementById('op' + (parseInt(matrCount, 10) - 2)).innerHTML = "";

        if (header.lastChild && cols.lastChild && rows.lastChild) {
            header.removeChild(header.lastChild);
            cols.removeChild(cols.lastChild);
            rows.removeChild(rows.lastChild);
        }
        var d = document.getElementById('inp' + (parseInt(matrCount, 10) - 1));
        while (d.firstChild) {
            d.removeChild(d.firstChild);
        }
    }
}

function createRows() {
    var id = this.id;
    var index = id[parseInt(id.length, 10) - 1];
    var rowCount = document.getElementById('row' + index);

    var row = rowCount.value;
    var matrCount = document.getElementById('matr-count').value;

    if (row < 10) {
        rowCount.value++;

        switch (getOperator()) {
            case "+":
            case "-":
                for (var t = 0; t < matrCount; t++) {
                    createRow(t);
                }
                break;
            case "*":
                if (index != 0) {
                    var columnCount = document.getElementById('col' + (parseInt(index, 10) - 1));
                    columnCount.value++;
                    createRow(index);
                    createColumn(parseInt(index, 10) - 1);
                }
                break;
            default:
                createRow(index);
        }
    }
}

function deleteRows() {
    var id = this.id;
    var index = id[parseInt(id.length, 10) - 1];
    var rowCount = document.getElementById('row' + index);

    var matrCount = document.getElementById('matr-count').value;

    if (rowCount.value > 1) {
        rowCount.value--;

        switch (getOperator()) {
            case "+":
            case "-":
                for (var t = 0; t < matrCount; t++) {
                    deleteRow(t);
                }
                break;
            case "*":
                if (index != 0) {
                    var columnCount = document.getElementById('col' + (parseInt(index, 10) - 1));
                    columnCount.value--;
                    deleteRow(index);
                    deleteColumn(parseInt(index, 10) - 1);
                }
                break;
            default:
                deleteRow(index);
        }
    }
}

function createColumns() {
    var id = this.id;
    var index = id[parseInt(id.length, 10) - 1];
    var colCount = document.getElementById('col' + index);

    var col = colCount.value;
    var matrCount = document.getElementById('matr-count').value;

    if (col < 10) {
        colCount.value++;

        switch (getOperator()) {
            case "+":
            case "-":
                for (var t = 0; t < matrCount; t++) {
                    createColumn(t);
                }
                break;
            case "*":
                if (parseInt(index, 10) + 1 != matrCount) {
                    var rowCount = document.getElementById('row' + (parseInt(index, 10) + 1));
                    rowCount.value++;
                    createColumn(index);
                    createRow(parseInt(index, 10) + 1);
                }
                break;
            default:
                createColumn(index);
        }
    }
}

function deleteColumns() {
    var id = this.id;
    var index = id[parseInt(id.length, 10) - 1];
    var colCount = document.getElementById('col' + index);

    var col = colCount.value;
    var matrCount = document.getElementById('matr-count').value;

    if (col > 1) {
        colCount.value--;

        switch (getOperator()) {
            case "+":
            case "-":
                for (var t = 0; t < matrCount; t++) {
                    deleteColumn(t);
                }
                break;
            case "*":
                if (parseInt(index, 10) + 1 != matrCount) {
                    var rowCount = document.getElementById('col' + (parseInt(index, 10) + 1));
                    rowCount.value--;
                    deleteColumn(index);
                    deleteRow(parseInt(index, 10) + 1);
                }
                break;
            default:
                deleteRow(index);
        }
    }
}

function createRow(t) {
    if (getOperator() == "+" || getOperator() == "-") {
        var col = document.getElementById('col0').value;
        var row = document.getElementById('row0').value;
    } else {
        var col = document.getElementById('col' + t).value;
        var row = document.getElementById('row' + t).value;
    }

    var d = document.getElementById('inp' + t)
    var div = document.createElement('div');
    div.id = "inp" + t + (parseInt(row, 10) - 1);
    div.classList.add('d-inline-flex');
    div.classList.add('flex-row');
    d.appendChild(div);

    for (i = 0; i < col; i++) {
        var input = document.createElement('input');
        input.type = "text";
        input.id = "number" + t + (parseInt(row, 10) - 1) + i;
        input.style.width = "50px";
        div.appendChild(input);
    }
}

function createColumn(t) {
    if (getOperator() == "+" || getOperator() == "-") {
        var col = document.getElementById('col0').value;
        var row = document.getElementById('row0').value;
    } else {
        var col = document.getElementById('col' + t).value;
        var row = document.getElementById('row' + t).value;
    }

    for (var i = 0; i < row; i++) {
        var div = document.getElementById('inp' + t + i);
        var input = document.createElement('input');
        input.type = "text";
        input.id = "number" + t + i + (parseInt(col, 10) - 1);
        input.style.width = "50px";
        div.appendChild(input);
    }
}

function deleteRow(t) {
    if (getOperator() == "+" || getOperator() == "-") {
        var row = document.getElementById('row0').value;
    } else {
        var row = document.getElementById('row' + t).value;
    }

    var div = document.getElementById('inp' + t + parseInt(row, 10));
    div.remove();
}

function deleteColumn(t) {
    if (getOperator() == "+" || getOperator() == "-") {
        var col = document.getElementById('col0').value;
        var row = document.getElementById('row0').value;
    } else {
        var col = document.getElementById('col' + t).value;
        var row = document.getElementById('row' + t).value;
    }

    for (var i = 0; i < row; i++) {
        var input = document.getElementById('number' + t + i + parseInt(col, 10));
        input.remove();
    }
}