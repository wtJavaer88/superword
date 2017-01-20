function init() {
	$(document).keyup(function(event) {

		switch (event.keyCode) {
		case 27:
		case 96:
			closeWindow();
			break;
		case 32:
			closeWindow();
			showTranslate();
			break;
		}
	});

	$(document).ready(
			function() {
				document.getElementById("maintext").ondblclick = function(e) {
					var word = window.getSelection().toString()
							.replace(' ', '').toLowerCase();
					console.log('word:' + word);
					$.ajax({
						type : "POST",
						url : "/rest/dict/mean",
						data : {
							word : word
						},
						datatype : "json",
						beforeSend : function() {
						},
						// 成功返回之后调用的函数
						success : function(data) {
							console.log(data);
							console.log(data.cn_mean);
							showDetail(word, data.cn_mean);

						},
						// 调用执行后调用的函数
						complete : function(XMLHttpRequest, textStatus) {
						},
						// 调用出错执行的函数
						error : function() {
						}
					});
				}
			});
}

function closeWindow() {
	$('#player').html('');
	$('.detail').unbind();
	layer.closeAll();
}
function showTranslate() {
	if ($('#maintext').is(':hidden')) {
		$('#maintext2').addClass('hide-div');
		$('#maintext').removeClass('hide-div');
	} else {
		$('#maintext').addClass('hide-div');
		$('#maintext2').removeClass('hide-div');
	}
}
function hotCommentShow(article_id) {
	layer.open({
		type : 1,
		area : '500px',
		offset : '200px',
		shadeClose : true,
		btn : [ '确定' ],
		title : '热评',
		content : $('#hot'),
		success : function(layero, index) {
			$('#hot').html('');
			$.ajax({
				dataType : 'json',
				type : 'get',
				url : '/rest/comments/hot',
				data : {
					id : article_id
				},
				success : function(result) {
					// console.log(result);
					$.each(result.rows, function(i, o) {
						$('#hot').append(
								"<p class=\"hot-comment\">" + o.content + "("
										+ o.up + "/" + o.down + ")</p>");
					});
				},
				error : function() {
					alert('网络问题,失败,请检查日志!');
				}
			});
		},
		yes : function(index, layero) {
			layer.close(index);// 如果设定了yes回调，需进行手工关闭
		}
	});
}
function showDetail(word, mean) {
	console.log('word:' + word);
	$('#question_area').text(word);
	// 询问框
	layer
			.open({
				type : 1,
				title : false,
				closeBtn : 0,
				btn : [ '确定' ],
				area : '400px',
				offset : '100px',
				shadeClose : true,
				content : $('#word_window'),
				success : function(layero, index) {
					layero.find('#content_area').html(
							mean.replace(/<p>/g, "<p class=\"hot-comment\">"));

					$('.close').click(function() {
						closeWindow();
					});
					$('#play')
							.click(
									function() {
										$
												.ajax({
													type : "POST",
													url : "/rest/word/mp3?t="
															+ Math.random(),
													data : {
														word : word
													},
													datatype : "text",// "xml",
													// "html",
													// "script",
													// "json",
													// "jsonp",
													// "text".
													beforeSend : function() {

													},
													// 成功返回之后调用的函数
													success : function(data) {
														console.log(data);
														if (data.length > 0) {
															$('#player')
																	.html(
																			'<embed src="/mp3/'
																					+ word
																					+ '.mp3" loop="false" autostart="false" width="0" height="0"></embed>');
														} else {
															$('#player').html(
																	'');
															layer
																	.msg(
																			'找不到声音文件!',
																			{
																				icon : 2
																			});
														}
													},
													// 调用执行后调用的函数
													complete : function(
															XMLHttpRequest,
															textStatus) {
													},
													// 调用出错执行的函数
													error : function() {
													}
												});
									});
					$('#detail').click(function() {
						window.open("http://www.iciba.com/" + word);
					});
					$('.test').click(function() {

					});
				},
				cancel : function(index) {
					if (confirm('确定要关闭么')) {
						layer.close(index)
					}
					return false;
				},
				end : function(index) {
					console.log('close in bg...');
					closeWindow();
					return false;
				}
			});
}