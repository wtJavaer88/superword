<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/talk.css" />
<link href="${pageContext.request.contextPath}/css/webuploader.css"
	type="text/css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>

<title>Insert title here</title>
</head>
<body>
	<div id="wrapper" class="wrapper beibei">
		<div id="header" class="beibei-header">
			<div id="headder-inner">
				<div class="l-inner human-logo">
					<div style="display: none">
						<img
							src="http://b0.hucdn.com/party/default/upload_5336f3a6391fe66e247d33da6e172eba_42x23.png"
							alt=""> <span class="separator"></span> <span
							id="j_header_name">智能机器人</span>
						<div class="slogan">24小时竭诚为你服务~</div>
					</div>
				</div>
				<a href="javascript:void(0);" class="human-service"
					id="customer-service" style="display: none;"></a> <a
					href="javascript:void(0);" id="win_maximize" class="max"></a> <a
					href="javascript:void(0);" class="service-close" id="close_im"></a>
			</div>
		</div>
		<div id="content" style="height: 851px;">
			<div id="mes-box">
				<div id="mes-list" style="height: 683px;">
					<div class="list-wrapper">
						<div id="loading-msg" style="display: none;">
							<img
								src="http://b0.hucdn.com/party/default/upload_e50adafce2be5391886dce58061bc362_16x16.gif"
								alt="">
						</div>
						<div id="history-message" data-page="1" style="display: block;">
							<img
								src="http://b0.hucdn.com/party/default/upload_bfafd760f1943fb5b0811a75fd9999b0_15x15.png"
								alt=""> <span>查看更多消息</span>
						</div>

						<script type="text/template" id="message_template">
                        {@each chats as chat}
                        <div class="item {@if chat.from =='self'}right{@else}left{@/if}">
                            {@if chat.time}
                            <p class="time">${chat.time}</p>
                            {@/if}
                            <div class="chat-body">
                                <p class="user">
                                    <img class="img" src=${chat.img} alt=""/>
                                </p>

                                <p class="angle"></p>

                                <div class="message">
                                    {@if chat.isImg}
                                        <img src="${chat.imgUrl}">
                                    {@else}
                                        <div class="rich-text">
                                            $${chat.message}
                                        </div>
                                        <div class="others">
                                            {@if chat.relatedQuestions}
                                            <div class="related">
                                                <div class="related-tip">您是否还要咨询以下问题:</div>
                                                <ul>
                                                    {@each chat.relatedQuestions as str}
                                                        <li>
                                                            <a class="related-link J_related-link" href="javascript:void(0)"><span class="dot">·</span>${str}</a>
                                                        </li>
                                                    {@/each}
                                                </ul>
                                            </div>
                                            {@/if}
                                            {@if chat.feedback}
                                            <div class="comment">
                                                <div class="c-box">
                                                    <a href="javascript:void(0)" class="comment-btn like" data-aid=${chat.id} data-qid=${chat.lastSendMsgId} data-target="0" >满意</a>
                                                    <a href="javascript:void(0)" class="comment-btn unlike" data-aid=${chat.id} data-qid=${chat.lastSendMsgId} data-target="1" >不满意</a>
                                                    <div class="comment-tip">感谢你的反馈!</div>
                                                </div>
                                            </div>
                                            {@/if}
                                        </div>
                                    {@/if}
                                </div>
                            </div>
                        </div>
                        {@/each}
                    </script>
						<div class="item left" style="display: none;">
							<div class="chat-body">
								<p class="user">
									<img class="img"
										src="http://b3.hucdn.com/upload/face/1602/29/43294137371642_1600x1600.jpg!160x160.jpg"
										alt="">
								</p>
								<p class="angle"></p>
								<div class="message">
									<div class="rich-text">您好，欢迎光临贝贝网，请稍后...</div>
									<div class="others"></div>
								</div>
							</div>
						</div>
						<div class="item left">
							<div class="chat-body">
								<p class="user">
									<img class="img"
										src="http://h0.hucdn.com/images/201636/ed1394715b68f74a_90x90.png"
										alt="">
								</p>
								<p class="angle"></p>
								<div class="message">
									<div class="rich-text">客服-小秀已进入对话，希望您能满意!
										为了保证服务质量，请您在服务结束后点击评价按钮，为本次服务做出评价，谢谢!</div>
									<div class="others"></div>
								</div>
							</div>
						</div>
						<div class="item left">
							<div class="chat-body">
								<p class="user">
									<img class="img"
										src="http://h0.hucdn.com/images/201636/ed1394715b68f74a_90x90.png"
										alt="">
								</p>
								<p class="angle"></p>
								<div class="message">
									<div class="rich-text">亲爱的贝贝会员，贝贝很高兴为您服务！</div>
									<div class="others"></div>
								</div>
							</div>
						</div>
						<div class="item right">
							<div class="chat-body">
								<p class="user">
									<img class="img"
										src="http://b3.hucdn.com/upload/face/1608/26/08150211726103_276x276.jpg!160x160.jpg"
										alt="">
								</p>
								<p class="angle"></p>
								<div class="message">
									<img
										src="http://b6.hucdn.com/upload/im/1702/03/97551011354777_100x100.jpeg">
								</div>
							</div>
						</div>
						<div class="item left">
							<div class="chat-body">
								<p class="user">
									<img class="img"
										src="http://h0.hucdn.com/images/201636/ed1394715b68f74a_90x90.png"
										alt="">
								</p>
								<p class="angle"></p>
								<div class="message">
									<div class="rich-text">
										亲爱的宝妈，请问您想咨询什么问题呢，亲爱的，您慢慢说，我一定竭诚为您解决的。</div>
									<div class="others"></div>
								</div>
							</div>
						</div>

						<div class="item right">
							<div class="chat-body">
								<p class="user">
									<img class="img"
										src="http://b3.hucdn.com/upload/face/1608/26/08150211726103_276x276.jpg!160x160.jpg"
										alt="">
								</p>
								<p class="angle"></p>
								<div class="message">
									<div class="rich-text">你好</div>
									<div class="others"></div>
								</div>
							</div>
						</div>

						<div class="item left">
							<div class="chat-body">
								<p class="user">
									<img class="img"
										src="http://h0.hucdn.com/images/201636/ed1394715b68f74a_90x90.png"
										alt="">
								</p>
								<p class="angle"></p>
								<div class="message">
									<div class="rich-text">请问还有其他可以帮到您的吗</div>
									<div class="others"></div>
								</div>
							</div>
						</div>
						<div class="item left">
							<div class="chat-body">
								<p class="user">
									<img class="img"
										src="http://h0.hucdn.com/images/201636/ed1394715b68f74a_90x90.png"
										alt="">
								</p>
								<p class="angle"></p>
								<div class="message">
									<div class="rich-text">
										您好，如果没有其他问题的话，我们将暂时关闭与您的对话，如有问题欢迎您随时咨询呢</div>
									<div class="others"></div>
								</div>
							</div>
						</div>
						<div class="notice-line">
							<p class="J_conBreak">
								客服结束了对话，如需咨询请点击 <a class="J_reconnect-btn reconnect-btn">重新连接</a>
							</p>
						</div>
					</div>
				</div>
				<div id="star-box" style="display: none;">
					<div class="header">
						<p>您对当前客服的服务满意吗？</p>
						<a href="#" class="close"> </a>
					</div>
					<div class="content">
						<p id="star-tip">评价：</p>

						<p id="start-number">
							<img
								src="http://b0.hucdn.com/party/default/upload_11a488d7b82121d675c8248675d0f027_13x12.png"
								alt=""> <img
								src="http://b0.hucdn.com/party/default/upload_11a488d7b82121d675c8248675d0f027_13x12.png"
								alt=""> <img
								src="http://b0.hucdn.com/party/default/upload_11a488d7b82121d675c8248675d0f027_13x12.png"
								alt=""> <img
								src="http://b0.hucdn.com/party/default/upload_11a488d7b82121d675c8248675d0f027_13x12.png"
								alt=""> <img
								src="http://b0.hucdn.com/party/default/upload_11a488d7b82121d675c8248675d0f027_13x12.png"
								alt="">
						</p>

						<p id="start-count">
							<img
								src="http://b0.hucdn.com/party/default/upload_3092d3af7c398ef94cf6379c8d9bbf9e_8x14.png"
								alt=""> <span>5分，非常满意</span>
						</p>
					</div>
					<div class="footer">
						<a id="star-submit" href="#">提交</a>
					</div>
					<div class="jiao"></div>
				</div>
				<div id="editor-box">
					<div class="search-box">
						<div class="search-bar">
							<input type="text" id="message-txt" class="message-txt"
								placeholder="请简要描述你的问题，如&quot;订单如何取消&quot;">
						</div>
						<button href="#" class="send-btn" id="send-btn">发送</button>
					</div>
				</div>
				<div id="edit-box">
					<div id="edit-bar" style="position: relative; display: block;">
						<ul>
							<li>
							<div id="uploader" class="file">
								<!--用来存放item-->
								<div id="fileList" class="uploader-list"></div>
								<div id="filePicker" >.
								</div>
								<a id="add-star" href="#"></a>
							</div>
							
							</li>
							<li></li>
						</ul>
					</div>
					<div id="suggestions" style="display: none;">
						<script type="text/template" id="question_template">
                        {@each questions as question}
                            <li>
                                <a>$${question}</a>
                            </li>
                        {@/each}
                    </script>
						<div class="inner">
							<ul id="question-ul">
							</ul>
						</div>
					</div>
					<textarea id="message-text"
						placeholder="请简要描述你的问题，如&quot;订单如何取消&quot;"
						style="color: rgb(153, 153, 153);"></textarea>
					<div id="text-overflow">请勿超出100个字符~</div>
					<div id="edit-footer">
						<a class="tip" id="live800" href="#" target="_blank">交易纠纷联系贝贝客服</a>

						<div class="button-groups">
							<a href="#" class="button" id="cancel-im">结束对话</a> <a href="#"
								class="button send-btn" id="send-im">发送</a>
						</div>
					</div>
				</div>
			</div>
			<div id="brand-box">
				<ul id="myTab" class="nav nav-tabs">
					<li class="active"><a href="#qustions" data-toggle="tab">常见问题</a></li>
					<li><a href="#orders" data-toggle="tab">订单</a></li>
				</ul>
				<div  class="tab-content" style="height: 816px;">
					<div id="qustions" class="tab-pane fade in active">
						<div id="problem-list" style="height: 816px;">
							<div class="list-cont" id="faq-list-1">
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-down"></div>
										售后申请步骤？
									</div>
									<div class="problem-answer" style="display: block;">
										您好，贝贝的售后申请步骤是：登陆贝贝—我的—全部订单—找到您要申请售后的订单—看到订单详情—点击售后按照流程操作，选择您要申请售后的选项，谢谢！
									</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										订单支付成功后多长时间发货？
									</div>
									<div class="problem-answer">
										您好，一般普通订单将在下单后24小时内仓库会安排发货（全球购订单3-7个工作日），发货后，系统会以短信或站内信方式告知，请您注意查看哦。温馨提示：大促或节假日期间（如周年庆、双11等）订单量较大，发货时间上可能会有所延误，请耐心等待，我们有专门人员会及时催促商家的哦。
									</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										申请退货，退款什么时候能到账？
									</div>
									<div class="problem-answer">您好，关于退款到账时效的问题：
										1、若您申请售后为“仅退款—未收到货”，仓库会在48小时内会核实处理；
										2、若您申请售后为“退货退款”，待退货商品签收后，仓库会尽快为您处理退款，请耐心关注售后信息
										3、自行取消或者联系客服取消的，取消成功后款项会在1-10个工作日内原路返回。 温馨提示：
										贝贝退款成功后，款项均会在1-10个工作日内原路返回，请您到时候注意查收哦。</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										贝贝现金券要如何使用？
									</div>
									<div class="problem-answer">现金券使用规则：
										1、单笔交易金额满足现金券使用规则，可使用现金券进行支付； 2、单笔交易只能使用一张现金券；
										3、现金券仅能在贝贝提交订单时抵扣对应支付商品金额，不能兑现和用于其他用途；
										4、若订单产生退款退货，该笔订单使用现金券所享受的优惠将不予返还。 现金券使用方法：
										1、购买商品结算并确认订单后，商品下方会出现“使用现金券”一栏； 2、可选择相应的现金券或输入可用的现金券券号；
										3、订单金额超出现金券折扣部分可选用其它支付方式进行支付。</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										获得的贝壳要如何使用？
									</div>
									<div class="problem-answer">购物车结算页面选择贝壳数量，即可减免相应付款金额。</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										需要取消订单，如何操作？
									</div>
									<div class="problem-answer">您好，关于取消订单：
										a、未发货状态：一般普通专场订单下单后2小时内您可自行取消订单（全球购订单30分钟内）； 若超过2小时的可在订
										单详情页面下方，点击“申请退款”按钮（除全球购订单）。 申请成功后，品牌入驻商会在6小时内处理的哦。
										b、已发货状态：由于此时订单已无法取消操作，建议您收货之后看下是否喜欢，若您确实不需要商品，建议拒签，然后申请售后选择“仅退款—未收到货”。
										c、拼团的订单无法取消，需拼团成功30分钟内联系高级在线客服核实处理哦。 温馨提示：
										全球购订单超时需要取消的，需联系高级在线客服核实处理哦</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										需要退货，要将商品退至哪里？
									</div>
									<div class="problem-answer">您可以通过以下方式查看退货地址：
										手机端：点击“我的—全部订单—选择需要退货的订单—申请售后”选择退货退款，申请完成后即可看到退货地址；
										电脑端：点击“我的订单—全部订单—选择需要退货的订单—售后”选择退货退款，申请完成后即可看到退货地址。 温馨提示：
										全球购订单，需要审核通过，方可看到地址。若您的售后显示关闭或者取消，请联系高级在线客服帮您查询。</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										购买的商品不合适，可以换货吗？
									</div>
									<div class="problem-answer">
										所有特卖商品如未经使用或穿着，商品及外包装保持出售时的原状，商品吊牌及配件齐全，将享受7天无理由退货服务。海外购特殊类目及特殊情况除外。即如果您对商品不满意，在不影响二次销售的前提下，可在收货后7天之内申请无理由退货。
									</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										在贝贝购物是否包邮？
									</div>
									<div class="problem-answer">
										贝贝支持全国大多数地区下单即包邮（偏远地区新疆、西藏、内蒙古除外）。若您的收货地址属于偏远地区，普通专场按每个订单加收10元运费，全球购按每个订单加收8元运费；部分类目（一般指大件商品）因品牌入驻商原因不支持送货。
									</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										贝贝的商品有正品保障吗？
									</div>
									<div class="problem-answer">
										是的。贝贝承诺，所有品牌入驻商均经过严格的资质审查，在线销售的所有特卖商品均为品牌正品。您在贝贝购买的每件特卖商品，均由中国人民财产保险公司（PICC）承保。
									</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										提现到支付宝多久到账？
									</div>
									<div class="problem-answer">申请提现后的2个工作日内到账。</div>
								</div>
							</div>
							<div class="list-cont" id="faq-list-2" style="display: none">
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-down"></div>
										平台运营模式是怎样的？
									</div>
									<div class="problem-answer" style="display: block;">
										贝贝是以品牌专场的形式做特卖活动（不能多品牌商品混合）；专场的商品数要丰富；价格要求全网最低价；专场特卖，一期专场活动是5天；单品特卖，一期活动是3天。
									</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										入驻申请中的店铺如何选择？
									</div>
									<div class="problem-answer">
										旗舰店自主入驻，需要是自有品牌或者是旗舰店授权，且仅支持经营一个品牌；如果是独家代理入驻且经营一个品牌，可以选择专卖店；如果需要同时经营一到多个同类目的品牌，或者是一到多级代理入驻，请选择专营店。一个店铺只能运营同一个类目的品牌。
									</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										入驻审核一般需要多久？
									</div>
									<div class="problem-answer">
										品牌审核一般是2个工作日，品牌审核通过后在1-2个工作日审核您的入驻资质，请持续关注后台页面提示。</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										商品的审核时间？
									</div>
									<div class="problem-answer">
										商品提交后2个工作日由商品审核专员审核通过后才能加入专场，请您耐心等待审核。</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										保证金的作用？
									</div>
									<div class="problem-answer">
										保证金主要用于保证商家按照贝贝的规则进行特卖活动，并且在商家有违规行为时根据相关规则用于向贝贝及消费者支付违约金。（保证金不提供发票）；一个商家一份保证金；保证金支持退回，若无违规处罚记录，申请后1个月内，结算完成后，退还全额保证金金额。
									</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										特卖运营如何联系？
									</div>
									<div class="problem-answer">特卖活动的排期是由运营负责，审核活动时会主动联系商家。</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										违规订单如何申诉？
									</div>
									<div class="problem-answer">
										请在平台规定时间内申诉（“超时未发货”、“超时无物流”类型的申诉时间为2个自然日，“质量问题”、“非正品”类型的申诉时间为10个自然日，其他违规类型的申诉时间为4个自然日）。
										申诉机会仅此一次，请确认上传真实有效的凭证，逾期申诉通道将被关闭。</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										提现打款时间是多久？
									</div>
									<div class="problem-answer">结算无误，确认后，在一个工作日后可进行提现操作；提现后
										，贝贝网会在您申请提现后的十个工作日打款</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										平台结算规则是什么？
									</div>
									<div class="problem-answer">
										结算规则：商家结算日（以商家结算页面显示的具体日期为准），商家可对上个结算日期范围内所有完结订单进行结算。每次结算，会对上个结算日期范围内所有完结订单的款项进行结算。非完结状态的订单会滞留到下一次结算时根据状态进行结算。
									</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										对账单中的促销服务费是什么？
									</div>
									<div class="problem-answer">
										促销服务费是订单中买家使用了贝贝的优惠劵支付的金额，优惠券是贝贝帮助商家促销，提高商家流量，直接发放给用户使用的。这部分在结算时是贝贝承担，并不影响商家的结算。
									</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										如何更改品牌售后信息?
									</div>
									<div class="problem-answer">
										关于修改品牌售后信息的发货地址、默认快递、客服QQ和电话、退货地址等，您可以点击【商家中心】—【商家管理】—【我的服务簿】—【管理服务簿】自主更改并保存。
									</div>
								</div>
							</div>
							<div class="list-cont" id="mizhe-list" style="display: none">
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-down"></div>
										订单支付成功后多久发货？
									</div>
									<div class="problem-answer" style="display: block;">
										您好，正常情况下您购买的商品将在下单后24小时内为您发货，物流正常2-5天内可以收到商品（偏远地区除外，如新疆、西藏、内蒙古等地），但若遇到购物平台大型活动造成物流高峰期，物流转件派送缓慢，请您耐心等待，给您造成的不便敬请谅解。
									</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										订单显示已经发货，但无物流信息？
									</div>
									<div class="problem-answer">您好，若您的订单已发货但无物流流转信息，请您核实：
										1、下单72小时内：快递公司揽件后，下单72小时内更新揽件信息是正常现象，建议您优先耐心等待；
										2、下单已超过72小时：若超72小时未更新物流信息，多是因为快递单量较多，快递未及时录入运单号所致，建议您申请下售后选择【仅退款】—【未收到货】，申请后仓库会在48小时内核实处理。

									</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										退货运费？
									</div>
									<div class="problem-answer">
										您好，从米折购买的商品（除全球购及特殊商品如食品、内衣等外）在不影响二次销售的前提下均可办理退货，退款完成后系统将自动补贴您一张8元无门槛现金券（此现金券全场通用，可作为您下次购物实付金额满足9元直接抵扣使用，有效期15天）作为您的运费补贴，感谢您对米折的支持。
										温馨提示： 1、若当月运费补贴已超限额（5次，退货运费现金券到账金额超过40元），退款完成后也无现金券补贴哦。
										2、若非商品质量问题退货，运费只有8元现金券补贴，超出部分无额外补贴，请您知悉。</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										退款去向？
									</div>
									<div class="problem-answer">
										您好，退款成功后，款项会在1-10个工作日内原路返回，请您到时候注意查收，感谢您对米折的支持与信赖！</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										能否换货？
									</div>
									<div class="problem-answer">
										您好，由于米折是品牌商品限时限量的特卖模式，库存有限，没有多余库存为您换货哦。
										如您对收到的商品不满意，可以在收到商品的七天内，且不影响二次销售的情况下申请退货处理哦。</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										需要取消订单如何操作？
									</div>
									<div class="problem-answer">
										您好，普通专场下单后未发货状态下，您可以在下单后2小时内自行取消订单（全球购订单30分钟内），若超过2小时的可在订单详情页面下方，点击【申请退款】按钮。申请成功后，品牌入驻商会在6小时内处理的哦。
										温馨提示： 全球购及拼团订单超时取消需联系高级在线客服核实处理哦。</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										可以备注指定快递吗？
									</div>
									<div class="problem-answer">
										您好，不同品牌默认的合作快递不同，您可在商品参数里查询默认快递，快递是随机发货的，暂不支持指定快递哦。 温馨提示：
										若您想要指定的快递公司寄送，您可在下单时进行备注，仓库会尽量满足您的要求，但无法百分百保证能发您指定的快递，请您谅解。</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										7天无理由退货的前提？
									</div>
									<div class="problem-answer">
										您好，只要您在订单确认收货的7天内，保证商品及本身包装原状且配件齐全，不影响二次销售，吊牌保持原样，未经拆洗、未经人为破坏，都是可以退货的哦。
										温馨提示： 1.若包裹内有说明退货填写说明，建议您填写后放入包裹一并寄回。
										2.全球购、食品、贴身衣物、大件商品退货，请先咨询高级在线客服。</div>
								</div>
								<div class="item">
									<div class="problem-word J_problem-word">
										<div class="triggle triangle-right"></div>
										如何申请售后？
									</div>
									<div class="problem-answer">您可以通过以下方式申请售后：
										手机端：点击【我的】—【全部订单】—【申请售后】； 电脑端：点击【我的订单】—【售后】 选择相应的售后类型就可以呢。
										温馨提示： 1、若物流显示签收后商品少发或未收到商品，仍想要商品的，售后原因可选择【补寄】；
										2、若商品少发或未收到商品，想要退款的，售后原因可选择【仅退款】； 3、若已收到商品，想要退货，售后原因可选择【退货退款】。

									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="orders" class="tab-pane fade">
						<div id="order-list">
							<script type="text/template" id="order_template">
                            {@each orders as order}
                            <div class="order-item">
                                <div class="order-header">
                                    <p>编号：${order.oid}</p>
                                </div>
                                <div class="order-content J_order-content" data-oid="${order.oid}">
                                    <span class="status">${order.status_text}</span>
                                    <img class="order-image" data-oid="${order.oid}" src="${order.img}!100x100.jpg"" alt="">

                                    <div class="order-info">
                                        <p class="one_float">共${order.num}件</p>

                                        <p class="two_float">总计：<span class="price">￥${order.orderPay}</span></p>
                                    </div>
                                </div>
                                <div class="order-footer">
                                    <p>下单时间：${order.createTime}</p>
                                    <span class="send-order" id=oid_${order.oid} data-oid="${order.oid}" data-status="${order.status_text}"
                                          data-gmt_create="${order.gmt_create}" data-img="${order.img}"
                                          data-total_fee="${order.orderPay}" data-num="${order.num}"></span>
                                </div>
                            </div>
                            {@/each}
                        	</script>
                        	<div class="order-item">
                                <div class="order-header">
                                    <p>编号：1111</p>
                                </div>
                                <div class="order-content J_order-content" data-oid="2">
                                    <span class="status">已完成</span>
                                    <img class="order-image" data-oid="3" src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" alt="">

                                    <div class="order-info">
                                        <p class="one_float">共1件</p>
                                        <p class="two_float">总计：<span class="price">￥100.00</span></p>
                                    </div>
                                </div>
                                <div class="order-footer">
                                    <p>下单时间：2222-12-22</p>
                                </div>
                            </div>
                            <div class="order-item">
                                <div class="order-header">
                                    <p>编号：1111</p>
                                </div>
                                <div class="order-content J_order-content" data-oid="2">
                                    <span class="status">已完成</span>
                                    <img class="order-image" data-oid="3" src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" alt="">

                                    <div class="order-info">
                                        <p class="one_float">共1件</p>
                                        <p class="two_float">总计：<span class="price">￥100.00</span></p>
                                    </div>
                                </div>
                                <div class="order-footer">
                                    <p>下单时间：2222-12-22</p>
                                </div>
                            </div>
							<div class="no-order">
								<div class="default-show">您最近没有订单哦~</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="customer-score" id="customer-score">
				<div class="wrap">
					<div class="title">你对当前客服人员的服务满意吗？</div>
					<div class="options">
						<ul>
							<li><input type="radio" value="0" name="score"
								checked="checked" id="score1"><label for="score1"><i
									class="checked"></i>满意</label></li>
							<li><input type="radio" value="1" name="score" id="score3"><label
								for="score3"><i></i>一般</label></li>
							<li><input type="radio" value="2" name="score" id="score5"><label
								for="score5"><i></i>不满意</label></li>
						</ul>
					</div>
					<div class="btns">
						<button class="ok J_ok">确定</button>
						<button class="cancel J_cancel">取消</button>
					</div>
				</div>
			</div>
			<div id="close-connect">
				<div class="wrap">
					<div class="title">确定离开咨询吗？</div>
					<div class="btns">
						<button id="leave-ok">确定</button>
						<button id="leave-cancel">取消</button>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div id="pic_window" style="display: none">
		<img id="pic" />
	</div>
</body>

<script type="text/javascript">
		var domain='';
		$(function () {
			domain = "${pageContext.request.contextPath}";
			var uploader = WebUploader.create({
				// 自动上传。
				auto : true,
				// swf文件路径
				swf : domain + '/js/webuploader/Uploader.swf',
				// 文件接收服务端。
				server : domain + '/rest/image/upload',
				// 选择文件的按钮。可选。
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.
				pick : {
					id : '#filePicker',
					multiple : false
				},
				// 只允许选择文件，可选。
				accept : {
					title : 'Images',
					extensions : 'gif,jpg,jpeg,png',
					mimeTypes : 'image/*'
				},
				// 20 M
				fileSizeLimit : 20 * 1024 * 1024,
				// 10 M
				fileSingleSizeLimit : 10 * 1024 * 1024
			});
			// 文件上传成功，给item添加成功class, 用样式标记上传成功。
			uploader.on('uploadSuccess', function(file, response) {
				var imgurl = domain + response.media;
				$('.list-wrapper').append('<div class="item right"><div class="chat-body"><p class="user"><img class="img" src="http://b3.hucdn.com/upload/face/1608/26/08150211726103_276x276.jpg!160x160.jpg" alt=""></p> <p class="angle"></p><div class="message"><img src="'+imgurl+'"></div></div></div>');
				console.log("success");
				//绑定点击图片的事件
				$('.list-wrapper img').each(function(){
					$(this).unbind();
					$(this).bind('click',function(){
						showPic(domain+$(this).attr('src'));
					});
				});
			});
			$('.problem-word').bind('click',function(){
				$('.problem-answer').hide();
				$(this).next().show();
			});
			
			
			function showPic(pic){
				layer.open({
					type : 1,
					area : '500px',
					offset : '200px',
					shadeClose : true,
					content : $('#pic_window'),
					success : function(layero, index) {
						$('#pic').attr('src',pic);
					},
					yes : function(index, layero) {
						layer.close(index);// 如果设定了yes回调，需进行手工关闭
					}
				});
			}
		});
		
</script>

</html>
