$(document).ready(function() {

	$("#btn").click(function() {
		var csrf = $("#csrf").val(),
		    numbers = getNumbers(),
    	    operator = document.getElementById('operators').innerHTML,
    	    params = getParams();
    	    Url = getURL();

		$.ajax({
			type: 'POST',
			url: Url,
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify({
				'numbers': numbers,
				'operator': operator,
				'params': params
			}),
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-Token', csrf)
			},
			success: function(result) {
			    var output = document.getElementById('answer');
			    while (output.firstChild) {
			        output.removeChild(output.firstChild);
			    }

			    switch (getOperator()) {
                    case "+":
                    case "-":
                    case "*":
                        showMatrix(result);
                        break;
                    case "^":
                    case "-1":
                    case "t":
                        showMatrices(result);
                        break;
                    case "d":
                        showValues(result, "det");
                        break;
                    case "m":
                        showValues(result, "rank");
                }
			}
		});
	});
});

function getParams() {
    var params = [],
        tables = $('#matr-count').val();

    for (var i = 0; i < tables; i++) {
        params[i] = $('#param' + i).val();
    }
    return params;
}

function getNumbers() {
	var numbers = [],
	    col = $('#col0').val(),
	    row = $('#row0').val(),
	    flag = true,
	    operator = document.getElementById('operators').innerHTML;

    if (operator == "Сложение" || operator == "Вычитание") {
        flag = false;
    }

    var tables = $('#matr-count').val();

	for (var t = 0; t < tables; t++) {
		numbers[t] = [];
		if (flag) {
		    col = $('#col' + t).val();
		    row = $('#row' + t).val();
		}
		for (var i = 0; i < row; i++) {
			numbers[t][i] = [];
			for (var j = 0; j < col; j++) {
				numbers[t][i][j] = $('#number' + t + i + j).val();
			}
		}
	}
	return numbers;
}

function getURL() {
    switch (getOperator()) {
        case "+":
        case "-":
        case "*":
            return "/sum-sub-mul";
        case "^":
        case "-1":
        case "t":
            return "/pow-rev-tran";
        case "d":
        case "m":
            return "/det-rank";
    }
}

function showMatrix(result) {
    createAnswerTable(0);

    var out = "";
	for (var i = 0; i < result.length; i++) {
		out += "<tr>";
		for (var j = 0; j < result[i].length; j++) {
			out += "<td>" + result[i][j] + "</td>";
		}
		out += "</tr>";
	}
	$("#result0").html(out);
}

function showMatrices(result) {
    for (var t = 0; t < result.length; t++) {
        if (result[t] == null) {
            var div = document.createElement('div');
            div.classList.add('d-inline-flex');
            div.classList.add('flex-column');
            div.classList.add('mr-3');

            var center = document.createElement('center');
            center.innerHTML = String.fromCharCode(parseInt(CHAR_CODE, 10) + parseInt(t, 10));

            var span = document.createElement('span');
            span.innerHTML = "Обратной матрицы не существует";

            div.appendChild(center);
            div.appendChild(span);
            document.getElementById('answer').appendChild(div);
            continue;
        }

        createAnswerTable(t);

        var out = "";
        for (var i = 0; i < result[t].length; i++) {
        	out += "<tr>";
        	for (var j = 0; j < result[t][i].length; j++) {
        		out += "<td>" + result[t][i][j] + "</td>";
        	}
        	out += "</tr>";
        }
        $("#result" + t).html(out);
    }
}

function showValues(result, valueType) {
    for (var t = 0; t < result.length; t++) {
        var div = document.createElement('div');
        div.classList.add('d-inline-flex');
        div.classList.add('flex-column');
        div.classList.add('mr-3');

        var span = document.createElement('span');
        span.innerHTML = valueType + "(" + String.fromCharCode(parseInt(CHAR_CODE, 10) + parseInt(t, 10)) + ") = " + result[t];

        div.appendChild(span);
        document.getElementById('answer').appendChild(div);
    }
}

function createAnswerTable(t) {
    var div = document.createElement('div');
    div.classList.add('d-inline-flex');
    div.classList.add('flex-column');
    div.classList.add('mr-3');

    var table = document.createElement('table');
    table.classList.add('table');
    table.classList.add('table-bordered');
    table.classList.add('table-sm');
    table.id = 'result' + t;

    if (getOperator() != '+' && getOperator() != '-' && getOperator() != '*') {
        var center = document.createElement('center');
        center.innerHTML = String.fromCharCode(parseInt(CHAR_CODE, 10) + parseInt(t, 10));

        div.appendChild(center);
    }

    div.appendChild(table);
    document.getElementById('answer').appendChild(div);
}