<#macro jquery>
$("#btn").click(function() {
	$.ajax({
		url: "/",
		cache: false,
		type: "POST",
		data: "",
		success: function(response) {
			var html = "";
			$.each(response.answer, function(i) {
				html = html + response.answer[i];
			});
			$('container').html(html);
		}
	});
});
</#macro>