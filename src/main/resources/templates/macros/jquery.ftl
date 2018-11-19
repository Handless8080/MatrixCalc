<#macro jquery>
$(document).ready(function() {

	$("#btn").click(function() {
		var csrf = document.getElementById('csrf').value;

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

	var col = $('#cols').val();
    var row = $('#rows').val();
    var tables = $('#matr-count').val();

	for (var t = 0; t < tables; t++) {
		numbers[t] = [];
		for (var i = 0; i < col; i++) {
			numbers[t][i] = [];
			for (var j = 0; j < row; j++) {
				numbers[t][i][j] = $('#number' + t + i + j).val();
			}
		}
	}
	return numbers;
}
</#macro>