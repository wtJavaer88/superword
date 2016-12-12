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
<script>

	$(document).keyup(function(event){

	 switch(event.keyCode) {
		case 27:
		case 96:
	        closeWindow();
		 }
	});

	$(document).ready(function () {
		document.getElementById("maintext").ondblclick=function(e){
			var  word=window.getSelection().toString().replace(' ','').toLowerCase();	
			console.log('word:'+word);
			var url = 'http://www.iciba.com/index.php';
			$.ajax({
	            type:"POST",
	            url:"/rest/dict/mean",
	            data:{word:word},
	            datatype: "json",
	            beforeSend:function(){
	            },
	            //成功返回之后调用的函数             
	            success:function(data){
	            	console.log(data);
	            	console.log(data.cn_mean);
					showDetail(word,data.cn_mean);

	            },
	            //调用执行后调用的函数
	            complete: function(XMLHttpRequest, textStatus){
	            },
	            //调用出错执行的函数
	            error: function(){
	            }         
	         }); 
		}
	});
	function closeWindow(){
			$('.player').html('');
			$('.detail').unbind();
	   	    layer.closeAll();
	}

	function showDetail(word,mean){
		console.log('word:'+word);
		//询问框
		layer.open(
            {
                type: 1,
                title: false,
                closeBtn: 0,
				area: '400px',
				offset: '100px',
                shadeClose: true,
                content:$('.edit_box'),
                success: function(layero, index){
                    layero.find('#question_area').val(word);
                    layero.find('#content_area').html(mean);

					$('.close').click(function(){
						closeWindow();
					});
					$('.play').click(function(){
						$.ajax({
				            type:"POST",
				            url:"/rest/word/mp3",
				            data:{word:word},
				            datatype: "text",//"xml", "html", "script", "json", "jsonp", "text".
				            beforeSend:function(){
							
				            },
				            //成功返回之后调用的函数             
				            success:function(data){
				            	console.log(data);
				            	if(data.length > 0){
									$('.player').html('<embed src="/mp3/'+word+'.mp3" loop="false" autostart="false" width="0" height="0"></embed>');
				            	}
				            	else{
				            		$('.player').html('');
				            		layer.msg('找不到声音文件!', {icon: 2});
				            	}
				            },
				            //调用执行后调用的函数
				            complete: function(XMLHttpRequest, textStatus){
				            },
				            //调用出错执行的函数
				            error: function(){
				            }         
				         });
						//$('.player').html('<embed src="'+ph_am_mp3+'" loop="false" autostart="false" width="0" height="0"></embed>');
					});
					$('.detail').click(function(){
						window.open("http://www.iciba.com/"+word); 
					});
					$('.test').click(function(){
						
					});
                },
				cancel: function(index){ 
				  if(confirm('确定要关闭么')){
					layer.close(index)
				  }
				  return false; 
				},
				end: function(index){ 
				  console.log('close in bg...');
				  closeWindow();
				  return false; 
				}
            });
	}
</script>
<body>
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
		<img src="${head_pic}"/>
	</div>
	<div id="maintext" style="font-size:24px">
 		${message}
	</div>

 <a class="edit_word" href="#" onclick="showDetai('abacus');">编辑</a></div>
	
</body>