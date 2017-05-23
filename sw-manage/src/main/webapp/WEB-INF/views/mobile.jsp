<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Page Title</title>
		<meta charset="GBK" />
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0a4.1/jquery.mobile-1.0a4.1.min.css" />
		<script src="http://code.jquery.com/jquery-1.5.2.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.0a4.1/jquery.mobile-1.0a4.1.min.js"></script>
		<script>
			$(function() {
				$("#more").live("click", function() {
					var i = 0;
					for( i = 0; i <= 3; i++) {
						var list = $("<li><a href='http://blog.csdn.net/actual_'>新增列表项" + i + "</a></li>");
						$("#list").append(list).find("li:last").hide();
						$('ul').listview('refresh');
						$("#list").find("li:last").slideDown(3000);
					}
				});
			});

		</script>
	</head>
	<body>
		<div data-role="page">
			<div data-role="header">
				<h1>maria</h1>
			</div>
			<!-- /header -->
			<div data-role="content">
				<ul data-role="listview" id="list" data-inset="true">
					<li>
						<a href="#">maria</a>
					</li>
				</ul>
			</div>
			<!-- /content -->
			<div data-role="footer" class="ui-bar">
				<a href="#" data-role="button" id="more" >更多...</a>
			</div>
			<!-- /footer -->
		</div>
		<!-- /page -->
	</body>
</html>