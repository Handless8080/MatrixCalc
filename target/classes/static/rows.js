function createRows() {
    var id = this.id,
        index = id[parseInt(id.length, 10) - 1],
        rowCount = document.getElementById('row' + index);

    var row = rowCount.value,
        matrCount = document.getElementById('matr-count').value;

    if (row < 10) {
        rowCount.value++;

        switch (getOperator()) {
            case "+":
            case "-":
                rowCount.value--;
                for (var t = 0; t < matrCount; t++) {
                    document.getElementById('row' + t).value++;
                    createRow(t);
                }
                break;
            case "*":
                if (index != 0) {
                    var columnCount = document.getElementById('col' + (parseInt(index, 10) - 1));
                    columnCount.value++;
                    createColumn(parseInt(index, 10) - 1);
                }
                createRow(index);
                break;
            default:
                createRow(index);
        }
    }
}

function deleteRows() {
    var id = this.id,
        index = id[parseInt(id.length, 10) - 1],
        rowCount = document.getElementById('row' + index);

    var matrCount = document.getElementById('matr-count').value;

    if (rowCount.value > 1) {
        rowCount.value--;

        switch (getOperator()) {
            case "+":
            case "-":
                rowCount.value++;
                for (var t = 0; t < matrCount; t++) {
                    document.getElementById('row' + t).value--;
                    deleteRow(t);
                }
                break;
            case "*":
                if (index != 0) {
                    var columnCount = document.getElementById('col' + (parseInt(index, 10) - 1));
                    columnCount.value--;
                    deleteColumn(parseInt(index, 10) - 1);
                }
                deleteRow(index);
                break;
            default:
                deleteRow(index);
        }
    }
}

function createRow(t) {
    var row = document.getElementById('row' + t).value,
        col = document.getElementById('col' + t).value;

    var d = document.getElementById('inp' + t),
        div = document.createElement('div');

    div.id = "inp" + t + (parseInt(row, 10) - 1);
    div.classList.add('d-inline-flex');
    div.classList.add('flex-row');
    d.appendChild(div);

    for (i = 0; i < col; i++) {
        var input = createInput(t, parseInt(row, 10) - 1, i);
        div.appendChild(input);
    }
}

function deleteRow(t) {
    var row = document.getElementById('row' + t).value,
        div = document.getElementById('inp' + t + parseInt(row, 10));

    div.remove();
}