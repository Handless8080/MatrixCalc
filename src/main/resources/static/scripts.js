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

function createInput(t, i, j) {
    var input = document.createElement('input');
    input.type = "text";
    input.id = "number" + t + i + j;
    input.style.width = "50px";
    return input;
}

function getSize(t, dimension) {
    var value;
    if (getOperator() == "+" || getOperator() == "-") {
        value = document.getElementById(dimension + '0').value;
    } else {
        value = document.getElementById(dimension + t).value;
    }
    return value;
}