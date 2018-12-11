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

    for (var i = 0; i < 10; i++) {
        document.getElementById('btn-rows-count-less' + i).addEventListener('click', deleteRows);
        document.getElementById('btn-rows-count-more' + i).addEventListener('click', createRows);

        document.getElementById('btn-cols-count-less' + i).addEventListener('click', deleteColumns);
        document.getElementById('btn-cols-count-more' + i).addEventListener('click', createColumns);
    }
});

function createInput(t, i, j) {
    var input = document.createElement('input');
    input.type = "text";
    input.id = "number" + t + i + j;
    input.style.width = "50px";
    return input;
}