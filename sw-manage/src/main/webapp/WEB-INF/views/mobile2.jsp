<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<style>
th {
    border-bottom: 1px solid #d6d6d6;
}

tr:nth-child(even) {
    background: #e9e9e9;
}
</style>
<script>
var domain = "${pageContext.request.contextPath}";
$(document).on( "pageinit", function() {
    $("#add_and_refresh").one( "click",  function () {
    	$( "#myTable tbody" ).html("");
        
    	$.ajax({
			dataType : 'json',
			type : 'get',
			url : domain+'/rest/zb8',
			data : {
			},
			success : function(result) {
				var rows = "";
				$.each(result.rows, function(i, o) {
					rows += "<tr><td>"+o.id+"</td><td>"+o.title+"</td><td>Christina Berglund</td><td>Berguvsven 8</td><td>Lule</td><td>S-958 22</td><td>Sweden</td></tr>";
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
});
</script>
</head>
<body>

<div data-role="page" id="pageone">
  <div data-role="header">
    <h1>切换表格样式</h1>
  </div>
  
  <div data-role="main" class="ui-content">
    <input type="button" id="add_and_refresh" data-role="button" data-icon="refresh" data-theme="c" data-inline="true" value="Add rows & refresh"> 
    <table data-role="table" data-mode="columntoggle" class="ui-responsive ui-shadow" id="myTable">
      <thead>
        <tr>
          <th data-priority="6">CustomerID</th>
          <th>CustomerName</th>
          <th data-priority="1">ContactName</th>
          <th data-priority="2">Address</th>
          <th data-priority="3">City</th>
          <th data-priority="4">PostalCode</th>
          <th data-priority="5">Country</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>Alfreds Futterkiste</td>
          <td>Maria Anders</td>
          <td>Obere Str. 57</td>
          <td>Berlin</td>
          <td>12209</td>
          <td>Germany</td>
        </tr>
        <tr>
          <td>2</td>
          <td>Antonio Moreno Taquer</td>
          <td>Antonio Moreno</td>
          <td>Mataderos 2312</td>
          <td>Mico D.F.</td>
          <td>05023</td>
          <td>Mexico</td>
        </tr>
        <tr>
          <td>3</td>
          <td>Around the Horn</td>
          <td>Thomas Hardy</td>
          <td>120 Hanover Sq.</td>
          <td>London</td>
          <td>WA1 1DP</td>
          <td>UK</td>
        </tr>

      </tbody>
    </table>
  </div>

  <div data-role="footer">
    <h1>底部文本</h1>
  </div>
</div> 

</body>
</html>