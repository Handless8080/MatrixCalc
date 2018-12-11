$(document).ready(function() {

	$("#btn").click(function() {
		var csrf = $("#csrf").val(),
		    numbers = getNumbers(),
    	    operator = document.getElementById('operators').innerHTML,
    	    Url = getURL();

		$.ajax({
			type: 'POST',
			url: Url,
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify({
				'numbers': numbers,
				'operator': operator
			}),
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-Token', csrf)
			},
			success: function(result) {
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
                }
			}
		});
	});
});

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
    }
}

function showMatrix(result) {
    var out = "";
	for (var i = 0; i < result.length; i++) {
		out += "<tr>";
		for (var j = 0; j < result[i].length; j++) {
			out += "<td>" + result[i][j] + "</td>";
		}
		out += "</tr>";
	}
	$("#result").html(out);
}

function showMatrices(result) {
    for (var t = 0; t < result.length; t++) {
        var table = document.createElement('table');
        table.classList.add('table');
        table.classList.add('table-bordered');
        table.classList.add('table-sm');
        table.id = 'result' + t;
        document.getElementById('answer').appendChild(table);

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