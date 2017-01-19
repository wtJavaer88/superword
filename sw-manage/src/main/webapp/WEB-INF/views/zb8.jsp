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
	
<link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css"/>
<link rel="stylesheet" href="/css/superword.css" type="text/css"/>

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
<script src="/js/layer/layer.js"></script>
<script src="/js/superword.js"></script>
</head>
<body>
	<div class="form-horizontal row" style="padding:10px;margin-top:20px">
		<div class="col-md-3 col-sm-4 col-xs-6">
			<span>日期:</span>
			<input id="day" type="text" class="easyui-datebox" style="line-height:26px;border:1px solid #ccc">
		</div>
		<div class="col-md-3 col-sm-4 col-xs-6">
			<span>关键字:</span>
			<select name="keyword" id="keyword" multiple="multiple" style="width:200px"></select>
		</div>
		<div class="col-md-3 col-sm-4 col-xs-6">
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
					<th data-options="field:'day',width:100">日期</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="newsAdd" class="easyui-window" title="新增新闻"
		data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/news/add'"
		style="width: 400px; height: 300px; padding: 10px;">
		The window content.
	</div>
	<div id="hot"
		style="display: none">
	</div>
	<script type="text/javascript">
		$(function () {  
		    $("#articleList").datagrid({  
		        //双击事件  
		        onDblClickRow: function (index, row) {  
		        	console.log(getSelectedFirstFromUrl());
		        	if($('#is_translate').is(':checked'))
		        		read();
		        	else
		        		window.open(getSelectedFirstUrl()); 		        		
		        }  
		    });  
		    $('#hot').html('');
			$.ajax({
				dataType : 'json',
				type : 'get',
				url : '/rest/club/keywords',
				data : {
				},
				success : function(result) {
					console.log(result);
					$.each(result.rows, function(i, o) {
						console.log('o::'+o);
						$("#keyword").append("<option value='"+o+"'>"+o+"</option>");	
					});
					var multiselect = $("#keyword").multiselect(
							{
								noneSelectedText : "请选择",
								checkAllText : "全选",
								uncheckAllText : '全不选',
								selectedList : 3,
								minWidth : "100%"
							});
				},
				error : function() {
					alert('网络问题,失败,请检查日志!');
				}
			});
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
					if(sel == "true"){
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
		function read(){
        	window.open('/rest/zb8/view?id='+getSelectedFirstId());
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
				'-',
				{
					text : '热评',
					iconCls : 'icon-hot',
					handler : function() {
						hotCommentShow(getSelectedFirstId());
					}
				},
				'-',
				{
					text : '阅读',
					iconCls : 'icon-read',
					handler : function() {
						read(); 
					}
				},
				'-',
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
				'-'
				 ];
	</script>
</body>
</html>