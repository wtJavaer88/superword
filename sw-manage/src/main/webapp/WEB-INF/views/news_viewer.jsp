<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>  
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<title>FreshNews - ${title}</title>  
</head> 
<script type="text/javascript" src="/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="/js/layer.js"></script>
<script type="text/javascript" src="/js/superword.js"></script>
<script>
</script>
<body onload="init()">
	<div class="edit_box" style="display: none">
	    <div class="edit_title">
	        <input type="button" class="close" value="关闭">
	    	<input type="button" class="play" value="播音">
			<input type="button" class="detail" value="详细">
			<input type="button" class="test" value="测试">
	    </div>
	    <div class="rows" style="margin-left:10;padding-top: 25px;">
	        <label>单词:</label>
	        <input type="text" class="chat_area" id="question_area"></textarea>
	    </div>
	    <div class="rows" style="margin-left:10;padding-top: 25px;">
	        <div class="chat_area" id="content_area" name="answer"></div>
	    </div>
		<div class='player'>
			
		</div>
	</div>
	<div>
		<a class="edit_word" href="#" onclick="showTranslate();">翻译</a></div>
		<img src="${head_pic}"/>
	</div>
	<div id="maintext" class="content" style="font-size:24px">
 		${message}
	</div>
	<div id="maintext2" class="content" style="font-size:24px;display:none">
 		${message_chs}
	</div>

<style>
.content p{
    line-height: 25px;margin: 20px auto 0 auto;font-size: 20px;
}
</style>
</body>