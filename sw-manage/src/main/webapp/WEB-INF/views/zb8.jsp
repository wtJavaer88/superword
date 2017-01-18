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
<link rel="stylesheet" type="text/css"
	href="/css/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css"
	href="/css/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="/css/jquery.ui.dialog.min.css" />
	
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
<script type="text/javascript" src="/js/jquery-easyui-1.4/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery.ui.widget.js"></script>	
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>	
<script type="text/javascript" src="/js/jquery.ui.dialog.min.js"></script>	
<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.min.js"></script>	
<script type="text/javascript" src="/js/jquery.nicescroll.js"></script>	
<script type="text/javascript" src="/js/jquery-migrate-1.2.1.min.js"></script>	
<script type="text/javascript" src="/js/jquery.multiselect.js"></script>
<script type="text/javascript"
	src="/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>

</head>
<body>
	<div class="form-horizontal row" style="padding:10px;margin-top:20px">
		<div class="col-md-3">
			<span>日期:</span>
			<input id="day" type="text" class="easyui-datebox" style="line-height:26px;border:1px solid #ccc">
		</div>
		<div class="col-md-3">
			<span>关键字:</span>
			<select name="keyword" id="keyword" multiple="multiple" style="width:200px"></select>
		</div>
		<div class="col-md-3">
			<span>翻译:</span>
			<input id="is_translate" type="checkbox">
		</div>
			<a href="#" class="form-control" plain="true" onclick="doSearch()">Search</a>
	</div>
	<div data-options="region:'north'" style="height:100px">
		<table class="easyui-datagrid" id="articleList" title="直播吧新闻列表"
			data-options="singleSelect:true,collapsible:true,pagination:true,url:'/rest/zb8/',method:'get',pageSize:15,toolbar:toolbar,pageList:[10,15,20],fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',width:50">ID</th>
					<th data-options="field:'title',width:400">标题</th>
					<th data-options="field:'url',width:150" hidden="true">网址</th>
					<th data-options="field:'fromUrl',width:150">来源网址</th>
					<th data-options="field:'comments',width:150">总评论数</th>
					<th data-options="field:'hotComments',width:150">热评数</th>
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
		$("#keyword").append("<option value='阿森纳'>阿森纳</option>");	
		$("#keyword").append("<option value='曼城'>曼城</option>");
		$("#keyword").append("<option value='巴萨罗那'>巴萨罗那</option>");	
		$("#keyword").append("<option value='英超'>英超</option>");	
		
		var multiselect = $("#keyword").multiselect(
				{
					noneSelectedText : "请选择",
					checkAllText : "全选",
					uncheckAllText : '全不选',
					selectedList : 3,
					minWidth : "100%"
				});
		
		$('#day').datebox({
			onSelect: function(date){
				var m = date.getMonth()+1;
				if(m < 10){
					m = "0"+m;
				}
				var d = date.getDate();
				if(d < 10){
					d = "0"+d;
				}
				$('#day').val(date.getFullYear()+"-"+m+"-"+d);
			}
		});
		function doSearch(){
				var arr = $("input[name='multiselect_keyword']");
				console.log(arr.length);
				var kw = '';
				$.each(arr, function(i, o) {
					var sel=$(o).attr('aria-selected');
					console.log(sel);
					if(sel){
						console.log($(o).val());
						kw=kw+$(o).val()+","
					}
				});
				console.log('kw:  '+kw);
				$('#articleList').datagrid('load',{
					keyword: kw,
					day: $('#day').val(),
					is_translate:$('#is_translate').is(':checked')
				});
			
		}
		function formatDate(val, row) {
			var now = new Date(val);
			return now.format("yyyy-MM-dd hh:mm:ss");
		}
		function formatThumb(val, row) {
			return '<img src="'+val+'" />'
		}
		
		function getSelectionsIds() {
			var articleList = $("#articleList");
			var sels = articleList.datagrid("getSelections");
			var ids = [];
			for ( var i in sels) {
				ids.push(sels[i].id);
			}
			ids = ids.join(",");
			return ids;
		}
		function getSelectedFirstUrl() {
			var articleList = $("#articleList");
			var sels = articleList.datagrid("getSelections");
			var url = "";
			if (sels.length>0) {
				url=sels[0].url;
			}
			return url;
		}
		function getSelectedFirstFromUrl() {
			var articleList = $("#articleList");
			var sels = articleList.datagrid("getSelections");
			var url = "";
			if (sels.length>0) {
				url=sels[0].fromUrl;
			}
			return url;
		}
		function getSelectedFirstId() {
			var articleList = $("#articleList");
			var sels = articleList.datagrid("getSelections");
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
						window.open('/rest/zb8/view?id='+getSelectedFirstId()); 
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
																							"#articleList")
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
						var optins = $("#articleList").datagrid("getPager").data(
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