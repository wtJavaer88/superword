<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻管理系统</title>
<link rel="stylesheet" type="text/css"
	href="/js/jquery-easyui-1.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="/js/jquery-easyui-1.4/themes/icon.css" />
<script type="text/javascript" src="/js/jquery-easyui-1.4/jquery.min.js"></script>
<script type="text/javascript"
	src="/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
	<div>
		<table class="easyui-datagrid" id="userList" title="直播吧新闻列表"
			data-options="singleSelect:false,collapsible:true,pagination:true,url:'/rest/zb8/',method:'get',pageSize:5,toolbar:toolbar,pageList:[2,5,10]">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'title',width:300">标题</th>
					<th style="display:none" data-options="field:'url',width:150">网址</th>
					<th data-options="field:'from_url',width:150">来源网址</th>
					<th data-options="field:'keyword',width:120">关键字</th>
					<th data-options="field:'thumbnail',width:200,formatter:formatThumb">缩略图</th>
					<th data-options="field:'day',width:100">日期</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="newsAdd" class="easyui-window" title="新增新闻"
		data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/news/add'"
		style="width: 400px; height: 300px; padding: 10px;">The window
		content.</div>
	<script type="text/javascript">
		function formatDate(val, row) {
			var now = new Date(val);
			return now.format("yyyy-MM-dd hh:mm:ss");
		}
		function formatThumb(val, row) {
			return '<img src="'+val+'" />'
		}
		
		function getSelectionsIds() {
			var userList = $("#userList");
			var sels = userList.datagrid("getSelections");
			var ids = [];
			for ( var i in sels) {
				ids.push(sels[i].id);
			}
			ids = ids.join(",");
			return ids;
		}
		function getSelectedFirstUrl() {
			var userList = $("#userList");
			var sels = userList.datagrid("getSelections");
			var url = "";
			if (sels.length>0) {
				url=sels[0].url;
			}
			return url;
		}
		function getSelectedFirstFromUrl() {
			var userList = $("#userList");
			var sels = userList.datagrid("getSelections");
			var url = "";
			if (sels.length>0) {
				url=sels[0].from_url;
			}
			return url;
		}
		function getSelectedFirstId() {
			var userList = $("#userList");
			var sels = userList.datagrid("getSelections");
			var id = 0;
			if (sels.length>0) {
				id=sels[0].id;
			}
			return id;
		}
		var toolbar = [
				{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						$('#newsAdd').window('open');
					}
				},
				{
					text : '直播吧',
					iconCls : 'icon-link-web',
					handler : function() {
						window.open(getSelectedFirstUrl()); 
					}
				},
				{
					text : '原文',
					iconCls : 'icon-link-web',
					handler : function() {
						window.open(getSelectedFirstFromUrl()); 
					}
				},
				{
					text : '阅读',
					iconCls : 'icon-edit',
					handler : function() {
						window.open('/rest/zb8/view?from_url='+getSelectedFirstFromUrl()); 
					}
				},
				{
					text : '删除',
					iconCls : 'icon-cancel',
					handler : function() {
						var ids = getSelectionsIds();
						if (ids.length == 0) {
							$.messager.alert('提示', '未选中新闻!');
							return;
						}
						$.messager
								.confirm(
										'确认',
										'确定删除ID为 ' + ids + ' 的新闻吗？',
										function(r) {
											if (r) {
												$
														.ajax({
															url : "/rest/news?ids="
																	+ ids,
															type : "DELETE",
															dataType : "json", // 返回的数据类型为json类型
															success : function(
																	data) {
																$.messager
																		.alert(
																				'提示',
																				'删除新闻成功!',
																				undefined,
																				function() {
																					$(
																							"#userList")
																							.datagrid(
																									"reload");
																				});
															},
															error : function(
																	data) {
																console.log(data);
																$.messager
																.alert(
																		'提示',
																		'未知错误!');
															}

														});
											}
										});
					}
				},
				'-',
				{
					text : '导出',
					iconCls : 'icon-remove',
					handler : function() {
						var optins = $("#userList").datagrid("getPager").data(
								"pagination").options;
						var page = optins.pageNumber;
						var rows = optins.pageSize;
						$("<form>")
								.attr({
									"action" : "/user/export/excel",
									"method" : "POST"
								})
								.append(
										"<input type='text' name='page' value='"+page+"'/>")
								.append(
										"<input type='text' name='rows' value='"+rows+"'/>")
								.submit();
					}
				} ];
	</script>
</body>
</html>