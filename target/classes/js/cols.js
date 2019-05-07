function createColumns() {
    var id = this.id,
        index = id[parseInt(id.length, 10) - 1],
        colCount = document.getElementById('col' + index);

    var col = colCount.value,
        matrCount = document.getElementById('matr-count').value;

    if (col < 10) {
        colCount.value++;

        switch (getOperator()) {
            case "+":
            case "-":
                colCount.value--;
                for (var t = 0; t < matrCount; t++) {
                    document.getElementById('col' + t).value++;
                    createColumn(t);
                }
                break;
            case "*":
                if (parseInt(index, 10) + 1 != matrCount) {
                    var rowCount = document.getElementById('row' + (parseInt(index, 10) + 1));
                    rowCount.value++;
                    createRow(parseInt(index, 10) + 1);
                }
                createColumn(index);
                break;
            case "^":
            case "d":
            case "-1":
                createColumn(index);
                var rowCount = document.getElementById('row' + parseInt(index, 10));
                rowCount.value++;
                createRow(index);
                break;
            default:
                createColumn(index);
        }
    }
}

function deleteColumns() {
    var id = this.id,
        index = id[parseInt(id.length, 10) - 1],
        colCount = document.getElementById('col' + index);

    var col = colCount.value,
        matrCount = document.getElementById('matr-count').value;

    if (col > 1) {
        colCount.value--;

        switch (getOperator()) {
            case "+":
            case "-":
                colCount.value++;
                for (var t = 0; t < matrCount; t++) {
                    document.getElementById('col' + t).value--;
                    deleteColumn(t);
                }
                break;
            case "*":
                if (parseInt(index, 10) + 1 != matrCount) {
                    var rowCount = document.getElementById('row' + (parseInt(index, 10) + 1));
                    rowCount.value--;
                    deleteRow(parseInt(index, 10) + 1);
                }
                deleteColumn(index);
                break;
            case "^":
            case "d":
            case "-1":
                var rowCount = document.getElementById('row' + parseInt(index, 10));
                rowCount.value--;
                deleteColumn(index);
                deleteRow(index);
                break;
            default:
                deleteColumn(index);
        }
    }
}

function createColumn(t) {
    var row = document.getElementById('row' + t).value,
        col = document.getElementById('col' + t).value;

    for (var i = 0; i < row; i++) {
        var div = document.getElementById('inp' + t + i),
            input = createInput(t, i, parseInt(col, 10) - 1);

        div.appendChild(input);
    }
}

function deleteColumn(t) {
    var row = document.getElementById('row' + t).value,
        col = document.getElementById('col' + t).value;

    for (var i = 0; i < row; i++) {
        var input = document.getElementById('number' + t + i + parseInt(col, 10));
        input.remove();
    }
}