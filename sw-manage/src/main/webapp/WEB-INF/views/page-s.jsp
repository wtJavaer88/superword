<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.0/jquery.mobile-1.4.0.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jqm-icon-pack-fa.css">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.0/jquery.mobile-1.4.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/beibei-app.js"></script>
</head>
<body>

<div data-role="page" id="pageone">
  <div data-role="header">
    <h1>欢迎来到我的主页</h1>
    <div data-role="navbar">
      <ul>
        <li><a href="/rest/mobile-page/index" data-icon="apple">推荐</a></li>
        <li><a href="/rest/mobile-page/page-s" class="ui-btn-active ui-state-persist" data-icon="search">搜索</a></li>
      </ul>
    </div>
  </div>

  <div data-role="main" class="ui-content">
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
      </tbody>
    </table>
  </div>

  <div data-role="footer">
    <h1>我的页脚</h1>
	<div data-role="navbar">
      <ul>
        <li><a href="/rest/mobile-page/index" data-icon="home">首页</a></li>
        <li><a onclick="toFrontPage()" data-icon="home">关于</a></li>
      </ul>
    </div>
  </div> 
</div> 

</body>
</html>