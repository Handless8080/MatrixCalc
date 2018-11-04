<#macro jquery>
$(document).ready(function() {

	$("#btnSubmit").click(function() {

		var message = "message";

		$.ajax({
			success: function() {
				$("#result").html(message);
			}
		});
	});
});
</#macro>