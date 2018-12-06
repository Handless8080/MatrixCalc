$(document).ready(function() {

	$("#btn").click(function() {
		var csrf = $("#csrf").val();

		var numbers = getNumbers();
    	var operator = document.getElementById('operators').innerHTML;

		$.ajax({
			type: 'POST',
			url: '/answer',
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
				var out = "";
				for (var i = 0; i < result.length; i++) {
					out += "<tr>";
					for (var j = 0; j < result[i].length; j++) {
						out += "<td>" + result[i][j] + "</td>";
					}
					out += "</tr>";
				}
				$("#result").html(out);
				return false;
			}
		});
	});
});

function getNumbers() {
	var numbers = [];
	var col, row, flag = true;

    if ($('#operators').val() == "Сложение" || $('#operators').val() == "Вычитание") {
        col = $('#col0').val();
        row = $('#row0').val();
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