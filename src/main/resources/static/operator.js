function setOperator() {
    var operator = this.innerHTML,
        select = document.getElementById('operators'),
        header = document.getElementById('header'),
        tables = document.getElementById('matr-count').value;

    select.innerHTML = operator;
    var char = getOperator();

    while (header.firstChild) {
        header.removeChild(header.firstChild);
    }

    for (var i = 1; i < tables; i++) {
        var colSize = document.getElementById('ctd' + i),
            rowSize = document.getElementById('rtd' + i);

        if (colSize && rowSize) {
            colSize.remove();
            rowSize.remove();
        }
    }

    if (char == "+" || char == "-") {
        var rows = document.getElementById('row0').value,
            cols = document.getElementById('col0').value;

        document.getElementById('rows').style.display = "table-row";
        document.getElementById('params').style.display = "none";
        document.getElementById('col-header').innerHTML = "Кол-во строк";

        while (header.firstChild) {
            header.removeChild(header.firstChild);
        }

        for (var i = 1; i < tables; i++) {
            var inp = document.getElementById('inp' + i);
            while (inp.lastChild) {
                inp.removeChild(inp.lastChild);
            }

            var center = document.createElement('center');
            center.innerHTML = parseInt(i, 10) + 1;
            center.classList.add("mb-2");
            inp.appendChild(center);

            for (var l = 0; l < rows; l++) {
                var div = document.createElement('div');
                div.classList.add('d-inline-flex');
                div.classList.add('flex-row');
                div.id = "inp" + i + l;
                inp.appendChild(div);

                for (var j = 0; j < cols; j++) {
                    var input = createInput(i, l, j);
                    div.appendChild(input);
                }
            }
        }
    } else {
        createHeader();
        document.getElementById('col-header').innerHTML = "Кол-во столбцов";
        switch (char) {
            case "*":
                document.getElementById('rows').style.display = "table-row";
                document.getElementById('params').style.display = "none";
                document.getElementById('col-header').innerHTML = "Кол-во столбцов";

                var cols = document.getElementById('col0').value;
                for (var t = 1; t < tables; t++) {
                    var d = document.getElementById('inp' + t);
                    document.getElementById('col' + t).value = document.getElementById('row' + t).value = cols;

                    while (d.lastChild) {
                        d.removeChild(d.lastChild);
                    }

                    var center = document.createElement('center');
                    center.innerHTML = parseInt(t, 10) + 1;
                    center.classList.add("mb-2");
                    d.appendChild(center);

                    for (var i = 0; i < cols; i++) {
                        var div = document.createElement('div');
                        div.classList.add('d-inline-flex');
                        div.classList.add('flex-row');
                        div.id = "inp" + t + i;
                        d.appendChild(div);

                        for (var j = 0; j < cols; j++) {
                            var input = createInput(t, i, j);
                            div.appendChild(input);
                        }
                    }
                }
                break;
            case "^":
                var params = document.getElementById('params');
                while (params.firstChild) {
                    params.removeChild(params.firstChild);
                }

                var th = document.createElement('th');
                th.scope = "col";
                th.innerHTML = "Степень";
                document.getElementById('rows').style.display = "none";

                params.style.display = "table-row";
                params.appendChild(th);

                document.getElementById('col-header').innerHTML = "Размерность";
                for (var i = 0; i < tables; i++) {
                    var cols = document.getElementById('col' + i),
                        rows = document.getElementById('row' + i),
                        max = cols;

                    if (cols < rows) {
                        var max = rows;
                    }


                    createSizeInput('param', i);
                }
                break;
        }
    }

    for (var i = 0; i < tables - 1; i++) {
        document.getElementById('op' + i).innerHTML = ((getOperator() == "+" || getOperator() == "-" || getOperator() == "*") ? char : "");
    }
}

function getOperator() {
    var char;

    switch(document.getElementById('operators').innerHTML) {
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

function createHeader() {
    var tables = document.getElementById('matr-count').value;

    var th = document.createElement('th');
    th.scope = "col";

    var div = document.createElement('div');
    div.innerHTML = "Номер матрицы";
    div.style.minWidth = "140px";

    th.appendChild(div);
    header.appendChild(th);

    var number1 = createMatrixHeader(1);
    header.appendChild(number1);

    for (var i = 1; i < tables; i++) {
        var number = createMatrixHeader(parseInt(i, 10) + 1);
        header.appendChild(number);
        createSizeInput('col', i);
        createSizeInput('row', i);
    }
}