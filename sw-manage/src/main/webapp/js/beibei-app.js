	$(document).on( "pageshow", function() {
		console.log('in');
		var flag = 0;
		if(window.location.href.indexOf('page-s') >= 0){
			flag = 1;
		}
		$( "#myTable tbody" ).html("");
		$( "#myTable tbody" ).closest( "table#myTable" ).table( "refresh" ).trigger( "create" );
		$.ajax({
			dataType : 'json',
			type : 'get',
			url : window.location.origin+'/rest/zb8?1='+Math.random(),
			data : {
			},
			success : function(result) {
				var rows = "";
				$.each(result.rows, function(i, o) {
					if(flag==1)
					rows += "<tr><td>"+o.id+"</td><td>"+o.title+"</td><td>Christina Berglund</td><td>Berguvsven 8</td><td>Lule</td><td>S-958 22</td><td>Sweden</td></tr>";
					else
					rows += "<tr><td>"+i+"</td><td>"+o.title+"</td><td>Christina Berglund</td><td>Berguvsven 8</td><td>Lule</td><td>S-958 22</td><td>Sweden</td></tr>";
				});
				$( "#myTable tbody" )
	            .append( rows )
	            // Call the refresh method
	            .closest( "table#myTable" )
	            .table( "refresh" )
	            // Trigger if the new injected markup contain links or buttons that need to be enhanced
	            .trigger( "create" );	
			},
			error : function() {
				alert('网络问题,失败,请检查日志!');
			}
		});
	});
function toFrontPage(){
	window.location.href = "/rest/mobile-page/index";
	alert('1111');
}


