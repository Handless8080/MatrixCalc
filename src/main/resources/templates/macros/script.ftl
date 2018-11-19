<#macro script>
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
});

function setTablesCount() {
    var tables = document.getElementById('matrCount').value;
    setOperator();
    createInputTable(tables);
};

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
    select.innerHTML = operator;
    var char = getOperator();
    
    for (var i = 0; i < tables - 1; i++) {
        var op = document.getElementById('op' + i);
        op.innerHTML = char;
    }
};

function clearTables(param) {
    for (var i = 9; i >= param; i--) {
        var d = document.getElementById('inp' + i);
        while (d.firstChild) {
            d.removeChild(d.firstChild);
        }
    }
    for (var i = 8; i >=param - 1; i--) {
        var d = document.getElementById('op' + i)
        while (d.firstChild) {
            d.removeChild(d.firstChild);
        }
    }
};

function createInputTable(tables) {
    var col = document.getElementById('cols').value;
    var row = document.getElementById('rows').value;
    clearTables(tables);

    for (var t = 0; t < tables; t++){
        var d = document.getElementById('inp' + t);
        while (d.firstChild) {
            d.removeChild(d.firstChild);
        }
        for (var i = 0; i < col; i++) {
            var div = document.createElement('div');
            div.classList.add('d-inline-flex');
            div.classList.add('flex-row');
            d.appendChild(div);
            for (var j = 0; j < row; j++){
                var input = document.createElement('input');
                input.type = "text";
                input.id = "number" + t + i + j;
                input.style.width = "50px";
                div.appendChild(input);
            }
        }
    }
};

function createTable() {
    var col = document.getElementById('cols').value;
    var row = document.getElementById('rows').value;
    var matrCount = document.getElementById('matr-count').value;

    if (matrCount < 10) {
        document.getElementById('matr-count').value = parseInt(matrCount, 10) + 1;
        document.getElementById('op' + (parseInt(matrCount, 10) - 1)).innerHTML = getOperator();

        var d = document.getElementById('inp' + matrCount)
        for (var i = 0; i < col; i++) {
            var div = document.createElement('div');
            div.classList.add('d-inline-flex');
            div.classList.add('flex-row');
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
        document.getElementById('matr-count').value = parseInt(matrCount, 10) - 1;
        document.getElementById('op' + (parseInt(matrCount, 10) - 2)).innerHTML = "";

        var d = document.getElementById('inp' + (parseInt(matrCount, 10) - 1));
        while (d.firstChild) {
            d.removeChild(d.firstChild);
        }
    }
}
</#macro>