<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>  
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<title>${title}</title>  
</head> 
<link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css"/>
<link rel="stylesheet" href="/css/superword.css" type="text/css"/>

<script type="text/javascript" src="/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="/js/layer.js"></script>
<script type="text/javascript" src="/js/superword.js"></script>
<script>
$(function() {
	var engContent = $('#maintext').text();
	var chsContent = $('#maintext2').text();
	var e_len = engContent.length;
	if(e_len < 10){
		$('#tranBtn').hide();
		$('#maintext').addClass('hide-div');
		$('#maintext2').removeClass('hide-div');
	}
});
</script>
<body onload="init()">
	<div id="hot" class="hide-div">
	</div>
	<div id="word_window" style="display: none">
		<div class="row myrow">
			<div class="col-md-4 col-sm-4 col-xs-6">
	        	<p class="hot-comment redtext"  id="question_area"></p>
			</div>
	        <div class="col-md-4 col-sm-4 col-xs-6">
	        	<input type="button" id="play" class="png-bg word-play">
	        	<input type="button" id="detail" class="png-bg word-detail">
	        </div>
	    </div>
	    <div>
	        <div id="content_area"></div>
	    </div>
		<div id='player' >
			
		</div>
	</div>
	<div class="right-menu">
		<a id="tranBtn"  href="#" onclick="showTranslate();">翻译</a>
		<a href="${from_url}" target="_blank">原文</a>
		<a href="${url}" target="_blank">直播吧</a>
		<a href="#" onclick="hotCommentShow(${id});">评论</a>
	</div>
	<div id="maintext" class="content">
 		${message}
	</div>
	<div id="maintext2" class="content hide-div">
 		${message_chs}
	</div>

<style>
.content p{
    line-height: 25px;margin: 20px auto 0 auto;font-size: 20px;
}
</style>
</body>