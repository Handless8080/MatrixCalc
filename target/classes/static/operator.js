function setOperator() {
    var operator = this.innerHTML,
        select = document.getElementById('operators'),
        tables = document.getElementById('matr-count').value;

    select.innerHTML = operator;
    var char = getOperator();

    if (char == "+" || char == "-") {
        var rows = document.getElementById('row0').value,
            cols = document.getElementById('col0').value;

        document.getElementById('header').style.display = "none";
        document.getElementById('rows').style.display = "table-row";
        document.getElementById('params').style.display = "none";
        document.getElementById('header').style.display = "none";

        var maxCols = cols,
            maxRows = rows;
        for (var i = 1; i < tables; i++) {
            var col = document.getElementById('col' + i).value,
                row = document.getElementById('row' + i).value;

            if (maxCols < col) {
                maxCols = col;
            }
            if (maxRows < row) {
                maxRows = row;
            }
        }

        for (var i = 0; i < tables; i++) {
            changeMatricesSize(i, maxCols, maxRows);

            document.getElementById('col0').value = maxCols;
            document.getElementById('row0').value = maxRows;
        }
    } else {
        document.getElementById('header').style.display = "table-row";
        for (var i = 0; i < tables; i++) {
            document.getElementById('s-head' + i).style.display = "table-cell";
            document.getElementById('td-col' + i).style.display = "table-cell";
            document.getElementById('td-row' + i).style.display = "table-cell";
        }

        switch (char) {
            case "*":
                document.getElementById('rows').style.display = "table-row";
                document.getElementById('params').style.display = "none";
                document.getElementById('col-header').innerHTML = "Кол-во столбцов";

                var maxCols = document.getElementById('col0').value,
                    maxRows = document.getElementById('row0').value;

                for (var i = 1; i < tables; i++) {
                    var col = document.getElementById('col' + i).value,
                        row = document.getElementById('row' + i).value;
                    if (maxCols < col) {
                        maxCols = col;
                    }
                    if (maxRows < row) {
                        maxRows = row;
                    }
                }
                if (maxRows > maxCols) {
                    maxCols = maxRows;
                }

                for (var t = 0; t < tables; t++) {
                    changeMatricesSize(t, maxCols, maxCols);

                    document.getElementById('col' + t).value = document.getElementById('row' + t).value = maxCols;
                }
                break;
            case "^":
                document.getElementById('rows').style.display = "none";
                document.getElementById('params').style.display = "table-row";
                document.getElementById('col-header').innerHTML = "Размерность";

                var maxCols = document.getElementById('col0').value,
                    maxRows = document.getElementById('row0').value;

                for (var i = 1; i < tables; i++) {
                    var col = document.getElementById('col' + i).value,
                        row = document.getElementById('row' + i).value;
                    if (maxCols < col) {
                        maxCols = col;
                    }
                    if (maxRows < row) {
                        maxRows = row;
                    }
                }
                if (maxRows > maxCols) {
                    maxCols = maxRows;
                }

                for (var t = 0; t < tables; t++) {
                    changeMatricesSize(t, maxCols, maxCols);

                    document.getElementById('td-param' + t).style.display = "table-cell";

                    document.getElementById('col' + t).value = document.getElementById('row' + t).value = maxCols;
                }
                break;
        }
    }

    for (var i = 0; i < tables - 1; i++) {
        document.getElementById('op' + i).innerHTML = ((getOperator() == "+" || getOperator() == "-" || getOperator() == "*") ? char : "");
    }
}

function getOperator() {
    switch(document.getElementById('operators').innerHTML) {
        case 'Сложение':
            return '+';
        case 'Вычитание':
            return '-';
        case 'Умножение':
            return '*';
        case 'Возведение в степень':
            return '^';
        case 'Найти определитель':
            return 'd';
        case 'Найти обратную':
            return '-1';
        case 'Транспонировать':
            return 't';
        case 'Найти ранг':
            return 'm';
    }
}

function changeMatricesSize(t, maxCols, maxRows) {
    var d = document.getElementById('inp' + t);
    var row = document.getElementById('row' + t).value;

    if (maxCols > row) {
        for (var i = row; i < maxRows; i++) {
            var div = document.createElement('div');
            div.classList.add('d-inline-flex');
            div.classList.add('flex-row');
            div.id = "inp" + t + i;
            d.appendChild(div);

            for (var j = 0; j < maxCols; j++) {
                var input = createInput(t, i, j);
                div.appendChild(input);
            }
        }
    } else {
        var cols = document.getElementById('col' + t).value;
        for (var l = 0; l < maxRows; l++) {

            for (var j = cols; j < maxCols; j++) {
                var div = document.getElementById('inp' + t + l)
                var input = createInput(t, l, j);
                div.appendChild(input);
            }
        }
    }
}