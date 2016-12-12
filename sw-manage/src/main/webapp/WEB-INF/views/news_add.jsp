<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<form id="content" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>新闻网页:</td>
	            <td><input id="url" class="easyui-textbox" type="text" name="news_url" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	function submitForm(){
		if(!$('#content').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		$.ajax({
			url : "/rest/news/getSingleNews?url="+ $("#url").val(),
			type : "GET",
			dataType : "json", // 返回的数据类型为json类型
			success : function(
					data) {
				console.log(data);
				if(data.status==200){
					$.messager
							.alert(
									'提示',
									'添加新闻成功!',
									undefined,
									function() {
										$('#newsAdd').window('close');
										$("#userList").datagrid("reload");
										clearForm();
									});
				}
				else{
					$.messager.alert('提示','添加新闻失败!');
				}
			},
			error : function(
					data) {
				$.messager.alert('提示','出现异常!');
			}

		});
	}
	function clearForm(){
		$('#content').form('reset');
	}
</script>
