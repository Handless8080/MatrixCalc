<#macro script>
function setTablesCount() {
    var tables = document.getElementById('matrCount').value;
    setOperator();
    createInputTable(tables);
};

function setOperator() {
    var operator = document.getElementById('operators').value;
    var tables = document.getElementById('matrCount').value;
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
    
    for (var i = 0; i < tables - 1; i++) {
        var op = document.getElementById('op' + i);
        op.innerHTML = "<span>" + char + "</span>";
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
</#macro>