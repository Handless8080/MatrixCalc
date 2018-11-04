<#macro jquery>
$(document).ready(function() {

	$("#btnSubmit").click(function() {

		$.ajax({
			url: '${pageContext.request.contextPath}/getAnswer',			
			type: 'POST',
			async: true,
			success: function(result) {
				if (result == "message") {
					$("result").html(result);	
				}
			}
		});
	});
});
</#macro>