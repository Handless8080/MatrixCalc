function createTable() {
    var matrCount = document.getElementById('matr-count').value;
    if (getOperator() != "+" && getOperator() != "-") {
        var col = document.getElementById('col' + (parseInt(matrCount, 10) - 1)).value,
            row = 1;
    } else {
        var col = document.getElementById('col0').value,
            row = document.getElementById('row0').value;
    }

    if (matrCount < 10) {
        if (getOperator() != "+" && getOperator() != "-") {
            createSizeInput('col', matrCount);
            createSizeInput('row', matrCount);

            var number = createMatrixHeader(parseInt(matrCount, 10) + 1);
            header.appendChild(number);
        }
        if (getOperator() == "^") {
            createSizeInput('param', parseInt(matrCount, 10) + 1);
        }

        document.getElementById('matr-count').value++;
        var char = getOperator();
        document.getElementById('op' + (parseInt(matrCount, 10) - 1)).innerHTML = ((char == "+" || char == "-" || char == "*") ? char : "");

        var d = document.getElementById('inp' + matrCount)
        for (var i = 0; i < row; i++) {
            var div = document.createElement('div');
            div.classList.add('d-inline-flex');
            div.classList.add('flex-row');
            div.id = "inp" + matrCount + i;
            d.appendChild(div);

            for (var j = 0; j < col; j++) {
                var input = createInput(matrCount, i, j);
                div.appendChild(input);
            }
        }
        createMatrixNumber(parseInt(matrCount, 10) + 1);
    }
}

function deleteTable() {
    var matrCount = document.getElementById('matr-count').value,
        header = document.getElementById('header'),
        cols = document.getElementById('cols'),
        rows = document.getElementById('rows');

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

        if (getOperator() == "^") {
            var params = document.getElementById('params');
            params.removeChild(params.lastChild);
        }
    }
}

function createMatrixHeader(number) {
    var header = document.createElement('th'),
        center = document.createElement('center');

    center.innerHTML = number;
    header.scope = "col";
    header.appendChild(center);
    return header;
}

function createMatrixNumber(number) {
    var div = document.getElementById('inp' + (parseInt(number, 10) - 1)),
        center = document.createElement('center');

    center.innerHTML = number;
    center.classList.add('mb-2');
    div.insertBefore(center, div.firstChild);
}