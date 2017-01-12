
	function init(){
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
	}
	
	function closeWindow(){
			$('.player').html('');
			$('.detail').unbind();
	   	    layer.closeAll();
	}
	function showTranslate(){
		if($('#maintext').is(':hidden')){
			$('#maintext').show();
			$('#maintext2').hide();
		}
		else{
			$('#maintext').hide();
			$('#maintext2').show();
		}
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