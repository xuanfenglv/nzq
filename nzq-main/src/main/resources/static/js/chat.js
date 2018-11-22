var chat_xf;
var msg_total = 0;
var websocket = null;
//var friend_xf = "";
var receive_xf;
var send_xf;
var my_xf = $("#id_container").html();
var edit_group_status = 0;//0 is addgroup ,1 is edit group name
var edit_group_id = 0;
//存储当前用户的分组信息
var friendGroup = new Array();
//$('#edit_my_birth').bind('input oninput', function() {  
//	judgeBirthday();
//}); 

// 判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	// alert('support com.ke.com.websocket');
//	com.ke.com.websocket = new WebSocket("ws://159y3720p2.imwork.net/nzq/chatserver");
	websocket = new WebSocket("ws://localhost:8080/nzq/chatserver");
// 	com.ke.com.websocket = new WebSocket("ws://192.168.199.187:8080/nzq/chatserver");
} else {
	alert('Not support com.ke.com.websocket');
}

// 连接发生错误的回调方法
websocket.onerror = function() {
	// setMessageInnerHTML("error");
	alert("错误");
};

// 连接成功建立的回调方法
websocket.onopen = function() {
	var xf = $("#id_container").html();
	// setMessageInnerHTML("open");
	websocket.send("1" + xf);
};

// 接收到消息的回调方法
websocket.onmessage = function(event) {
	var msg = event.data;
//	alert("收到消息"+msg);
	var flag = msg.charAt(0);
	switch (flag) {
	case "1":
//		alert(msg);
		break;
	case "2":
		getMsg(msg);
		break;
	case "3":
		offToON(msg);
		break;
	case "4":
		onToOff(msg);
		break;
	case "5":
		receiveApplication(msg);
		break;
	case "6":
		addNewFriend(msg);//通知发送申请的人添加好友到列表
		break;
	case "7":
		alert(msg);
		forceOffline();
		break;
	case "8":
		refuseFriend(msg);
		break;
	case "9":
		deleteFriend(msg);
		break;
	default:
		break;
	}
};

// 连接关闭的回调方法
websocket.onclose = function() {
	alert("连接关闭！！！");
};

// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function() {
	closeWebSocket();
};
//仅仅关闭页面时有效，页面前进后退不管用
function changeOffline() {
	var xf = $("#id_container").html();
	$.ajax({
		url : "/nzq/SetOffline",
		data : {
			xf4 : xf
		},
		method : 'get',
		error : function() {
			console.log("下线失败");
		},
		success : function() {
			console.log("下线成功");
		}
	})
}
// 关闭连接
function closeWebSocket() {
	//向好友发送下线通知
	//由于强行关闭浏览器时不一定能发出去，所以废除
//	sendOfflineNotice();
//	com.ke.com.websocket.close();
}

//异地登录强制下线
function forceOffline() {
	websocket.close();
	alert("假装去登录页");
}
// 发送消息
function send() {
	var message = document.getElementById('text').value;
	websocket.send(message);
}
		$.ajax({
			url : "/nzq/SubGroup",
			data : {
				xf : $("#id_container").html()
			},
			method : 'get',
			dataType : 'json', // 很重要!!!. 预期服务器返回的数据类型
			error : function() {
				alert("error occured!!!");
			},
			success : function(data) {
				$.each(data,function(index) {
									var group = '<div class="group"><div class="group_detail" id="'
											+ data[index].sno
											+ '" onclick="showFriends(this.id)"><div class="group_arrow"><img src="/nzq/img/right.png" style="width: 100%;" imgid="'
											+ data[index].sno
											+ '" /></div><div class="group_name">'
											+ data[index].sname
											+ '</div><div class="online_number"><span class="f_online" >'
											+ data[index].online
											+ '</span>/<span class="f_total" >'
											+ data[index].total
											+ '</span></div></div><div class="panel" style="display: none;" groupid="'
											+ data[index].sno
											+ '"><div class="online"></div><div class="offline"></div></div></div>';
//									friendGroup[index] = {sno:data[index].sno,sname:data[index].sname};
//									$("#group").html($("#group").html()+'<option value="'+data[index].sno+'">'+data[index].sname+'</option>');
//									$("#group1").html($("#group1").html()+'<option value="'+data[index].sno+'">'+data[index].sname+'</option>');
									
									$("#fl_group").html($("#fl_group").html() + group);
								})
				$
						.ajax({
							url : "/nzq/DetailSubGroup",
							data : {
								xf2 : $("#id_container").html()
							},
							method : 'post',
							dataType : 'json', // 很重要!!!. 预期服务器返回的数据类型
							error : function() {
								alert("error occured!!!");
							},
							success : function(data) {
								var name;
								$.each(data,function(index) {
													if(data[index].remark=="") {
														name = data[index].nickname;
													} else {
														name = data[index].remark;
													}
													if (data[index].fstatus == 1) {
														var friend = '<div xf="'
																+ data[index].fxf
																+ '" class="friend_msg" onclick="showChatDetail('+ data[index].fxf+ ')"><img class="friend_msg_photo" src="/nzq/photo/'
																+ data[index].fxf
																+ '.jpg" /><div class="friend_msg_name">'
																+ name
																+ '</div><div class="friend_msg_content">'
																+ '[在线]'
																+ '</div></div>';
														$("[groupid="+ data[index].fsno+ "] .online").html(
																		$("[groupid="+ data[index].fsno+ "] .online").html()+ friend);
//														friend_xf += data[index].fxf.toString() + ".";
													} else {
														var friend = '<div xf="'
																+ data[index].fxf
																+ '" class="friend_msg" onclick="showChatDetail('+ data[index].fxf+ ')"><img class="friend_msg_photo" src="/nzq/photo/'
																+ data[index].fxf
																+ '.jpg" /><div class="friend_msg_name">'
																+ name
																+ '</div><div class="friend_msg_content">'
																+ '[离线]'
																+ '</div></div>';
														$("[groupid="+ data[index].fsno+ "] .offline").html(
																		$("[groupid="+ data[index].fsno+ "] .offline").html()+ friend);
													}
												})
//												console.log("send_before");
//												sendOnlineNotice();已废除
								 				getMyApplication();
								 				getMyReceiveApplication();
								 				initMyInfo();
							}
						});
			}
		});
function initMyInfo() {
	$.ajax({
		url : "/nzq/GetMyUserInfoByXf",
		data : {
			xf6 : $("#id_container").html()
		},
		method : 'get',
		dataType : 'json', // 很重要!!!. 预期服务器返回的数据类型
		error : function() {
			console.log("获取我的资料失败!!!");
		},
		success : function(data) {
			console.log("获取我的资料成功!!!");

			$("#myinfo_name").html(data.nickname);
			$("#my_birth").html(data.s_birthday);
			$("#my_age").html(data.age);
			if(data.sex=="m")
				$("#my_sex").html("男");
			else
				$("#my_sex").html("女");
			$("#my_phone").html(data.telephone);
			$("#my_grade").html(data.grade);
			$("#my_money").html(data.money);
		}
	})
}
//my
function getMyApplication() {
	var xf = $("#id_container").html();
	$.ajax({
		url : "/nzq/GetMyApplication",
		data : {
			xf1 : xf
		},
		method : 'get',
		dataType : 'json', // 很重要!!!. 预期服务器返回的数据类型
		error : function() {
			console.log("获取我的申请失败");
		},
		success : function(data) {
			console.log("获取我的申请成功");
			var conditions_text = "";
			$.each(data,function(index) {
				console.log(data[index].send_xf);
				if(data[index].conditions == 0) {
					conditions_text = "已发送验证消息";
				} else if(data[index].conditions == 1) {
					conditions_text = "对方已添加你为好友";
				} else if(data[index].conditions == 2) {
					conditions_text = "拒绝了你的好友申请";
				}
				addMyApplication(data[index].receive_xf,data[index].nickname,conditions_text);
			})
		}
	})
}
function getMyReceiveApplication() {
	var xf = $("#id_container").html();
	$.ajax({
		url : "/nzq/GetMyReceiveApplication",
		data : {
			xf2 : xf
		},
		method : 'get',
		dataType : 'json', // 很重要!!!. 预期服务器返回的数据类型
		error : function() {
			console.log("获取我收到的申请失败");
		},
		success : function(data) {
			console.log("获取我收到的申请成功");
			$.each(data,function(index) {
				console.log(data[index].send_xf);
				addMyReceiveApplication(data[index].send_xf,data[index].nickname,data[index].text,data[index].conditions)
			})
		}
	})
}
// 显示ID为id的好友列表
function showFriends(id) {
	$("[groupid=" + id + "]").toggle();

	if ($("[groupid=" + id + "]").is(":visible")) {
		$("[imgid=" + id + "]").attr("src", "/nzq/img/down.png");
	} else {
		$("[imgid=" + id + "]").attr("src", "/nzq/img/right.png");
	}
}
// 显示聊天框
function showChatDetail(id) {
	if (($("[friendid=" + id + "]").length > 0)
			&& ($("div[friendid=" + id + "] .number").html() != "0")) {
		msg_total -= parseInt($("div[friendid=" + id + "] .number").html());
		// alert(msg_total);

		$("div[friendid=" + id + "] .number").html("0");
		$("div[friendid=" + id + "] .number").css("display", "none"); 
		if (msg_total == 0) {
			$("#msg_number_val").html("");
			$("#newMsg_num").css("display", "none");
		} else {
			$("#msg_number_val").html("(" + msg_total + ")");
		}
		$("#newMsg_num").html(msg_total);
		
	}

	chat_xf = id;
	haveSession(id);
	$("#chat_detail").css("display", "inline");
	$("#msglist").css("display", "inline");
	$("#f_name").html($("div[xf = " + id + "] .friend_msg_name").html());
	$("#f_state").html($("div[xf = " + id + "] .friend_msg_content").html());
	// 判断是否有聊天面板，有则直接打开，否则新建
	if ($("[xf_no=" + id + "]").length > 0) {
		$("[xf_no=" + id + "]").css("display", "inline");
	} else {
		var chat_box = '<div xf_no="' + id + '"></div>';
		$(".chat_box").html($(".chat_box").html() + chat_box);
	}
	$("[xf_no=" + id + "]").scrollTop = $("[xf_no=" + id + "]").scrollHeight; 
}
// 判断点击的好友是否有会话
function haveSession(id) {
	if ($("[friendid=" + id + "]").length > 0) {
		// alert("会话已存在");
		// 隐藏普通发送按钮
		$("#send_button1").css("display", "inline");
		$("#send_button2").css("display", "none");
	} else {
		// 显示发送按钮
		// alert("会话不已存在");
		$("#send_button1").css("display", "none");
		$("#send_button2").css("display", "inline");
	}
}

function haveSessionReceive(id) {
	if ($("[friendid=" + id + "]").length == 0)
		createSession(id);
}
function playDiDiDi() {
	var audio = document.createElement("audio");
	audio.src = "/nzq/sound/dididi.wav";
	audio.play();
}

// 2.接受聊天消息
function getMsg(msg) {
//	alert(msg);
	var division = msg.indexOf("|");
	var ms = msg.substr(0,division).split(".");
	var text = msg.substr(division+1);
//	 alert("好友:"+ms[1]+"发来消息:"+text);
	// 如果没有与该好友的聊天面板的话，创建面板
	if ($("[xf_no=" + ms[1] + "]").length == 0) {
		//创建一个隐藏的聊天面板
		var chat_box = '<div xf_no="' + ms[1]
				+ '" style="display:none;"></div>';
		//将聊天面板插入到chat_box
		$(".chat_box").html($(".chat_box").html() + chat_box);
	}
	//生成气泡
	var msg_box = '<div class="outerChat">'
		+'<img class="outerChat_photo f_photo" src="/nzq/photo/'+ms[1]+'.jpg"/>'
		+'<div class="outerChat_msg f_msg outerChat_msg_blue">'+text+'</div>'
	+'</div>';
	//插入气泡
	$("[xf_no=" + ms[1] + "]")
			.html($("[xf_no=" + ms[1] + "]").html() + msg_box);
	$("[xf_no=" + chat_xf + "]").scrollTop($("[xf_no=" + chat_xf + "]").height()); 
	//创建会话
	haveSessionReceive(ms[1]);
	//向会话插入内容
	$("div[friendid=" + ms[1] + "] .content").html(text);
	//如果没有与此人聊天
	if ($("[xf_no=" + ms[1] + "]").css("display") == "none") {
		$("div[friendid=" + ms[1] + "] .number").html(
				parseInt($("div[friendid=" + ms[1] + "] .number").html()) + 1);
		$("div[friendid=" + ms[1] + "] .number").css("display", "inline");
		// alert("before:"+msg_total);
		msg_total += 1;
		$("#newMsg_num").html(msg_total);
		$("#newMsg_num").css("display", "inline");
		// alert(msg_total);
		$("#msg_number_val").html("(" + msg_total + ")");
	}
	playDiDiDi();
}
// 发送消息
function sendMessage() {
	var msg = $("#msg_input").val();
	if (msg != "") {
		var msg_box = '<div class="outerChat">'
						+'<img class="outerChat_photo i_photo" src="/nzq/photo/'+my_xf+'.jpg"/>'
						+'<div class="outerChat_msg i_msg outerChat_msg_blue">'+msg+'</div>'
					+'</div>';
		$("[xf_no=" + chat_xf + "]").html(
				$("[xf_no=" + chat_xf + "]").html() + msg_box);
		$("[xf_no=" + chat_xf + "]").scrollTop($("[xf_no=" + chat_xf + "]").height()); 
		$("div[friendid=" + chat_xf + "] .content").html(msg);
		var message = "2." + $("#id_container").html() + "." + chat_xf + "|"
				+ msg;
		// alert(message);
		websocket.send(message);
		$("#msg_input").val("");
	} else {
		showTip("不可发送空消息");
	}
}
function playOnline() {
	var audio = document.createElement("audio");
	audio.src = "/nzq/sound/online.wav";
	audio.play();
}
function playOffline() {
	var audio = document.createElement("audio");
	audio.src = "/nzq/sound/offline.wav";
	audio.play();
}
//
function offToON(msg) {
	var xf = msg.substr(1);
	var grandpa = $("[xf ="+xf+"]").parent().parent();
	grandpa.find($(".online")).append($("[xf ="+xf+"]"));
	grandpa.find($(".online")).find($("[xf ="+xf+"]")).find($(".friend_msg_content")).html("<span>[在线]</span>");
	grandpa.find($(".offline")).find($("[xf ="+xf+"]")).remove();
	playOnline();
}
function onToOff(msg) {
	var xf = msg.substr(1);
	var grandpa = $("[xf ="+xf+"]").parent().parent();
	grandpa.find($(".offline")).append($("[xf ="+xf+"]"));
	grandpa.find($(".offline")).find($("[xf ="+xf+"]")).find($(".friend_msg_content")).html("<span>[离线]</span>");
	grandpa.find($(".online")).find($("[xf ="+xf+"]")).remove();
	playOffline();
}
//5.
function receiveApplication(msg) {
	var xf = msg.substr(1);
	//生成本地
//	alert(xf+"请求添加好友");
	addReceiveApplication(xf);
}
//6.addNewFriend
function addNewFriend(msg) {
	var myxf = $("#id_container").html();
	//获取xf，nickname，【在线】，分子分母都加一
	//动态添加一条
	//删除请求消息，不可见
	var xf = msg.substr(1);
	//生成本地
//	alert("添加好友"+xf+"成功");
	$.ajax({
		url : "/nzq/AddNewFriend",
		data : {
			myxf3 : myxf,
			xf5 : xf
		},
		dataType : 'json', 
		method : 'post',
		error : function() {
			console.log("向好友列表中插入新的好友失败");
		},
		success : function(data) {
				console.log("向好友列表中插入新的好友成功");
				$("[application_send_id = " +xf+ "] .app_condition").html("对方已添加你为好友");
//				$("[application_send_id = " +xf+ "]").remove();
				var name;
				if(data.remark=="") {
					name = data.nickname;
				} else {
					name = data.remark;
				}
				if (data.fstatus == 1) {//在线
					var friend = '<div class="friend_msg" xf="'+ data.fxf +'" onclick="showChatDetail('+ data.fxf +')"><img class="friend_msg_photo" src="/nzq/photo/'
							+ data.fxf
							+ '.jpg" /><div class="friend_msg_name">'
							+ name
							+ '</div><div class="friend_msg_content">['
							+ '在线'
							+ ']</div></div>';
					$("[groupid="+ data.fsno+ "] .online").html(
									$("[groupid="+ data.fsno+ "] .online").html()+ friend);
					//subgroup's online number add 1
					$("div#"+data.fsno+" .online_number .f_online").html(parseInt($("div#"+data.fsno+" .online_number .f_online").html())+1);
				} else {//离线
					var friend = '<div class="friend_msg" xf="'+ data.fxf +'" onclick="showChatDetail('+ data.fxf +')"><img class="friend_msg_photo" src="/nzq/photo/'
							+ data.fxf
							+ '.jpg" /><div class="friend_msg_name">'
							+ name
							+ '</div><div class="friend_msg_content">['
							+ '离线'
							+ ']</div></div>';
					$("[groupid="+ data.fsno+ "] .offline").html(
									$("[groupid="+ data.fsno+ "] .offline").html()+ friend);
				}
				//subgroup's total number add 1
				$("div#"+data.fsno+" .online_number .f_total").html(parseInt($("div#"+data.fsno+" .online_number .f_total").html())+1);
		}
	})
}

//8.refuseFriend
function refuseFriend(msg) {
	var xf = msg.substr(1);
//	alert(xf+"拒绝了你的请求");
	$("[application_send_id = " +xf+ "] .app_condition").html("拒绝了你的好友申请");
}
//9.deleteFriend
function deleteFriend(msg) {
	var xf = msg.substr(1);
//	alert(xf+"删除了你");
	showTip($("[xf="+xf+"] .friend_msg_name").html()+" 删除了你");
	removeFriend(xf);
}
function removeFriend(xf) {
	if($("[xf="+xf+"]").parent().attr("class")=="online") {
		var online_num = $("[xf="+xf+"]").parent().parent().parent().children(".group_detail").children(".online_number").children(".f_online").html();
		online_num = online_num * 1 - 1 ;
		$("[xf="+xf+"]").parent().parent().parent().children(".group_detail").children(".online_number").children(".f_online").html(online_num);
	}
	var total_num = $("[xf="+xf+"]").parent().parent().parent().children(".group_detail").children(".online_number").children(".f_total").html();
	total_num = total_num * 1 - 1 ;
	$("[xf="+xf+"]").parent().parent().parent().children(".group_detail").children(".online_number").children(".f_total").html(total_num);
	$("[xf="+xf+"]").remove();
	
}

function createSession(chat_xf) {
	var d = new Date();
	var msg_window = '<div class="msg_window" friendid="' + chat_xf
			+ '" onclick="showChatDetail(' + chat_xf + ')">'
			+ '<img class="friend_photo" src="/nzq/photo/' + chat_xf
			+ '.jpg" />' + '<div class="friend_name">'
			+ $("div[xf = " + chat_xf + "] .friend_msg_name").html() + '</div>'
			+ '<div class="msg_time">'+d.getHours()+":"+d.getMinutes()+'</div>'
			+ '<div class="content"></div>' + '<div class="number">0</div>'
			+ '</div>';
	// alert("2");
	$("#msglist").html($("#msglist").html() + msg_window);
}
function sendMessageAndCreateSession() {
//	 alert("1");
	createSession(chat_xf);
//	 alert("3");
	sendMessage();
	// 创建会话后send1替换send2
	$("#send_button1").css("display", "inline");
	$("#send_button2").css("display", "none");
}
$(document).ready(function() {
	$("#friend").click(function() {
		$("#friendlist").css("display", "inline");
		$("#msg").css({
			"background-color" : "#1296db",
			"color" : "white"
		});
		$("#friend").css({
			"background" : "white",
			"color" : "#1296db"
		});
		$("#addfriend").css("display", "none");
		$("#editgroup").css("display", "inline");
	})
})

$(document).ready(function() {
	$("#msg").click(function() {
		$("#friendlist").css("display", "none");
		$("#friend").css({
			"background-color" : "#1296db",
			"color" : "white"
		});
		$("#msg").css({
			"background" : "white",
			"color" : "#1296db"
		});
		$("#addfriend").css("display", "inline");
		$("#editgroup").css("display", "none");
	})
})
// 退出聊天框
$(document).ready(function() {
	$("#msg_number").click(function() {
		$("[xf_no=" + chat_xf + "]").css("display", "none");
		$("#chat_detail").css("display", "none");
	})
})

$(document).ready(function() {
	$("#addfriend").click(function() {
		$("#search_friend").css("display", "inline");
	})
})

$(document).ready(function() {
	$("#editgroup").click(function() {
		$("#my_groups").html("");
		var group;
		var id;
		var name;
		var group1;
		var groups = document.getElementsByClassName("group_detail");
		for(var i = 0; i < groups.length; i++) {
			group = groups[i];
			id = group.id;
			name = group.getElementsByClassName("group_name")[0].innerHTML;
			if(i==0) {
				group1 = '<div class="mygroup">'
					+'<div class="groupname" mygroupid="'+id+'" onclick="showEditGroupName('+id+')" >'+name+'</div>'
					+'</div>';
			} else {
				group1 = '<div class="mygroup">'
					+'<div class="groupname" mygroupid="'+id+'" onclick="showEditGroupName('+id+')" >'+name+'</div>'
					+'<img class="delete_group" title="删除分组" onclick="showDeleteGroup('+id+')" src="/nzq/img/delete_group.png" />'
					+'</div>';
			}
			
			$("#my_groups").html($("#my_groups").html()+group1);
			console.log(id+" "+name);
		}
		$("#manage_group").css("display","inline");
	})
})

$(document).ready(function() {
	$("#search_friend_return").click(function() {
		$("#search_friend").css("display", "none");
//		var x;
//		for (x in friendGroup)
//		{
//		alert(friendGroup[x].sno+friendGroup[x].sname);
//		}
	})
})

$(document).ready(function() {
	$("#w").click(function() {
		$("#conditon_sex").attr("sex","w");
		$("#w").css({ "background-color": "#1296db", "color": "white" });
		$("#m").css({ "background-color": "white", "color": "black" });
		$("#n").css({ "background-color": "white", "color": "black" });
	})
})
$(document).ready(function() {
	$("#m").click(function() {
		$("#conditon_sex").attr("sex","m");
		$("#m").css({ "background-color": "#1296db", "color": "white" });
		$("#w").css({ "background-color": "white", "color": "black" });
		$("#n").css({ "background-color": "white", "color": "black" });
	})
})
$(document).ready(function() {
	$("#n").click(function() {
		$("#conditon_sex").attr("sex","n");
		$("#n").css({ "background-color": "#1296db", "color": "white" });
		$("#w").css({ "background-color": "white", "color": "black" });
		$("#m").css({ "background-color": "white", "color": "black" });
	})
})
//通过xf查找用户
$(document).ready(function() {
	$("#search_photo").click(function() {
		var xf = $("#search_friend_xf").val();
		if(xf=="") {
			alert("请输入xf号后再点击查询！！！");
			return;
		} else if(!(/^[1-9]\d{4,9}$/.test(xf))) {
			alert("请输入正确的xf号格式！！！");
			return;
		}
		alert(xf);
		
		$.ajax({
			url : "/nzq/GetUserInfoByXf",
			data : {
				xf3 : xf
			},
			method : 'get',
			dataType : 'json', // 很重要!!!. 预期服务器返回的数据类型
			error : function() {
				alert("通过xf号查找好友出现错误!!!");
			},
			success : function(data) {
				if(data==null) {
					//无当前xf号的
					alert("未找到");
				} else {
					//展示搜索到的用户
					//alert("success--用户>nickname："+data.nickname+data.xf+data.sex+data.age+data.grade);
					search_item = '<div class="search_friend_item">'
						+'<div class="search_friend_one" search_friend_id="'+data.xf+'">'
						+'<img class="friend_msg_photo" src="/nzq/photo/'+data.xf+'.jpg" />'
						+'<div class="friend_msg_data">'
						+'<div class="search_friend_msg">'
						+'<div class="search_friend_msg_name">'+data.nickname+'</div>'
						+'<div class="search_friend_msg_xf">('+data.xf+')</div>'
						+'</div>'
						+'<div class="friend_msg_detail">'
						+'<div class="friend_msg_sex_'+data.sex+'">'
						+'<img src="/nzq/img/'+data.sex+'.png" style="width: 10px;height:10px;margin-top: 1px;margin-left: 1px;" /> '+data.age+''
						+'</div>'
						+'<div class="friend_msg_grade">'+data.grade+'</div>'
						+'</div>'
						+'</div>'
						+'</div>'
						+'<button class="add_button" onclick="showAddFriend('+data.xf+')" >添加</button>'
						+'</div>';
//					var search_item = '<div class="search_friend_item">'
//										+'<div class="friend_msg" search_friend_id="'+data.xf+'">'
//										+'<img class="friend_msg_photo" src="/nzq/photo/'+data.xf+'.jpg" />'
//										+'<div class="friend_msg_data">'
//										+'<div class="friend_msg_name">'+data.nickname+'&nbsp;<span style="color:lightslategray">('+data.xf+')</span></div>'
//										+'<div class="friend_msg_detail">'
//										+'<div class="friend_msg_sex_'+data.sex+'">'
//										+'<img src="/nzq/img/'+data.sex+'.png" style="width: 10px;height:10px;margin-top: 1px;margin-left: 1px;" /> '+data.age+''
//										+'</div>'
//										+'<div class="friend_msg_grade">'+data.grade+'</div>'
//										+'</div>'
//										+'</div>'
//										+'</div>'
//										+'<button class="add_button">添加</button>'
//										+'</div>';
					$("#search_friend_result").html(search_item);
				}
			}
		})
	})
})
//通过条件查找用户
$(document).ready(function() {
	$("#condition_button").click(function() {
		showRunning("搜索中");
		var nickname = $("#search_friend_nickname").val();
		var sex = $("#conditon_sex").attr("sex");
		var grade = $("#search_friend_grade").val();
		var myxf = $("#id_container").html();
		$.ajax({
			url : "/nzq/GetUserInfoByCondition",
			data : {
				myxf : myxf,
				nickname : nickname,
				sex : sex,
				grade : grade
			},
			method : 'post',
			dataType : 'json', // 很重要!!!. 预期服务器返回的数据类型
			error : function() {
				endRunning();
				showError();
				alert("通过条件查找好友出现错误!!!");
			},
			success : function(data) {
				if(data==null) {
					//无当前xf号的
					alert("未找到");
				} else {
					endRunning();
					//展示搜索到的用户
					var search_item;
					$("#search_friend_result").html("");
					var add = "";
					$.each(data,function(index) {
						if(data[index].isFriend==0) {
							add = '<button class="add_button" onclick="showAddFriend('+data[index].xf+')" >添加</button>';
						} else {
							add = "";
						}
						search_item = '<div class="search_friend_item">'
							+'<div class="search_friend_one" search_friend_id="'+data[index].xf+'">'
							+'<img class="friend_msg_photo" src="/nzq/photo/'+data[index].xf+'.jpg" />'
							+'<div class="friend_msg_data">'
							+'<div class="search_friend_msg">'
							+'<div class="search_friend_msg_name">'+data[index].nickname+'</div>'
							+'<div class="search_friend_msg_xf">('+data[index].xf+')</div>'
							+'</div>'
							+'<div class="friend_msg_detail">'
							+'<div class="friend_msg_sex_'+data[index].sex+'">'
							+'<img src="/nzq/img/'+data[index].sex+'.png" style="width: 10px;height:10px;margin-top: 1px;margin-left: 1px;" /> '+data[index].age+''
							+'</div>'
							+'<div class="friend_msg_grade">'+data[index].grade+'</div>'
							+'</div>'
							+'</div>'
							+'</div>'
							+add
							+'</div>';
						$("#search_friend_result").html($("#search_friend_result").html()+search_item);
					})
				}
			}
		})
	})
})

//添加好友
$(document).ready(function() {
	$("#add_friend_return").click(function() {
		$("#add_friend").css("display", "none");
	})
})
function showAddFriend(xf) {
	$("#group").html("");
	var group;
	var id;
	var name;
	var group1;
	var groups = document.getElementsByClassName("group_detail");
	for(var i = 0; i < groups.length; i++) {
		group = groups[i];
		id = group.id;
		name = group.getElementsByClassName("group_name")[0].innerHTML;
		$("#group").html($("#group").html()+'<option value="'+id+'">'+name+'</option>');
	}
	$("#add_friend_data").html($("[search_friend_id = "+xf+"]").html());
	$("#add_friend").css("display", "inline");
	$("#remark").val("");
	receive_xf = xf;
	
}
//已废除
//online notice
function sendOnlineNotice() {
	var id = setInterval(function() {
		websocket.send("3"+ $("#id_container").html()+"." + friend_xf);
		clearInterval(id);
	}, 100)
	
}
//已废除
function sendOfflineNotice() {
//	alert(friend_xf);
	websocket.send("4"+ $("#id_container").html()+"." + friend_xf);
}
//send add friend application
$(document).ready(function() {
	$("#add_friend_send").click(function() {
		showRunning("发送请求中");
		var friend_msg = $("#add_friend_data .search_friend_msg .search_friend_msg_name").html();
//		alert(friend_msg);
		var send_xf = $("#id_container").html();
		var text = $("#add_friend_msg").val();
//		alert(text);
		var send_sno = $("#group").val();
		var send_remark = $("#remark").val();
		console.log("数据传递"+send_xf+receive_xf+text+send_sno+send_remark);

		$.ajax({
			url : "/nzq/SendApplication",
			data : {
				send_xf : send_xf,
				receive_xf : receive_xf,
				text : text,
				send_sno : send_sno,
				send_remark : send_remark,
			},
			dataType : 'text', 
			method : 'post',
			error : function() {
				endRunning();
				showError();
				console.log("前台发送好友申请失败");
			},
			success : function(text) {
				if(text=="success") {
					endRunning();
					$("[search_friend_id="+receive_xf+"]").parent().children(".add_button").remove();
					console.log("好友申请存入数据库成功");
					//本地添加申请记录
					addSendApplication(receive_xf,friend_msg);
					websocket.send("5."+send_xf+"."+receive_xf);
				} else {
					endRunning();
					showDBError();
					console.log("好友申请存入数据库失败");
				}
				
			}
		})
		//发送成功后关闭
		$("#add_friend").css("display", "none");
	})
})
function addSendApplication(xf,friend_msg) {
	if($("[application_send_id="+xf+"]").length>0) {
		$("[application_send_id="+xf+"]").remove();
	}
	var app_send = '<div application_send_id="'+xf+'" class="bar" >'
						+'<img src="/nzq/photo/'+xf+'.jpg" class="application_photo" />'
						+'<div class="app_container">'
						+'	<div class="app_name">'+friend_msg+'</div>'
						+'	<div class="app_condition">已发送验证消息</div>'
						+'</div>'
						+'<div class="app_del" onclick="removeSendApplication('+xf+')">删除</div>'
					+'</div>';
	$("#application_send").html($("#application_send").html()+app_send);
}
function addMyApplication(xf,nickname,conditions_text) {
	var app_send = '<div application_send_id="'+xf+'" class="bar" >'
						+'<img src="/nzq/photo/'+xf+'.jpg" class="application_photo" />'
						+'<div class="app_container">'
						+'	<div class="app_name">'+nickname+'</div>'
						+'	<div class="app_condition">'+conditions_text+'</div>'
						+'</div>'
						+'<div class="app_del" onclick="removeSendApplication('+xf+')">删除</div>'
					+'</div>';
	$("#application_send").html($("#application_send").html()+app_send);
}
function addReceiveApplication(send_xf) {
	console.log("添加收到的申请");
	if($("[application_receive_id = "+send_xf+"]").length>0) {
		$("[application_receive_id = "+send_xf+"]").remove();
	}
	var receive_xf = $("#id_container").html();
	$.ajax({
		url : "/nzq/GetApplicationByXf",
		data : {
			send_xf1 : send_xf,
			receive_xf1 : receive_xf
		},
		dataType : 'json', 
		method : 'get',
		error : function() {
			console.log("获取陌生人好友申请失败");
		},
		success : function(data) {
			console.log("获取陌生人好友申请成功");
			if($("[application_receive_id="+send_xf+"]").length>0) {
				$("[application_send_id="+send_xf+"]").remove();
			}
			var app_receive = '<div application_receive_id="'+send_xf+'" class="bar">'
									+'<img src="/nzq/photo/'+send_xf+'.jpg" class="application_photo" />'
									+'<div class="app_container">'
									+'	<div class="app_name">'+data.nickname+'</div>'
									+'	<div class="app_condition">'+data.text+'</div>'
									+'</div>'
									+'<div class="a_r">'
									+'	<div class="accept" onclick="acceptApplication('+send_xf+')">同意</div>'
									+'	<div class="refuse" onclick="refuseApplication('+send_xf+')">拒绝</div>'
									+'</div>'
									+'<div class="app_del" onclick="removeReceiveApplication('+send_xf+')">删除</div>'
								+'</div>';
			$("#application_receive").html($("#application_receive").html()+app_receive);
			
		}
	})
}
function addMyReceiveApplication(send_xf,nickname,text,conditions) {
	var conditions_text = "";
	if(conditions == 0) {
		conditions_text = '<div class="accept" onclick="acceptApplication('+send_xf+')">同意</div>'
							+'<div class="refuse" onclick="refuseApplication('+send_xf+')">拒绝</div>';
	} else if(conditions == 1) {
		conditions_text = "已添加";
	} else if(conditions == 2) {
		conditions_text = "已拒绝";
	}
	
	var app_receive = '<div application_receive_id="'+send_xf+'" class="bar">'
	+'<img src="/nzq/photo/'+send_xf+'.jpg" class="application_photo" />'
	+'<div class="app_container">'
	+'	<div class="app_name">'+nickname+'</div>'
	+'	<div class="app_condition">'+text+'</div>'
	+'</div>'
	+'<div class="a_r">'
	+conditions_text
	+'</div>'
	+'<div class="app_del" onclick="removeReceiveApplication('+send_xf+')">删除</div>'
	+'</div>';
	$("#application_receive").html($("#application_receive").html()+app_receive);
}
function acceptApplication(send_xf1) {
	send_xf = send_xf1;
	console.log(send_xf);
	$("#group1").html("");
	var group;
	var id;
	var name;
	var group1;
	var groups = document.getElementsByClassName("group_detail");
	for(var i = 0; i < groups.length; i++) {
		group = groups[i];
		id = group.id;
		name = group.getElementsByClassName("group_name")[0].innerHTML;
		$("#group1").html($("#group1").html()+'<option value="'+id+'">'+name+'</option>');
	}
	$("#remark1").val("");
	$("#acceptApplication").css("display", "inline");
}
function refuseApplication(send_xf) {
	showRunning("拒绝申请中")
	var myxf = $("#id_container").html();
	$.ajax({
		url : "/nzq/RefuseApplication",
		data : {
			myxf : myxf,
			send_xf : send_xf
		},
		dataType : 'text', 
		method : 'get',
		error : function() {
			endRunning();
			showError();
			console.log("拒绝添加陌生人好友失败");
		},
		success : function(data) {
			if(data=="success") {
				endRunning();
				//显示已拒绝
				$("div[application_receive_id = "+send_xf+"] .a_r").html("已拒绝");
				websocket.send("8."+myxf+"."+send_xf);//通知对方更新好友列表
				console.log("拒绝添加陌生人好友成功");
			} else {
				endRunning();
				showDBError();
			}
			
			
		}
	})
}
$(document).ready(function() {
	$("#accept_quit").click(function() {
		$("#acceptApplication").css("display", "none");
	})
})

$(document).ready(function() {
	$("#accept_confirm").click(function() {
		showRunning("添加好友中")
		var myxf = $("#id_container").html();
		var remark = $("#remark1").val();
		var group = $("#group1").val();
//		alert(myxf+send_xf+remark+group);
		$.ajax({
			url : "/nzq/AcceptApplication",
			data : {
				myxf : myxf,
				send_xf2 : send_xf,
				my_remark : remark,
				my_group : group,
			},
			dataType : 'text', 
			method : 'post',
			error : function() {
				endRunning();
				showError();
				console.log("添加陌生人好友失败");
			},
			success : function(data) {
				if(data=="success") {
					console.log("添加陌生人好友成功");
//					$("#application").css("display", "inline");
					$("div[application_receive_id = "+send_xf+"] .a_r").html("已添加");
					websocket.send("6."+myxf+"."+send_xf);//通知对方更新好友列表
					$("#acceptApplication").css("display", "none");
					
					$.ajax({
						url : "/nzq/AddNewFriend",
						data : {
							myxf3 : myxf,
							xf5 : send_xf
						},
						dataType : 'json', 
						method : 'post',
						error : function() {
							endRunning();
							showError();
							console.log("向好友列表中插入新的好友失败");
						},
						success : function(data) {
								endRunning();
								console.log("向好友列表中插入新的好友成功");
								var name;
								if(data.remark=="") {
									name = data.nickname;
								} else {
									name = data.remark;
								}
								if (data.fstatus == 1) {
									var friend = '<div class="friend_msg" xf="'+ data.fxf +'" onclick="showChatDetail('+ data.fxf +')"><img class="friend_msg_photo" src="/nzq/photo/'
											+ data.fxf
											+ '.jpg" /><div class="friend_msg_name">'
											+ name
											+ '</div><div class="friend_msg_content">['
											+ '在线'
											+ ']</div></div>';
									$("[groupid="+ data.fsno+ "] .online").html(
													$("[groupid="+ data.fsno+ "] .online").html()+ friend);
									//subgroup's online number add 1
									$("div#"+data.fsno+" .online_number .f_online").html(parseInt($("div#"+data.fsno+" .online_number .f_online").html())+1);
								} else {
									var friend = '<div class="friend_msg" xf="'+ data.fxf + '" onclick="showChatDetail('+ data.fxf + ')"><img class="friend_msg_photo" src="/nzq/photo/'
											+ data.fxf
											+ '.jpg" /><div class="friend_msg_name">'
											+ name
											+ '</div><div class="friend_msg_content">['
											+ '离线'
											+ ']</div></div>';
									$("[groupid="+ data.fsno+ "] .offline").html(
													$("[groupid="+ data.fsno+ "] .offline").html()+ friend);
								}
								//subgroup's total number add 1
								$("div#"+data.fsno+" .online_number .f_total").html(parseInt($("div#"+data.fsno+" .online_number .f_total").html())+1);
						}
					})
					
					
				}
				
				
			}
		})
		
	})
})
$(document).ready(function() {
	$("#newfriend").click(function() {
		$("#application").css("display", "inline");
	})
})
//open friendset
$(document).ready(function() {
	$("#f_detail").click(function() {
		$("#friend_note .note").html($("[xf = "+chat_xf+"] .friend_msg_name").html());
		$("#group2").html($("[xf = "+chat_xf+"]").parent().parent().parent().children(".group_detail").children(".group_name").html());
		$("#chat_friendset").css("display", "inline");
	})
})
//close friendset
$(document).ready(function() {
	$("#chat_friendset_return").click(function() {
		$("#chat_friendset").css("display", "none");
	})
})
$(document).ready(function() {
	$("#application_return").click(function() {
		$("#application").css("display", "none");
	})
})
function removeSendApplication(xf){
	$.ajax({
		url : "/nzq/RemoveSendApplication",
		data : {
			myxf6 : $("#id_container").html(),
			friend_xf2 : xf
		},
		method : 'get',
		dataType : 'text', // 很重要!!!. 预期服务器返回的数据类型
		error : function() {
			console.log("删除我的申请失败1");
		},
		success : function(data) {
			if(data=="success") {
				console.log("删除我的申请成功");
				$("[application_send_id="+xf+"]").remove();
			} else {
				console.log("删除我的申请失败2");
			}
			
		}
	})
}
function removeReceiveApplication(xf){
	showRunning("正在删除申请");
	$.ajax({
		url : "/nzq/RemoveReceiveApplication",
		data : {
			myxf7 : $("#id_container").html(),
			friend_xf3 : xf
		},
		method : 'get',
		dataType : 'text', // 很重要!!!. 预期服务器返回的数据类型
		error : function() {
			endRunning();
			showError();
			console.log("删除我收到的申请失败1");
		},
		success : function(data) {
			if(data=="success") {
				endRunning();
				$("[application_receive_id="+xf+"]").remove();
				console.log("删除我收到的申请成功");
			} else {
				endRunning();
				showDBError();
				console.log("删除我收到的申请失败2");
			}
			
		}
	})
}

$(document).ready(function() {
	$("#delete_friend_button").click(function() {
		showRunning("删除好友中");
		var myxf = $("#id_container").html();
		$.ajax({
			url : "/nzq/DeleteFriend",
			data : {
				friend_xf : chat_xf,
				myxf4 : myxf
			},
			method : 'get',
			dataType : 'text', // 很重要!!!. 预期服务器返回的数据类型
			error : function() {
//				console.log("删除好友失败1");
				endRunning();
				showDBError();
			},
			success : function(data) {
				endRunning();
				if(data=="success") {
					removeFriend(chat_xf);//从好友列表删除
					$("[xf_no="+chat_xf+"]").remove();//移除（聊天细节）chat_detail 
					$("#chat_friendset").css("display", "none");//关闭好友设置
					$("#chat_detail").css("display", "none");//关闭聊天页
					websocket.send("9."+myxf+"."+chat_xf);
				} else {
					showError();
//					console.log("删除好友失败2");
				}
			}
		})
	})
})

$(document).ready(function() {
	$("#friend_note").click(function() {
		$("#friend_note").css("display", "none");
		$("#input_note").css("display", "inline");
		$("#remark_input").val($("#friend_note .note").html());
	})
})

$(document).ready(function() {
	$("#remark_confirm").click(function() {
		showRunning("正在修改好友备注");
		$.ajax({
			url : "/nzq/UpdateFriendRemark",
			data : {
				myxf5 : $("#id_container").html(),
				friend_xf1 : chat_xf,
				remark : $("#remark_input").val(),
			},
			method : 'post',
			dataType : 'text', // 很重要!!!. 预期服务器返回的数据类型
			error : function() {
				endRunning();
				showError();
				console.log("修改好友备注失败1");
			},
			success : function(data) {
				if(data=="success") {
					endRunning();
					console.log("修改好友备注成功");
					$("#friend_note .note").html($("#remark_input").val());//静态展示
					$("#f_name").html($("#remark_input").val());//聊天页面
					$("[xf ="+chat_xf+"] .friend_msg_name").html($("#remark_input").val());//好友列表名字 
				} else {
					endRunning();
					showDBError();
					console.log("修改好友备注失败2");
				}
				
			}
			
		})
		$("#friend_note").css("display", "inline");
		$("#input_note").css("display", "none");
	})
})

$(document).ready(function() {
	$("#friend_group").click(function() {
		var g3 = $("[xf = "+chat_xf+"]").parent().parent().parent();
		var groupid = g3.children(".group_detail").attr("id");
		$("#selgroups").html("");
		var group1;
		var group;
		var id;
		var name;
		var groups = document.getElementsByClassName("group_detail");
		for(var i = 0; i < groups.length; i++) {
			group = groups[i];
			id = group.id;
			name = group.getElementsByClassName("group_name")[0].innerHTML;
			if(id==groupid) {
				group1 = '<div selectgroupid="'+id+'" class="selectgroup" onclick="moveToGroup('+id+')">'
						+'<div class="selgroupname">'+name+'</div>'
						+'<img id="nowgroup" src="/nzq/img/dui.png"/>'
						+'</div>';
			} else {
				group1 = '<div selectgroupid="'+id+'" class="selectgroup" onclick="moveToGroup('+id+')">'
						+'<div class="selgroupname">'+name+'</div>'
						+'</div>';
			}
			
			$("#selgroups").html($("#selgroups").html()+group1);
			console.log(id+" "+name);
		}
		
		$("#selectnewgroup").css("display", "inline");
	})
})
$(document).ready(function() {
	$("#selectnewgroup_return").click(function() {
		$("#selectnewgroup").css("display","none");
	})
})
function moveToAnotherGroup(groupid) {
	var status;
	var g3 = $("[xf = "+chat_xf+"]").parent().parent().parent();
	var nowgroupid = g3.children(".group_detail").attr("id");
	$("[selectgroupid = "+groupid+"]").append($("#nowgroup"));//打钩
	$("#group2").html($("[selectgroupid = "+groupid+"] .selgroupname").html());//好友资料更新分组
//	alert($("#nowgroup").parent().html());
	if($("[xf = "+chat_xf+"] .friend_msg_content").html()=="[在线]") {
		$("div#"+groupid+" .online_number .f_online").html(parseInt($("div#"+groupid+" .online_number .f_online").html())+1);
		$("div#"+nowgroupid+" .online_number .f_online").html(parseInt($("div#"+nowgroupid+" .online_number .f_online").html())-1);
		status = ".online";
	} else {
		status = ".offline";
	}
	var father = $("#"+groupid).parent();
	var list = father.children(".panel").children(status);
	//subgroup's total number add 1
	$("div#"+groupid+" .online_number .f_total").html(parseInt($("div#"+groupid+" .online_number .f_total").html())+1);
	$("div#"+nowgroupid+" .online_number .f_total").html(parseInt($("div#"+nowgroupid+" .online_number .f_total").html())-1);
	list.append($("[xf = "+chat_xf+"]"));
	$("#selectnewgroup").css("display","none");
}
function moveToGroup(groupid) {
	showRunning("分组移动中")
	$.ajax({
		url : "/nzq/MoveToGroup",
		data : {
			fxf : chat_xf,
			myxf9 : $("#id_container").html(),
			newgroupid : groupid
		},
		method : 'get',
		dataType : 'text', // 很重要!!!. 预期服务器返回的数据类型
		error : function() {
			endRunning();
			showError();
			console.log("好友移动分组失败1");
		},
		success : function(data) {
			if(data=="success") {
				endRunning();
				moveToAnotherGroup(groupid);
				console.log("好友移动分组成功");
				
			} else {
				endRunning();
				showDBError();
				console.log("好友移动分组失败2");
			}
			
		}
	})
}
/*begin遮罩+个人资料-----------------------------------------------------------------*/
$(document).ready(function() {
	$("#photo").click(function() {
		$("#log_window").css("display", "inline");
		$("#myinfo").css("display", "inline");
	})
})
$(document).ready(function() {
	$("#myinfo_return").click(function() {
		$("#log_window").css("display", "none");
		$("#myinfo").css("display", "none");
	})
})
function copyToEdit() {
	$("#myinfo_name_edit").val($("#myinfo_name").html());
	$("#edit_my_birth").val($("#my_birth").html());
	$("#edit_my_phone").val($("#my_phone").html());
	if($("#my_sex").html() == "女") {
		$("#select_my_sex").val("w");
	} else {
		$("#select_my_sex").val("m");
	}
}
function copyToShow() {
	$("#myinfo_name").html($("#myinfo_name_edit").val());
	$("#my_birth").html($("#edit_my_birth").val());
	$("#my_phone").html($("#edit_my_phone").val());
	if($("#select_my_sex").val() == "w") {
		$("#my_sex").html("女");
	} else {
		$("#my_sex").html("男");
	}
}
function showStatus() {
	$("#myinfo_name").css("display", "inline");
	$("#my_birth").css("display", "inline");
	$("#my_phone").css("display", "inline");
	$("#my_sex").css("display", "inline");

	$(".myinfo_edit").css("display", "none");
	$("#myinfo_name_edit").css("display", "none");
	$("#select_my_sex").css("display", "none");

	$("#myinfo_quit_edit").css("display", "none");
	$("#myinfo_return").css("display", "inline");
	
	$("#myinfo_edit_sign").attr("src", "/nzq/img/edit_sign.png");
}
function editStatus() {
	$(".myinfo_edit").css("display", "inline");
	$("#myinfo_name_edit").css("display", "inline");
	$("#select_my_sex").css("display", "inline");

	$("#myinfo_name").css("display", "none");
	$("#my_birth").css("display", "none");
	$("#my_phone").css("display", "none");
	$("#my_sex").css("display", "none");

	$("#myinfo_return").css("display", "none");
	$("#myinfo_quit_edit").css("display", "inline");
	
	$("#myinfo_edit_sign").attr("src", "/nzq/img/submit_sign.png");
}
$(document).ready(function() {
	$("#myinfo_edit_sign").click(function() {
		if($("#myinfo_edit_sign").attr("src") == "/nzq/img/edit_sign.png") {
			copyToEdit();
			editStatus();
		} else {
			updateMyUserInfoByXf();
		}

	})
})
$(document).ready(function() {
	$("#myinfo_quit_edit").click(function() {
		$("#myinfo_quit_edit").css("display", "none");
		$("#myinfo_return").css("display", "inline");
		showStatus();
	})
})

/*end遮罩+个人资料-----------------------------------------------------------------*/
 laydate({
            elem: '#edit_my_birth'
        });
        
$(document).ready(function() {
	$(".edit_my_phone").blur(function() {
		if(this.value=="") {
					$("#telephone_true").css("background-image","url(../../img/x.png)");
					telephone_state = false;
					$("#phone_err").html("请输入您的手机号码");
				} else if(!(/^1[3|4|5|8][0-9]\d{8}$/.test(this.value))) {
					$("#telephone_true").css("background-image","url(../../img/x.png)");
					telephone_state = false;
					$("#phone_err").html("请输入正确的号码格式");
				} else {
					$("#telephone_true").css("background-image","url(../../img/y.png)");
					telephone_state = true;
					$("#phone_err").html("");
				}
	})
})
$(document).ready(function() {
	$("#edit_my_birth").change(function() {
		judgeBirthday();
	})
})
function judgeBirthday() {
	if($("#edit_my_birth").val()!="") {
		var age = calculate_age($("#edit_my_birth").val());
		if(age == -1) {
			$("#birthday_err_content").html("出生日期超过当前时间");
			$("#my_birthday_err").css("display", "inline");
		} else {
			$("#my_age").html(age);
			$("#my_birthday_err").css("display", "none");
		}
	} else {
		$("#birthday_err_content").html("生日不能为空");
		$("#my_birthday_err").css("display", "inline");
	}
}

$(document).ready(function() {
	$("#edit_my_phone").blur(function() {
		if($("#edit_my_phone").val() == "") {
			$("#phone_err_content").html("手机号不能为空");
			$("#my_phone_err").css("display", "inline");
		} else if(!(/^1[3|4|5|8][0-9]\d{8}$/.test($("#edit_my_phone").val()))) {
			$("#phone_err_content").html("请输入正确格式手机号");
			$("#my_phone_err").css("display", "inline");
		} else {
			$("#my_phone_err").css("display", "none");
		}
	})
})
function calculate_age(strBirthday) {
	console.log("calculate_age");
	var returnAge;
	var strBirthdayArr = strBirthday.split("-");
	var birthYear = strBirthdayArr[0];
	var birthMonth = strBirthdayArr[1];
	var birthDay = strBirthdayArr[2];

	var d = new Date();
	var nowYear = d.getFullYear();
	var nowMonth = d.getMonth() + 1;
	var nowDay = d.getDate();

	if(birthYear>nowYear) {
		return -1;
	} else if(birthYear==nowYear) {
		if(birthMonth>nowMonth) {
			return -1;
		} else if(birthMonth==nowMonth) {
			if(birthDay>nowDay) {
				return -1;
			} else {
				return 0;
			} 
		} else {
			return 0;
		}
	} else {//生日年小于今年
		var ageDiff = nowYear - birthYear;
		if(nowMonth == birthMonth) {
			var dayDiff = nowDay - birthDay; //日之差  
			if(dayDiff < 0) {
				returnAge = ageDiff - 1;
			} else {
				returnAge = ageDiff;
			}
		} else {
			var monthDiff = nowMonth - birthMonth; //月之差  
			if(monthDiff < 0) {
				returnAge = ageDiff - 1;
			} else {
				returnAge = ageDiff;
			}
		}
	}
	console.log("年龄："+returnAge);
	return returnAge; //返回周岁年龄  
	
}
function updateMyUserInfoByXf() {
	showRunning("正在保存资料");//保存图标出现
		$.ajax({
			url : "/nzq/UpdateMyUserInfoByXf",
			data : {
				xf7 : $("#id_container").html(),
				nickname1 : $("#myinfo_name_edit").val(),
				birthday : $("#edit_my_birth").val(),
				sex1 : $("#select_my_sex").val(),
				telephone : $("#edit_my_phone").val()
			},
			method : 'post',
			dataType : 'text', // 很重要!!!. 预期服务器返回的数据类型
			error : function() {
				endRunning();//保存图标消失
				showError();//显示修改失败
				console.log("修改个人信息失败1");
//				showStatus();
			},
			success : function(data) {
				if(data=="success") {
					endRunning();//保存图标消失
					console.log("修改个人信息成功");
					copyToShow();//刷新前台
					showStatus();//切换到展示状态
					//显示修改个人信息成功
				} else {
					endRunning();//保存图标消失
					showDBError();//显示修改失败
					console.log("修改个人信息失败2");
//					showStatus();
				}
			}
		})
}
//begin分组管理-----------------------------------------------------
$(document).ready(function() {
	$("#edit_groupname_menu_quit").click(function() {
		$("#edit_groupname").css("display","none");
	})
})
function showEditGroupName(groupid) {
	edit_group_status = 1;
	edit_group_id = groupid;
	$("#edit_groupname_menu_name").html("编辑分组");
	$("#edit_groupname_menu_input").val($("[mygroupid = "+groupid+"]").html());
	$("#edit_groupname").css("display","inline");
}
function showDeleteGroup(groupid) {
	$("#del_group").css("display","inline");
	edit_group_id = groupid;
}
$(document).ready(function() {
	$("#del_group_quit").click(function() {
		$("#del_group").css("display","none");
	})
})

$(document).ready(function() {
	$("#end_manage_group").click(function() {
		$("#manage_group").css("display","none");
	})
})
$(document).ready(function() {
	$("#add_group_text").click(function() {
		edit_group_status = 0;
		$("#edit_groupname_menu_name").html("添加分组");
		$("#edit_groupname_menu_input").val("");
		$("#edit_groupname").css("display","inline");
	})
})
$(document).ready(function() {
	$("#del_group_confirm").click(function() {
		$("#del_group").css("display","none");
		showRunning("正在删除分组")
		$.ajax({
			url : "/nzq/DeleteGroup",
			data : {
				myxf10 : $("#id_container").html(),
				groupid2 : edit_group_id
			},
			method : 'get',
			dataType : 'text', // 很重要!!!. 预期服务器返回的数据类型
			error : function() {
				endRunning();
				showError();
				console.log("删除分组失败1");
			},
			success : function(data) {
					endRunning();
					groupToGroup(edit_group_id, data)
					console.log("删除分组成功");
			}
		})
	})
})
function refreshGroupName() {
	$("[mygroupid = "+edit_group_id+"]").html($("#edit_groupname_menu_input").val());
	$("#"+edit_group_id+" .group_name").html($("#edit_groupname_menu_input").val());
}
$(document).ready(function() {
	$("#edit_groupname_menu_confirm").click(function() {
		$("#edit_groupname").css("display","none");
		
		if(edit_group_status == 0) {
			//add group
			showRunning("正在添加分组");
			$.ajax({
				url : "/nzq/AddGroup",
				data : {
					myxf8 : $("#id_container").html(),
					groupname1 : $("#edit_groupname_menu_input").val()
					
				},
				method : 'post',
				dataType : 'text', // 很重要!!!. 预期服务器返回的数据类型
				error : function() {
					endRunning();
					showError();
					console.log("失败1");
				},
				success : function(data) {
					//刷新客户端
					endRunning();
					addANewGroup(data, $("#edit_groupname_menu_input").val());
					console.log("新的分组是"+data)
				}
			})
		} else {
			//edit group name
			showRunning("正在修改分组名称");
			if($("#edit_groupname_menu_input").val()!=$("[mygroupid = "+edit_group_id+"]").html()) {
				
				$.ajax({
					url : "/nzq/UpdateGroupName",
					data : {
						groupid0 : edit_group_id,
						groupname0 : $("#edit_groupname_menu_input").val()
					},
					method : 'post',
					dataType : 'text', // 很重要!!!. 预期服务器返回的数据类型
					error : function() {
						endRunning();
						showError();
						console.log("失败1");
					},
					success : function(data) {
						if(data=="success") {
							refreshGroupName();
							endRunning();
							console.log("成功");
						} else {
							endRunning();
							showDBError();
							console.log("失败2");
						}
						
					}
				})
			} else {
				endRunning();
			}	
		}
	})
})
function addANewGroup(groupid, groupname) {
	var newgroup1 = '<div class="mygroup">'
		+'<div class="groupname" mygroupid="'+groupid+'" onclick="showEditGroupName('+groupid+')" >'+groupname+'</div>'
		+'<img class="delete_group" title="删除分组" onclick="showDeleteGroup('+groupid+')" src="/nzq/img/delete_group.png" />'
		+'</div>';
	var newgroup2 = '<div class="group"><div class="group_detail" id="'+groupid+'" onclick="showFriends(this.id)"><div class="group_arrow"><img src="/nzq/img/right.png" style="width: 100%;" imgid="'+groupid+'"></div><div class="group_name">'+groupname+'</div><div class="online_number"><span class="f_online">0</span>/<span class="f_total">0</span></div></div><div class="panel" style="display: none;" groupid="'+groupid+'"><div class="online"></div><div class="offline"></div></div></div>';
	$("#my_groups").html($("#my_groups").html()+newgroup1);
	$("#fl_group").html($("#fl_group").html()+newgroup2);
}
//end分组管理-----------------------------------------------------
//begin提示框----------------------------------------------------
function endRunning() {
	$("#running").css("display","none");
}
function showRunning(title) {
//	console.log("showtime");
	$("#running_title").html(title+"...");
	$("#running").css("display","inline");
}
function showTip(title) {
//	console.log("showtime");
	$("#chatTip").stop(true, true);
	$("#chatTip").html(title);
	$("#chatTip").fadeIn().delay(500).fadeOut();
}
//网络连接错误
function showError() {
	$("#error").slideDown(600).delay(1500).slideUp(600);
}
//数据库错误
function showDBError() {
	$("#db_error").slideDown(600).delay(1500).slideUp(600);
}
function sleep(n) { //n表示的毫秒数
    var start = new Date().getTime();
        while (true)
        	if (new Date().getTime() - start > n)
        		break;
}  
//end提示框----------------------------------------------------
$(document).ready(function() {
	$("#friendinfo_return").click(function() {
		$("#friendinfo").css("display","none");
	})
})
function groupToGroup(g1, g2) {
	var group1 = $("#"+g1).parent();
	var online = group1.children(".panel").children(".online");
	var offline = group1.children(".panel").children(".offline");
	var group2 = $("#"+g2).parent();
	group2.children(".panel").children(".online").html(group2.children(".panel").children(".online").html()+online.html());
	group2.children(".panel").children(".offline").html(group2.children(".panel").children(".offline").html()+offline.html());
	online.html("");
	offline.html("");
	//subgroup's online number add 1
	$("div#"+g2+" .online_number .f_online").html(parseInt($("div#"+g2+" .online_number .f_online").html())+parseInt($("div#"+g1+" .online_number .f_online").html()));

	//subgroup's total number add 1
	$("div#"+g2+" .online_number .f_total").html(parseInt($("div#"+g2+" .online_number .f_total").html())+parseInt($("div#"+g1+" .online_number .f_total").html()));
	$("[mygroupid = "+g1+"]").parent().remove();
	$("#"+g1).parent().remove();
	
}

$(document).ready(function() {
	$("#friend_info").click(function() {
		showRunning("正在获取好友资料");
		$("#friendinfo").css("display","inline");
		$.ajax({
			url : "/nzq/GetMyUserInfoByXf",
			data : {
				xf6 : chat_xf
			},
			method : 'get',
			dataType : 'json', // 很重要!!!. 预期服务器返回的数据类型
			error : function() {
				endRunning();
				showError();
				console.log("通过xf号查找好友出现错误!!!");
			},
			success : function(data) {
				endRunning();
					$("#friendinfo_name").html(data.nickname);
					$("#friendinfo_sex").html(data.sex);
					$("#friendinfo_age").html(data.age);
					$("#friendinfo_birth").html(data.s_birthday);
					$("#friendinfo_phone").html(data.telephone);
					$("#friendinfo_grade").html(data.grade);
					$("#friendinfo_money").html(data.money);
			}
		})
	})
})
//$(document).ready(function() {
//	$("#laydate_ok").click(function() {
//		judgeBirthday();
//	})
//})
//$(document).ready(function() {
//	$("#edit_my_birth").focus(function() {
//		$("#laydate_ok").bind("click", function(){
//			judgeBirthday();
//		});
//	})
//})
//begin测试---------------------------------------------------
$(document).ready(function() {
	$("#bt1").click(function() {
//		alert("aa");
//		alert(document.getElementById("1").innerHTML);
//		alert(document.getElementById("1").parentNode.innerHTML);
//		var father = document.getElementById("1").parentNode;
//		alert(father.getElementById("panel").innerHTML);

//		var grandpa = $("#2").parent();
//		var uncle = grandpa.children(".panel").children(".online");
//		alert(uncle.html());
//		uncle.append($("#10002"));
//		groupToGroup(2, 18);
		removeFriend(10003);

	})
})
$(document).ready(function() {
	$("#bt2").click(function() {
//		addANewGroup("19", "同学");
		var g1 = $("#2").parent();
		var on1 = grandpa1.children(".panel").children(".online").html();
		var of1 = grandpa1.children(".panel").children(".offline").html();
		var g2 = $("#18").parent();
		g2.children(".panel").children(".online").html(grandpa2.children(".panel").children(".online").html()+on1);
		g2.children(".panel").children(".offline").html(grandpa2.children(".panel").children(".offline").html()+of1);
		
		//subgroup's online number add 1
		$("div#"+g2+" .online_number .f_online").html(parseInt($("div#"+g2+" .online_number .f_online").html())+parseInt($("div#"+g1+" .online_number .f_online").html()));

		//subgroup's total number add 1
		$("div#"+g2+" .online_number .f_total").html(parseInt($("div#"+g2+" .online_number .f_total").html())+parseInt($("div#"+g1+" .online_number .f_total").html()));
		
		$("div#"+g1+" .online_number .f_online").html("");
		$("div#"+g1+" .online_number .f_total").html("");
	})
})
$(document).ready(function() {
	$("#bt3").click(function() {
//		alert($("[xf = 10002] .friend_msg_content").html());
//		if($("[xf = 10002] .friend_msg_content").html()=="[在线]") {
//			alert("在线");
//		} else {
//			alert("离线");
//		}
//		var g3 = $("[xf = 10002]").parent().parent().parent();
//		var groupid = g3.children(".group_detail").attr("id");
//		alert(groupid);
//		alert(calculate_age($("#edit_my_birth").val()));
		acceptInvitationSuccess("4.10003.b");
	})
})

//end测试-----------------------------------------------------
$(document).ready(function() {
	$("#home_button").click(function() {
		if($("#chat").css("display") == "none") {
			$("#chat").css("display", "inline");
			$("#phone").css("height","650px");
		} else {
			$("#chat").css("display", "none");
			$("#phone").css("height","50px");
		}
	})
})
