function createTable() {
    var matrCount = document.getElementById('matr-count').value;

    if (matrCount < 10) {
        var col, row;
        switch (getOperator()) {
            case "+":
            case "-":
                col = document.getElementById('col0').value;
                row = document.getElementById('row0').value;
                break;
            case "*":
                col = row = document.getElementById('col' + (parseInt(matrCount, 10) - 1)).value;
                document.getElementById('s-head' + matrCount).style.display = "table-cell";
                document.getElementById('td-col' + matrCount).style.display = "table-cell";
                document.getElementById('td-row' + matrCount).style.display = "table-cell";
                break;
            default:
                col = row = 1;
                document.getElementById('s-head' + matrCount).style.display = "table-cell";
                document.getElementById('td-col' + matrCount).style.display = "table-cell";
                document.getElementById('td-row' + matrCount).style.display = "table-cell";
        }

        document.getElementById('matr-count').value++;
        var char = getOperator();
        document.getElementById('op' + (parseInt(matrCount, 10) - 1)).innerHTML = ((char == "+" || char == "-" || char == "*") ? char : "");

        var d = document.getElementById('inp' + matrCount);
        d.style.visibility = "visible";
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

        document.getElementById('col' + matrCount).value = col;
        document.getElementById('row' + matrCount).value = row;
    }
}

function deleteTable() {
    var matrCount = document.getElementById('matr-count').value,
        cols = document.getElementById('cols'),
        rows = document.getElementById('rows');

    if (matrCount > 1) {
        document.getElementById('matr-count').value--;
        document.getElementById('s-head' + (parseInt(matrCount, 10) - 1)).style.display = "none";
        document.getElementById('td-col' + (parseInt(matrCount, 10) - 1)).style.display = "none";
        document.getElementById('td-row' + (parseInt(matrCount, 10) - 1)).style.display = "none";
        document.getElementById('td-param' + (parseInt(matrCount, 10) - 1)).style.display = "none";

        var id = parseInt(matrCount, 10) - 1;

        document.getElementById('op' + (parseInt(id, 10) - 1)).innerHTML = "";

        var d = document.getElementById('inp' + id);
        while (d.firstChild) {
            d.removeChild(d.firstChild);
        }

        var center = document.createElement('center');
        center.classList.add('mb-2');
        center.innerHTML = String.fromCharCode(parseInt(CHAR_CODE, 10) + parseInt(matrCount) - 1);
        d.appendChild(center);

        d.style.visibility = "hidden";

        if (getOperator() == "^") {
            document.getElementById('param' + id).style.display = "none";
        }
    }
}