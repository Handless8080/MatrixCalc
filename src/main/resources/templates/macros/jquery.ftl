<#macro jquery>
$(document).ready(function() {

	$("#btn").click(function() {
		var csrf = document.getElementById('csrf').value;

		var numbers = getNumbers();
		var cols = $('#cols').val();
    	var rows = $('#rows').val();
    	var operator = $('#operators').val();

		$.ajax({
			type: 'POST',
			url: '/answer',	
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify({
				'numbers': numbers,
				'cols': cols,
				'rows': rows,
				'operator': operator
			}),			
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-Token', csrf)
			},
			success: function(result) {
				$("#result").html(result);	
				return false;
			}
		});
	});
});

function getNumbers() {
	var numbers = [[[]]];

	var col = $('#cols').val();
    var row = $('#rows').val();
    var tables = $('#matrCount').val();

	for (var t = 0; t < tables; t++) {
		for (var i = 0; i < col; i++) {
			for (var j = 0; j <row; j++) {
				numbers[t][i][j] = $('#number' + t + i + j).val();
			}
		}
	}
	return numbers;
}
</#macro>