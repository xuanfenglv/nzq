var myxf;
var yaoxf;
var roomId;
var myLocation;
var toLocation;
var Time;
var TimePP;
var TimePz5;
var TimePz4;
var roomModel = 2;
var pPModel = 2;
var invitationModel = 2;
// 判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	// alert('support com.ke.com.websocket');
//	nzqGameWebSocket = new WebSocket("ws://159y3720p2.imwork.net/nzq/nzqgameserver");
// 	nzqGameWebSocket = new WebSocket("ws://localhost:8080/nzq/nzqgameserver");
	// nzqGameWebSocket = new WebSocket("ws://192.168.199.187:8080/nzq/nzqgameserver");
} else {
	alert('Not support com.ke.com.websocket');
}

//--------------------------------------------------------------------
//2
function getInvitation2(msg) {
	$("#yaoqing").css("display", "none");
	$("#yaoqing_tip_model").html("双人五子棋");
	invitationModel = 2;
	getInvitaterMsg(msg);
}
//18
function getInvitation3(msg) {
	$("#yaoqing_tip_model").html("三人四子棋");
	invitationModel = 3;
	getInvitaterMsg(msg);
}

function getInvitaterMsg(msg) {
	var fxf_roomId = msg.substr(3).split(".");
	var fxf = fxf_roomId[0];
	roomId = fxf_roomId[1];
	yaoxf = fxf;
	$.ajax({
		url : "/nzq/GetUserInfoByXf1",
		data : {
			fxf2 : fxf,
			myxf12 :myxf
		},
		method : 'get',
		dataType : 'json', // 很重要!!!. 预期服务器返回的数据类型
		error : function() {
			console.log("失败");
		},
		success : function(data) {
			console.log("成功");
			var grade = data.grade.substring(0,2);
			var gradeImgName = getGradeImgName(grade);
			$("#yaoqing_grade_img").attr("src","/nzq/grade/"+gradeImgName+".jpg");
			if(data.remark=="") {
				name = data.nickname;
			} else {
				name = data.remark;
			}
			if(data.sex=="m") {
				$("#yaoqing_sex").attr("src","/nzq/img/male.png");
			} else {
				$("#yaoqing_sex").attr("src","/nzq/img/female.png");
			}
			$("#yaoqing_nickname").html(name);
			$("#yaoqing_grade_text").html(data.grade);
			$("#yaoqing_photo").attr("src","/nzq/photo/"+fxf+".jpg");
			$("#yaoqing").css("display", "inline");
		}
	})
}
function getGradeImgName(grade) {
	switch(grade) {
	case "青铜":
		return "qingtong";
	case "白银":
		return "baiyin";
	case "黄金":
		return "huangjin";
	case "铂金":
		return "bojin";
	case "钻石":
		return "zuanshi";
	case "最强":
		return "wangzhe";
	}
}
//3
function acceptInvitation2(msg) {
	var flag = msg.charAt(3);
	switch (flag) {
	case "1":
		friendAcceptMyInvitation(msg);
		break;
	case "2":
		iInRoom(msg);
		break;
	case "3":
		roomFull();
		break;
	case "4":
		roomNotExist();
		break;
	}
}
//19
function acceptInvitation3(msg) {
	console.log("acceptInvitation3");
	var flag = msg.charAt(3);
	switch (flag) {
	case "1":
//		console.log("有人加入");
		friendAcceptInvitation3(msg);
		break;
	case "2":
//		console.log("我加入");
		iInRoom3(msg);
		break;
	case "3":
		roomFull();
		break;
	case "4":
		roomNotExist();
		break;
	}
}
function friendAcceptInvitation3(msg) {
	if(true) {
		$("#gy_start").attr("disabled", false);
	}
	var fxf_loc = msg.substr(5).split(".");
	if(fxf_loc[1] == "b") {
		console.log("黑方加入");
		$("#gy3_competitor_img_black .gy3_house_owner").css("display", "none");
		$("#gy3_competitor_img_black .gy3_exchange").css("display", "inline");
		if($("#gy3_competitor_img_"+myLocation+" .gy3_house_owner").css("display")!="none") {
			$("#gy3_competitor_img_black .gy3_exit").css("display", "inline");
		}
		
		$("#gy3_competitor_black .gy3_competitor_photo").attr("src","/nzq/photo/"+fxf_loc[0]+".jpg");
		$("#gy3_competitor_black .gy3_competitor_name").html($("[xf="+fxf_loc[0]+"] .friend_msg_name").html());
		$("#gy3_competitor_black").css("display", "inline");
	} else if(fxf_loc[1] == "w") {
		console.log("白方加入");
		$("#gy3_competitor_img_white .gy3_house_owner").css("display", "none");
		$("#gy3_competitor_img_white .gy3_exchange").css("display", "inline");
		if($("#gy3_competitor_img_"+myLocation+" .gy3_house_owner").css("display")!="none") {
			$("#gy3_competitor_img_white .gy3_exit").css("display", "inline");
		}
		console.log("白方xf:"+fxf_loc[0]);
		console.log("白方name:"+$("[xf="+fxf_loc[0]+"] .friend_msg_name").html());
		$("#gy3_competitor_white .gy3_competitor_photo").attr("src","/nzq/photo/"+fxf_loc[0]+".jpg");
		$("#gy3_competitor_white .gy3_competitor_name").html($("[xf="+fxf_loc[0]+"] .friend_msg_name").html());
		$("#gy3_competitor_white").css("display", "inline");
	} else if(fxf_loc[1] == "r") {
		$("#gy3_competitor_img_red .gy3_house_owner").css("display", "none");
		$("#gy3_competitor_img_red .gy3_exchange").css("display", "inline");
		if($("#gy3_competitor_img_"+myLocation+" .gy3_house_owner").css("display")!="none") {
			$("#gy3_competitor_img_red .gy3_exit").css("display", "inline");
		}
		
		$("#gy3_competitor_red .gy3_competitor_photo").attr("src","/nzq/photo/"+fxf_loc[0]+".jpg");
		$("#gy3_competitor_red .gy3_competitor_name").html($("[xf="+fxf_loc[0]+"] .friend_msg_name").html());
		$("#gy3_competitor_red").css("display", "inline");
	}
}
function friendAcceptMyInvitation(msg) {
	$("#gy_start").attr("disabled", false);
	var fxf = msg.substr(5);
	if($("#gy_competitor_white").css("display")=="none") {
		$("#white_house_owner").css("display", "none");
		$("#change_white").css("display", "inline");
		$("#tick_white").css("display", "inline");
		$("#gy_competitor_white .gy_competitor_photo").attr("src","/nzq/photo/"+fxf+".jpg");
//		alert("他的名字是："+$("[xf="+fxf+"] .friend_msg_name").html());
		$("#gy_competitor_white .gy_competitor_name").html($("[xf="+fxf+"] .friend_msg_name").html());
		$("#gy_competitor_white").css("display", "inline");
	} else {
		$("#black_house_owner").css("display", "none");
		$("#change_black").css("display", "inline");
		$("#tick_black").css("display", "inline");
		$("#gy_competitor_black .gy_competitor_photo").attr("src","/nzq/photo/"+fxf+".jpg");
		$("#gy_competitor_black .gy_competitor_name").html($("[xf="+fxf+"] .friend_msg_name").html());
		$("#gy_competitor_black").css("display", "inline");
	}
}

function iInRoom(msg) {
	var xf_loc = msg.substr(5).split(".");
	$(".gy3_exit").css("display", "none");
	getInvitationList();
	if(xf_loc[1]=="w") {
		myLocation ="white";
//		$("#tick_black").css("display", "none");
		$("#white_house_owner").css("display", "none");
		$("#tick_white").css("display", "none");
		$("#tick_black").css("display", "none");
		$("#change_white").css("display", "none");
		
		$("#black_house_owner").css("display", "inline");
		$("#change_black").css("display", "inline");
		
		$("#gy_competitor_black .gy_competitor_photo").attr("src","/nzq/photo/"+xf_loc[0]+".jpg");
		$("#gy_competitor_black .gy_competitor_name").html($("[xf="+xf_loc[0]+"] .friend_msg_name").html());
		
		$("#gy_competitor_white .gy_competitor_photo").attr("src","/nzq/photo/"+myxf+".jpg");
		$("#gy_competitor_white .gy_competitor_name").html($("#myinfo_name").html());
	} else {
		myLocation ="black";
//		$("#tick_white").css("display", "none");
		$("#black_house_owner").css("display", "none");
		$("#tick_black").css("display", "none");
		$("#tick_white").css("display", "none");
		$("#change_black").css("display", "none");
		
		$("#white_house_owner").css("display", "inline");
		
		
		
		$("#change_white").css("display", "inline");
		
		$("#gy_competitor_white .gy_competitor_photo").attr("src","/nzq/photo/"+xf_loc[0]+".jpg");
		$("#gy_competitor_white .gy_competitor_name").html($("[xf="+xf_loc[0]+"] .friend_msg_name").html());
		
		$("#gy_competitor_black .gy_competitor_photo").attr("src","/nzq/photo/"+myxf+".jpg");
		$("#gy_competitor_black .gy_competitor_name").html($("#myinfo_name").html());
	}
	$("#gy_start").css("display", "none");
	$("#gy_competitor_white").css("display", "inline");
	$("#game_yue2_competitor").css("display", "inline");
	$("#game_yue_competitor").css("display", "inline");
	roomModel = 2;//房主退出后，我也可以邀请别人进
}

function iInRoom3(msg) {
//	console.log("我加入开始");
	var fxf_loc = msg.substr(7).split(".");
	console.log(fxf_loc[0]+"----"+fxf_loc[1]+"----"+fxf_loc[2]+"----");
	var myLoc = msg.charAt(5);
	$(".gy3_exit").css("display", "none");//肯定不能踢人
	$(".gy3_house_owner").css("display", "none");
	$(".gy3_exchange").css("display", "none");
	$(".gy3_competitor_detail").css("display", "none");
	getInvitationList();
	if(myLoc=="b") {
		myLocation ="black";
		$("#gy3_competitor_black").css("display", "inline");
		$("#gy3_competitor_black .gy3_competitor_photo").attr("src","/nzq/photo/"+myxf+".jpg");
		$("#gy3_competitor_black .gy3_competitor_name").html($("#myinfo_name").html());
	} else if(myLoc=="w") {
		console.log("我是白色");
		myLocation ="white";
		$("#gy3_competitor_white").css("display", "inline");
		$("#gy3_competitor_white .gy3_competitor_photo").attr("src","/nzq/photo/"+myxf+".jpg");
		$("#gy3_competitor_white .gy3_competitor_name").html($("#myinfo_name").html());
	} else if(myLoc=="r") {
		myLocation ="red";
		$("#gy3_competitor_red").css("display", "inline");
		$("#gy3_competitor_red .gy3_competitor_photo").attr("src","/nzq/photo/"+myxf+".jpg");
		$("#gy3_competitor_red .gy3_competitor_name").html($("#myinfo_name").html());
	}
	//房主
	$("#gy3_competitor_img_"+fxf_loc[0]+" .gy3_house_owner").css("display", "inline");
	//至少有一人
	$("#gy3_competitor_img_"+fxf_loc[2]+" .gy3_exchange").css("display", "inline");
	$("#gy3_competitor_"+fxf_loc[2]).css("display", "inline");
	$("#gy3_competitor_"+fxf_loc[2]+" .gy3_competitor_photo").attr("src","/nzq/photo/"+fxf_loc[1]+".jpg");
	$("#gy3_competitor_"+fxf_loc[2]+" .gy3_competitor_name").html($("[xf="+fxf_loc[1]+"] .friend_msg_name").html());
	console.log("长度"+fxf_loc.length);
	if(fxf_loc.length==5) {
		$("#gy3_competitor_img_"+fxf_loc[4]+" .gy3_exchange").css("display", "inline");
		$("#gy3_competitor_"+fxf_loc[4]).css("display", "inline");
		$("#gy3_competitor_"+fxf_loc[4]+" .gy3_competitor_photo").attr("src","/nzq/photo/"+fxf_loc[3]+".jpg");
		$("#gy3_competitor_"+fxf_loc[4]+" .gy3_competitor_name").html($("[xf="+fxf_loc[3]+"] .friend_msg_name").html());
	}
	$("#gy3_start").css("display", "none");
	$("#game_yue3_competitor").css("display", "inline");
	$("#game_yue_competitor").css("display", "inline");
	roomModel = 3;//我也可以邀请
}

function roomFull() {
//	alert("房间已满员");
	$("#game_warn").html("无法加入房间：房间已满");
	$("#game_warn").fadeIn().delay(500).fadeOut();
}

function gameWarn(text) {
//	alert("房间已满员");
	$("#game_warn").html(text);
	$("#game_warn").fadeIn().delay(500).fadeOut();
}

function roomNotExist() {
//	alert("房间已消失");
	$("#game_warn").html("无法加入房间：房间不存在");
	$("#game_warn").fadeIn().delay(500).fadeOut();
}

//4
function changeLocation() {
	if($("#gy_competitor_white").css("display")=="none") {
		moveToWhite();
		myLocation = "white";
	} else {
		moveToBlack();
		myLocation = "black";
	}
}
//06
function refuseMyInvitation(msg) {
	var fxf = msg.substr(3);
	var fxf_name = $("[xf="+fxf+"] .friend_msg_name").html();
	gameWarn(fxf_name+" 拒绝了您的邀请");
}
//20
function changeLocation3(msg) {
	if(msg.length==2) {
		exchangeGY3detail();
	} else {
		exchangeAnotherAndNull(msg)
	}
	
}
//21
function refuseMyInvitation(msg) {
	var fxf = msg.substr(3);
	var fxf_name = $("[xf="+fxf+"] .friend_msg_name").html();
	gameWarn(fxf_name+" 拒绝了您的邀请");
}
//22
function friendExist3(msg) {
	var existFriend = msg.substr(3)
	var theLast;
	$("#gy3_competitor_img_"+existFriend+" .gy3_exchange").css("display", "none");
	$("#gy3_competitor_img_"+existFriend+" .gy3_exit").css("display", "none");
	$("#gy3_competitor_img_"+existFriend+" .gy3_house_owner").css("display", "none");
	$("#gy3_competitor_"+existFriend).css("display", "none");
	if(myLocation!="black"&&existFriend!="black") {
		theLast = "black";
	}
	if(myLocation!="white"&&existFriend!="white") {
		theLast = "white";
	}
	if(myLocation!="red"&&existFriend!="red") {
		theLast = "red";
	}
	if($("#gy_competitor_"+theLast).css("display")=="none") {
		$("#gy3_start").css("display","inline");
	}
	
}
//23
function tickOut3() {
	$("#game_yue_competitor").css("display","none");
	$("#game_yue3_competitor").css("display","none");
//	alert("你被房主踢出了房间");
	$("#game_warn").html("你被房主踢出了房间");
	$("#game_warn").fadeIn().delay(500).fadeOut();
}
//24
function ReceiveRequestChangeLocation3(msg) {
	var duifang = msg.substr(3);
	$(".gy3_exchange").css("display", "none");
	$("#gy3_ex"+duifang+"_count").html("5");
	$("#gy3_exchange_request_"+duifang).css("display", "inline");
//	alert(duifang);
	Time1=setInterval(function(){
//		console.log("miao:"+$("#gy3_black_count").html());
				$("#gy3_ex"+duifang+"_count").html($("#gy3_ex"+duifang+"_count").html()*1-1);
				if($("#gy3_ex"+duifang+"_count").html()=="0") {
					$("#gy3_exchange_request_"+duifang).css("display", "none");
					showOtherExchange();
					clearInterval(Time1);
				}
			}, 1000);
}
function showOtherExchange() {
//	alert("showex:"+myLocation);
	if(myLocation == "black") {
		if($("#gy3_competitor_white").css("display")!="none") {
			$("#gy3_competitor_img_white .gy3_exchange").css("display", "inline");
		}
		if($("#gy3_competitor_red").css("display")!="none") {
			$("#gy3_competitor_img_red .gy3_exchange").css("display", "inline");
		}
	} else if(myLocation == "white") {
		if($("#gy3_competitor_black").css("display")!="none") {
			$("#gy3_competitor_img_black .gy3_exchange").css("display", "inline");
		}
		if($("#gy3_competitor_red").css("display")!="none") {
			$("#gy3_competitor_img_red .gy3_exchange").css("display", "inline");
		}
	} else {
		if($("#gy3_competitor_white").css("display")!="none") {
			$("#gy3_competitor_img_white .gy3_exchange").css("display", "inline");
		}
		if($("#gy3_competitor_black").css("display")!="none") {
			$("#gy3_competitor_img_black .gy3_exchange").css("display", "inline");
		}
	}
}
//25
function changeLocationBetweenTwoPlayer3(msg) {
	var result = msg.charAt(3);
	$(".gy3_competitor_cover").css("display", "none");
	clearInterval(Time);
	if(result=="0") {
		gameWarn("被请求方拒绝换位");
		showOtherExchange();
	} else if(result=="1") {
		toLocation = msg.substr(5);
//		alert("qu"+toLocation);
		exchange22GY3detail();
		showOtherExchange();
	} else {
		ex2 = msg.substr(5).split(".");
		exchange2GY3detail(ex2);
	}
	
}
//7
function friendExist() {
	if(myLocation == "black") {
		$("#white_house_owner").css("display", "none");
		$("#change_white").css("display", "none");
		
		$("#gy_competitor_white").css("display", "none");
		$("#black_house_owner").css("display", "inline");
	} else {
		$("#black_house_owner").css("display", "none");
		$("#change_black").css("display", "none");
		
		$("#gy_competitor_black").css("display", "none");
		$("#white_house_owner").css("display", "inline");
	}
	$("#gy_start").css("display","inline");
	$("#gy_start").attr("disabled", true);
}
//08
function tickOut() {
	$("#game_yue_competitor").css("display","none");
	$("#game_yue2_competitor").css("display","none");
//	alert("你被房主踢出了房间");
	$("#game_warn").html("你被房主踢出了房间");
	$("#game_warn").fadeIn().delay(500).fadeOut();
}
//09
function ReceiveRequestChangeLocation() {
	var duifang;
	if(myLocation=="white") {
		$("#gy_exchange_request_black").css("display", "inline");
		$("#change_black").css("display", "none");
		duifang = "black";
	} else {
		$("#gy_exchange_request_white").css("display", "inline");
		$("#change_white").css("display", "none");
		duifang = "white";
	}
	
	$("#gy_ex"+duifang+"_count").html("5");
	$("#gy_exchange_request_"+duifang).css("display", "inline");
//	alert(duifang);
	Time1=setInterval(function(){
//		console.log("miao:"+$("#gy3_black_count").html());
				$("#gy_ex"+duifang+"_count").html($("#gy_ex"+duifang+"_count").html()*1-1);
				if($("#gy_ex"+duifang+"_count").html()=="0") {
					$("#gy_exchange_request_"+duifang).css("display", "none");
					$("#change_"+duifang).css("display", "inline");
					clearInterval(Time1);
				}
			}, 1000);
	
}
//10
function changeLocationBetweenTwoPlayerDealer(msg) {
	var flag = msg.charAt(3);
	clearInterval(Time);
	if(flag == "1") {
		changeLocationBetweenTwoPlayer();
	} else {
		if(myLocation == "black") {
			$("#change_white").css("display", "inline");
			$("#gy_white_cover").css("display", "none");
		} else {
			$("#change_black").css("display", "inline");
			$("#gy_black_cover").css("display", "none");
		}
		gameWarn("被请求方拒绝换位");
	}
	
}
function changeLocationBetweenTwoPlayer() {
	exchangeGYdetail();
	if($("#black_house_owner").css("display")=="none") {//white是房主
		if(myLocation=="white") {
			$("#gy_black_cover").css("display", "none");
			
			$("#tick_black").css("display", "none");
			
			$("#tick_white").css("display", "inline");
			
			$("#change_black").css("display", "none");
			$("#change_white").css("display", "inline");
		} else {
			$("#gy_white_cover").css("display", "none");
			
			$("#change_black").css("display", "inline");
			$("#change_white").css("display", "none");
		}
		

		$("#black_house_owner").css("display","inline");
		$("#white_house_owner").css("display","none");
	} else {
		if(myLocation=="black") {
			$("#gy_white_cover").css("display", "none");
			
			$("#tick_white").css("display", "none");
			
			$("#tick_black").css("display", "inline");
			
			$("#change_black").css("display", "inline");
			$("#change_white").css("display", "none");
		} else {
			$("#gy_black_cover").css("display", "none");
			
			$("#change_black").css("display", "none");
			$("#change_white").css("display", "inline");
		}
		
		$("#black_house_owner").css("display","none");
		$("#white_house_owner").css("display","inline");
	}
	if(myLocation=="white") {
		myLocation="black";
	} else {
		myLocation="white";
	}
}
//11
function beginGy5() {
	$("#friend_2").css("display", "none");
	if(myLocation == "black") {
		myOrder = 0;
		var src = $("#gy_competitor_white .gy_competitor_photo").attr("src");
		$("#player1_photo").attr("src",src);
		$("#player1_name").html($("#gy_competitor_white .gy_competitor_name").html());
		
		src = $("#gy_competitor_black .gy_competitor_photo").attr("src");
		$("#player3_photo").attr("src",src);
		$("#player3_name").html($("#gy_competitor_black .gy_competitor_name").html());
		
		daojishi3();
	} else {
		myOrder = 1;
		var src = $("#gy_competitor_black .gy_competitor_photo").attr("src");
		$("#player1_photo").attr("src",src);
		$("#player1_name").html($("#gy_competitor_black .gy_competitor_name").html());
		
		src = $("#gy_competitor_white .gy_competitor_photo").attr("src");
		$("#player3_photo").attr("src",src);
		$("#player3_name").html($("#gy_competitor_white .gy_competitor_name").html());
		daojishi1();
	}
	$("#inner_chat_record").html("");
	$("#wuziqi_game").css("display", "inline");
	$("#game_yue_competitor2").css("display", "none");
	$("#game_yue_competitor").css("display", "none");
	
//	$("#yaoqing").css("display", "none");
}
function beginGy4() {
	$("#friend_2").css("display", "inline");
	if(myLocation == "black") {
		myOrder = 0;
		var src = $("#gy3_competitor_white .gy3_competitor_photo").attr("src");
		$("#player1_photo").attr("src",src);
		$("#player1_name").html($("#gy3_competitor_white .gy3_competitor_name").html());
		
		src = $("#gy3_competitor_red .gy3_competitor_photo").attr("src");
		$("#player2_photo").attr("src",src);
		$("#player2_name").html($("#gy3_competitor_black .gy3_competitor_name").html());
		
		src = $("#gy3_competitor_black .gy3_competitor_photo").attr("src");
		$("#player3_photo").attr("src",src);
		$("#player3_name").html($("#gy3_competitor_black .gy3_competitor_name").html());
		
		daojishi3();
	} else if(myLocation == "white") {
		myOrder = 1;
		var src = $("#gy3_competitor_black .gy3_competitor_photo").attr("src");
		$("#player1_photo").attr("src",src);
		$("#player1_name").html($("#gy3_competitor_black .gy3_competitor_name").html());
		
		src = $("#gy3_competitor_red .gy3_competitor_photo").attr("src");
		$("#player2_photo").attr("src",src);
		$("#player2_name").html($("#gy3_competitor_red .gy3_competitor_name").html());
		
		src = $("#gy3_competitor_white .gy3_competitor_photo").attr("src");
		$("#player3_photo").attr("src",src);
		$("#player3_name").html($("#gy3_competitor_white .gy3_competitor_name").html());
		daojishi1();
	} else {
		myOrder = 2;
		var src = $("#gy3_competitor_black .gy3_competitor_photo").attr("src");
		$("#player1_photo").attr("src",src);
		$("#player1_name").html($("#gy3_competitor_black .gy3_competitor_name").html());
		
		src = $("#gy3_competitor_white .gy3_competitor_photo").attr("src");
		$("#player2_photo").attr("src",src);
		$("#player2_name").html($("#gy3_competitor_white .gy3_competitor_name").html());
		
		src = $("#gy3_competitor_red .gy3_competitor_photo").attr("src");
		$("#player3_photo").attr("src",src);
		$("#player3_name").html($("#gy3_competitor_red .gy3_competitor_name").html());
		
		daojishi1();
	}
	$("#inner_chat_record").html("");
	$("#wuziqi_game").css("display", "inline");
	$("#game_yue_competitor2").css("display", "none");
	$("#game_yue_competitor").css("display", "none");
	
//	$("#yaoqing").css("display", "none");
}
function beginPp5() {
	roomModel ==3;
	if(myLocation == "black") {
		myOrder = 0;
		var src = $("#pz2_white_photo").attr("src");
		$("#player1_photo").attr("src",src);
		
		src = $("#pz2_black_photo").attr("src");
		$("#player3_photo").attr("src",src);
		daojishi3();
	} else {
		myOrder = 1;
		var src = $("#pz2_black_photo").attr("src");
		$("#player1_photo").attr("src",src);
		
		src = $("#pz2_white_photo").attr("src");
		$("#player3_photo").attr("src",src);
		daojishi1();
	}
	$("#inner_chat_record").html("");
	$("#friend_2").css("display", "none");
	$("#wuziqi_game").css("display", "inline");
}
function beginPp4() {
	roomModel = 3;
	if(myLocation == "black") {
		myOrder = 0;
		var src = $("#pz3_white_photo").attr("src");
		$("#player1_photo").attr("src",src);
		
		src = $("#pz3_red_photo").attr("src");
		$("#player2_photo").attr("src",src);
		
		src = $("#pz3_black_photo").attr("src");
		$("#player3_photo").attr("src",src);
		daojishi3();
	} else if(myLocation == "white") {
		myOrder = 1;
		var src = $("#pz3_black_photo").attr("src");
		$("#player1_photo").attr("src",src);
		
		src = $("#pz3_red_photo").attr("src");
		$("#player2_photo").attr("src",src);
		
		src = $("#pz3_white_photo").attr("src");
		$("#player3_photo").attr("src",src);
		daojishi1();
	} else {
		myOrder = 2;
		var src = $("#pz3_black_photo").attr("src");
		$("#player1_photo").attr("src",src);
		
		src = $("#pz3_white_photo").attr("src");
		$("#player2_photo").attr("src",src);
		
		src = $("#pz3_red_photo").attr("src");
		$("#player3_photo").attr("src",src);
		daojishi1();
	}
	$("#inner_chat_record").html("");
	$("#friend_2").css("display", "inline");
	$("#wuziqi_game").css("display", "inline");
}
//12
function drop5(msg) {
	var r_c = msg.substr(2).split(".");
	var row = parseInt(r_c[0]);
	var column = parseInt(r_c[1]);
	dropIn5(row, column);
}
//27
function drop4(msg) {
	var r_c = msg.substr(2).split(".");
	var row = parseInt(r_c[0]);
	var column = parseInt(r_c[1]);
	dropIn4(row, column);
}
//28
function receiveInnerMsg3(msg) {
	var innerMsg = msg.substr(3);
	addFriendInnerMsg3(innerMsg);
}
//13
function receiveInnerMsg(msg) {
	var innerMsg = msg.substr(2);
	addFriendInnerMsg(innerMsg);
}
//14
function receiveEmoticon(msg) {
	var emoid = msg.substr(2);
	showReceiveEmoticon(emoid);
}
//15
function receiveVoice(msg) {
	var voiceid = msg.substr(2);
	playVoice(voiceid);
}
//29
function receiveEmoticon3(msg) {
	var loc_emoid = msg.substr(3).split(".");
	showReceiveEmoticon3(loc_emoid);
}
//30
function receiveVoice3(msg) {
	var loc_voiceid = msg.substr(3).split(".");
	playVoice3(loc_voiceid);
}
//32
function enterMatchPreparement(msg) {
	$("#match_pipei_wait").css("display", "none");
	clearInterval(TimePP);
	var l_xf = msg.substr(2).split(".");
	$(".pz2_photo_cover").css("display", "inline");
	$("#pz2_number").html("0");
	if(l_xf[0] == "black") {
		$("#pz2_black_photo").attr("src","/nzq/photo/"+myxf+".jpg");
		$("#pz2_white_photo").attr("src","/nzq/photo/"+l_xf[1]+".jpg");
		myLocation = "black";
	} else {
		$("#pz2_black_photo").attr("src","/nzq/photo/"+l_xf[1]+".jpg");
		$("#pz2_white_photo").attr("src","/nzq/photo/"+myxf+".jpg");
		myLocation = "white";
	}
	$("#match_ready_pz2").css("display", "inline");
	$("#pz2_count").html("30");
	TimePz5=setInterval(function(){
		$("#pz2_count").html($("#pz2_count").html()*1-1);
		if($("#pz2_count").html()=="0") {
			nzqGameWebSocket.send("34");
			$("#match_ready_pz2").css("display", "none");
			clearInterval(TimePz5);
			gameWarn("有玩家未确认，退出到大厅");
			//销毁房间
		}
	}, 1000);
}
//35
function enterMatch4Preparement(msg) {
	$("#match_pipei_wait").css("display", "none");
	clearInterval(TimePP);
	var l_xf = msg.substr(2).split(".");
	myLocation = l_xf[0];
	$(".pz3_photo_cover").css("display", "inline");
	$("#pz3_number").html("0");
	if(l_xf[0] == "black") {
		$("#pz3_black_photo").attr("src","/nzq/photo/"+myxf+".jpg");
		$("#pz3_white_photo").attr("src","/nzq/photo/"+l_xf[1]+".jpg");
		$("#pz3_red_photo").attr("src","/nzq/photo/"+l_xf[2]+".jpg");
		myLocation = "black";
	} else if(l_xf[0] == "white") {
		$("#pz3_black_photo").attr("src","/nzq/photo/"+l_xf[1]+".jpg");
		$("#pz3_white_photo").attr("src","/nzq/photo/"+myxf+".jpg");
		$("#pz3_red_photo").attr("src","/nzq/photo/"+l_xf[2]+".jpg");
		myLocation = "white";
	} else {
		$("#pz3_black_photo").attr("src","/nzq/photo/"+l_xf[1]+".jpg");
		$("#pz3_white_photo").attr("src","/nzq/photo/"+l_xf[2]+".jpg");
		$("#pz3_red_photo").attr("src","/nzq/photo/"+myxf+".jpg");
	}
	$("#match_ready_pz3").css("display", "inline");
	$("#pz3_count").html("30");
	TimePz4=setInterval(function(){
		$("#pz3_count").html($("#pz3_count").html()*1-1);
		if($("#pz3_count").html()=="0") {
			nzqGameWebSocket.send("37");
			$("#match_ready_pz3").css("display", "none");
			clearInterval(TimePz4);
			gameWarn("有玩家未确认，退出到大厅");
			//销毁房间
		}
	}, 1000);
}
//33
function match5Confirm() {
	if(myLocation == "black") {
		$("#pz2_white .pz2_photo_cover").css("display", "none");
	} else {
		$("#pz2_black .pz2_photo_cover").css("display", "none");
	}
	$("#pz2_number").html($("#pz2_number").html()*1 + 1);
	if($("#pz2_number").html()=="2") {
		//开
		clearInterval(TimePz5);
		$("#match_ready_pz2").css("display", "none");
		//alert("开始");
		beginPp5();
	}
	
}
//36
function match4Confirm(msg) {
	var floc = msg.substr(2);
	if(floc == "black") {
		$("#pz3_black .pz3_photo_cover").css("display", "none");
	}  else if(floc == "white") {
		$("#pz3_white .pz3_photo_cover").css("display", "none");
	} else {
		$("#pz3_red .pz3_photo_cover").css("display", "none");
	}
	$("#pz3_number").html($("#pz3_number").html()*1 + 1);
	if($("#pz3_number").html()=="3") {
		//开
		clearInterval(TimePz4);
		$("#match_ready_pz3").css("display", "none");
		//alert("开始");
		beginPp4();
	}
	
}
//--------------------------------------------------------------------

$(document).ready(function() {
 	$("#play_b").click(function() {
		$("#homepage").css("display", "none");
		$("#person").css("display", "none");
		$("#commodity").css("display", "none");
		$("#store").css("display", "none");

		$("#play").css("display", "inline");
	})
})

$(document).ready(function() {
	$("#homepage_b").click(function() {
//		$("#play").css("display", "none");
//		$("#person").css("display", "none");
//		$("#commodity").css("display", "none");
//		$("#store").css("display", "none");
//
//		$("#homepage").css("display", "inline");
		gameWarn("小的们正在努力构建中，陛下敬请期待！")
	})
})

$(document).ready(function() {
	$("#person_b").click(function() {
//		$("#play").css("display", "none");
//		$("#homepage").css("display", "none");
//		$("#commodity").css("display", "none");
//		$("#store").css("display", "none");
//
//		$("#person").css("display", "inline");
		gameWarn("小的们正在努力构建中，陛下敬请期待！")
	})
})

$(document).ready(function() {
	$("#commodity_b").click(function() {
//		$("#play").css("display", "none");
//		$("#homepage").css("display", "none");
//		$("#person").css("display", "none");
//		$("#store").css("display", "none");
//
//		$("#commodity").css("display", "inline");
		gameWarn("小的们正在努力构建中，陛下敬请期待！")
	})
})

$(document).ready(function() {
	$("#store_b").click(function() {
//		$("#play").css("display", "none");
//		$("#homepage").css("display", "none");
//		$("#person").css("display", "none");
//		$("#commodity").css("display", "none");
//
//		$("#store").css("display", "inline");
		gameWarn("小的们正在努力构建中，陛下敬请期待！")
	})
})

$(document).ready(function() {
	$("#wuziqi").click(function() {
		pPModel = 2;
		$("#wuziqi_introduce").css("visibility","visible");
		$("#siziqi_introduce").css("visibility","hidden");
		$("#game_pattern").css("left","50px");
	})
})
$(document).ready(function() {
	$("#siziqi").click(function() {
		pPModel = 3;
		$("#wuziqi_introduce").css("visibility","hidden");
		$("#siziqi_introduce").css("visibility","visible");
		$("#game_pattern").css("left","650px");
	})
})

$(document).ready(function() {
	$("#wuziqi_f").click(function() {
		roomModel = 2;
		$("#wuziqi_introduce_f").css("visibility","visible");
		$("#siziqi_introduce_f").css("visibility","hidden");
	})
})
$(document).ready(function() {
	$("#siziqi_f").click(function() {
		roomModel = 3;
		$("#wuziqi_introduce_f").css("visibility","hidden");
		$("#siziqi_introduce_f").css("visibility","visible");
	})
})

$(document).ready(function() {
	$(".quit_game").click(function() {
		$("#play").css("display", "none");
		$("#person").css("display", "none");
		$("#commodity").css("display", "none");
		$("#store").css("display", "none");

		$("#homepage").css("display", "inline");
	})
})
//榜单切换bangdanqiehuan
$(document).ready(function() {
	$("#treasure_friend").click(function() {
		$("#treasure_bangdan_world").css("display","none");
		$("#treasure_bangdan_friend").css("display","inline");
		$("#treasure_world").css({"background-color":"#009BDD","color":"white"});
		$("#treasure_friend").css({"background-color":"white","color":"#009BDD"});
	})
})
$(document).ready(function() {
	$("#treasure_world").click(function() {
		$("#treasure_bangdan_friend").css("display","none");
		$("#treasure_bangdan_world").css("display","inline");
		$("#treasure_friend").css({"background-color":"#009BDD","color":"white"});
		$("#treasure_world").css({"background-color":"white","color":"#009BDD"});
	})
})

$(document).ready(function() {
	$("#playergame").click(function() {
		$("#computer_fight").css("display","none");
		$("#friend_fight").css("display","none");
		$("#player_fight").css("display","inline");
	})
})
$(document).ready(function() {
	$("#conputergame").click(function() {
		$("#computer_fight").css("display","inline");
		$("#friend_fight").css("display","none");
		$("#player_fight").css("display","none");
	})
})
$(document).ready(function() {
	$("#friendgame").click(function() {
		$("#computer_fight").css("display","none");
		$("#friend_fight").css("display","inline");
		$("#player_fight").css("display","none");
	})
})
//begin
$(document).ready(function() {
	$("#yaoqing_close").hover(function() {
		$("#yaoqing_close").attr("src","/nzq/game_img/invitation_close2.png");
	})
})
$(document).ready(function() {
	$("#yaoqing_close").mouseleave(function() {
		$("#yaoqing_close").attr("src","/nzq/game_img/invitation_close1.png");
	})
})
$(document).ready(function() {
	$("#yaoqing_close").click(function() {
		$("#yaoqing").css("display","none");
	})
})
//erzhu
$(document).ready(function() {
	$("#playergame").click(function() {
		$("#playergame").css("box-shadow", "#BCBD93 2px 2px 2px");
		$("#conputergame").css("box-shadow", "");
		$("#friendgame").css("box-shadow", "");
		$("#game").css("background-image", "url(/nzq/img/bgimg.jpg)");
	})
})
$(document).ready(function() {
	$("#conputergame").click(function() {
		$("#playergame").css("box-shadow", "");
		$("#conputergame").css("box-shadow", "#BCBD93 2px 2px 2px");
		$("#friendgame").css("box-shadow", "");
		$("#game").css("background-image", "url(/nzq/img/bgimg2.jpg)");
	})
})
$(document).ready(function() {
	$("#friendgame").click(function() {
		$("#playergame").css("box-shadow", "");
		$("#conputergame").css("box-shadow", "");
		$("#friendgame").css("box-shadow", "#BCBD93 2px 2px 2px");
		$("#game").css("background-image", "url(/nzq/img/bgimg3.jpg)");
	})
})

$(document).ready(function() {
	$("#siziqi").click(function() {
		$("#siziqi .siziqi_sign").attr("src", "/nzq/img/siziqi_sign3.png");
		$("#wuziqi .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign.png");

	})
})

$(document).ready(function() {
	$("#wuziqi").click(function() {
		$("#wuziqi .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign2.png");
		$("#siziqi .siziqi_sign").attr("src", "/nzq/img/siziqi_sign.png");

	})
})

$(document).ready(function() {
	$("#siziqi_f").click(function() {
		$("#siziqi_f .siziqi_sign").attr("src", "/nzq/img/siziqi_sign3.png");
		$("#wuziqi_f .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign.png");

	})
})

$(document).ready(function() {
	$("#wuziqi_f").click(function() {
		$("#wuziqi_f .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign2.png");
		$("#siziqi_f .siziqi_sign").attr("src", "/nzq/img/siziqi_sign.png");

	})
})

$(document).ready(function() {
	$("#siziqi").hover(function() {
			//鼠标经过的操作
			$("#siziqi .siziqi_sign").attr("src", "/nzq/img/siziqi_sign3.3.png");
		},
		function() {
			//鼠标离开的操作
			if($("#siziqi_introduce").css("visibility") == "hidden") {
				$("#siziqi .siziqi_sign").attr("src", "/nzq/img/siziqi_sign.png");
			} else {
				$("#siziqi .siziqi_sign").attr("src", "/nzq/img/siziqi_sign3.png");
			}

		});
})

$(document).ready(function() {
	$("#wuziqi").hover(function() {
			//鼠标经过的操作
			$("#wuziqi .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign2.2.png");
		},
		function() {
			//鼠标离开的操作
			if($("#wuziqi_introduce").css("visibility") == "hidden") {
				$("#wuziqi .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign.png");
			} else {
				$("#wuziqi .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign2.png");
			}
		});
})

$(document).ready(function() {
	$("#siziqi_f").hover(function() {
			//鼠标经过的操作
			$("#siziqi_f .siziqi_sign").attr("src", "/nzq/img/siziqi_sign3.3.png");
		},
		function() {
			//鼠标离开的操作
			if($("#siziqi_introduce_f").css("visibility") == "hidden") {
				$("#siziqi_f .siziqi_sign").attr("src", "/nzq/img/siziqi_sign.png");
			} else {
				$("#siziqi_f .siziqi_sign").attr("src", "/nzq/img/siziqi_sign3.png");
			}

		});
})

$(document).ready(function() {
	$("#wuziqi_f").hover(function() {
			//鼠标经过的操作
			$("#wuziqi_f .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign2.2.png");
		},
		function() {
			//鼠标离开的操作
			if($("#wuziqi_introduce_f").css("visibility") == "hidden") {
				$("#wuziqi_f .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign.png");
			} else {
				$("#wuziqi_f .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign2.png");
			}
		});
})
//end
//创建房间
//s05
$(document).ready(function() {
	$("#confirm_game_f").click(function() {
		if(roomModel == 2) {
			create2Room();
		} else {
			create3Room();
		}
		
	})
})
function create2Room() {
	nzqGameWebSocket.send("05");
	myLocation = "black";
	$("#gy_start").css("display","inline");
	$("#gy_competitor_black .gy_competitor_photo").attr("src","/nzq/photo/"+$("#id_container").html()+".jpg");
	$("#gy_competitor_black .gy_competitor_name").html($("#myinfo_name").html());
	
	$("#black_house_owner").css("display", "inline");
	$("#tick_black").css("display", "none");
	$("#change_black").css("display", "none");
	$("#gy_competitor_black").css("display", "inline");
	
	$("#white_house_owner").css("display", "none");
	$("#tick_white").css("display", "none");
	$("#change_white").css("display", "none");
	$("#gy_competitor_white").css("display", "none");
	
	$("#game_yue2_competitor").css("display","inline");
	$("#game_yue_competitor").css("display","inline");
	
	getInvitationList();
}
function create3Room() {
	nzqGameWebSocket.send("17");
	myLocation = "black";
	$("#gy3_start").css("display","inline");
	$("#gy3_competitor_black .gy3_competitor_photo").attr("src","/nzq/photo/"+$("#id_container").html()+".jpg");
	$("#gy3_competitor_black .gy3_competitor_name").html($("#myinfo_name").html());
	
	$("#gy3_competitor_img_black .gy3_house_owner").css("display", "inline");
	$("#gy3_competitor_img_black .gy3_exit").css("display", "none");
	$("#gy3_competitor_img_black .gy3_exchange").css("display", "none");
	$("#gy3_competitor_black").css("display", "inline");
	
	$("#gy3_competitor_img_white .gy3_house_owner").css("display", "none");
	$("#gy3_competitor_img_white .gy3_exit").css("display", "none");
	$("#gy3_competitor_img_white .gy3_exchange").css("display", "none");
	$("#gy3_competitor_white").css("display", "none");
	
	$("#gy3_competitor_img_red .gy3_house_owner").css("display", "none");
	$("#gy3_competitor_img_red .gy3_exit").css("display", "none");
	$("#gy3_competitor_img_red .gy3_exchange").css("display", "none");
	$("#gy3_competitor_red").css("display", "none");
	
	$("#game_yue3_competitor").css("display","inline");
	$("#game_yue_competitor").css("display","inline");
	getInvitationList();
}
function getInvitationList() {
	$("#gy_list_offline").html("");
	$("#gy_list_online").html("");
	$.ajax({
		url : "/nzq/GetInvitationList",
		data : {
			myxf11 : $("#id_container").html()
		},
		method : 'get',
		dataType : 'json', // 很重要!!!. 预期服务器返回的数据类型
		error : function() {
			console.log("失败");
		},
		success : function(data) {
			console.log("成功");
			var status;
			var friend;
			var name;
			$.each(data,function(index) {
				status = getStatusText(data[index].fstatus);
				if(data[index].remark=="") {
					name = data[index].nickname;
				} else {
					name = data[index].remark;
				}
				
				if(data[index].fstatus==0) {
					friend = '<div gyxf="'+data[index].fxf+'" class="gy_list_friend">'
						+'<img class="gy_list_photo" src="/nzq/photo/'+data[index].fxf+'.jpg">'
						+'<div class="gy_list_info">'
						+'	<div class="gy_list_name">'+name+'</div>'
						+'	<div class="gy_list_grade">'+data[index].grade+'</div>'
						+'	<div class="gy_list_state">'+status+'</div>'
						+'</div>'
						+'</div>';
					$("#gy_list_offline").html($("#gy_list_offline").html() + friend);
				} else {
					friend = '<div gyxf="'+data[index].fxf+'" class="gy_list_friend">'
						+'<img class="gy_list_photo" src="/nzq/photo/'+data[index].fxf+'.jpg" src="/nzq/photo/'+data[index].fxf+'.jpg">'
						+'<div class="gy_list_info">'
						+'	<div class="gy_list_name">'+name+'</div>'
						+'	<div class="gy_list_grade">'+data[index].grade+'</div>'
						+'	<div class="gy_list_state">'+status+'</div>'
						+'</div>'
						+'<button class="gy_list_request" onclick="sendInvitation('+data[index].fxf+')">邀请</button>'
						+'</div>';
					$("#gy_list_online").html($("#gy_list_online").html() + friend);
				}
				console.log(data[index].fxf+data[index].remark+data[index].nickname+data[index].fstatus);
			})
		}
	})
}
//s02
//s18
function sendInvitation(fxf) {
	$("[gyxf="+fxf+"] .gy_list_info .gy_list_state").html("已邀请");
	if(roomModel == 2) {
		nzqGameWebSocket.send("02." + myxf +"."+fxf);
	} else {
		nzqGameWebSocket.send("18." + myxf +"."+fxf);
	}
}
function getStatusText(status) {
	switch (status) {
	case 0:
		return "离线";
	case 1:
		return "闲逛中";
	case 2:
		return "双人五子棋约战准备中";
	case 3:
		return "三人四子棋约战准备中";
	case 4:
		return "双人五子棋匹配赛准备中";
	case 5:
		return "三人四子棋匹配赛准备中";
	case 6:
		return "双人五子棋排位赛准备中";
	case 7:
		return "三人四子棋排位赛准备中";
	case 8:
		return "双人五子棋约战游戏中";
	case 9:
		return "三人四子棋约战游戏中";
	case 10:
		return "双人五子棋匹配赛游戏中";
	case 11:
		return "三人四子棋匹配赛游戏中";
	case 12:
		return "双人五子棋排位赛游戏中";
	case 13:
		return "三人四子棋排位赛游戏中";
	default:
		break;
	}
}
//退出房间
//s07
$(document).ready(function() {
	$("#gy_return_photo").click(function() {
		nzqGameWebSocket.send("07");
		$("#game_yue2_competitor").css("display","none");
		$("#game_yue_competitor").css("display","none");
	})
})
//s22
$(document).ready(function() {
	$("#gy3_return_photo").click(function() {
		nzqGameWebSocket.send("22");
		$("#game_yue3_competitor").css("display","none");
		$("#game_yue_competitor").css("display","none");
	})
})
//s04
$(document).ready(function() {
	$(".gy_competitor_exchange").click(function() {
		nzqGameWebSocket.send("04");
	})
})
function exchangeGYdetail() {
	var white = $("#gy_competitor_white").html();
	var black = $("#gy_competitor_black").html();
	$("#gy_competitor_white").html(black);
	$("#gy_competitor_black").html(white);
}
function exchangeGY3detail() {
//	alert(myLocation);
	if($("#gy3_competitor_img_"+myLocation+" .gy3_house_owner").css("display")!="none") {
		$("#gy3_competitor_img_"+myLocation+" .gy3_house_owner").css("display", "none");
		$("#gy3_competitor_img_"+toLocation+" .gy3_house_owner").css("display", "inline");
	}
	
	$("#gy3_competitor_"+myLocation).css("display", "none");
	$("#gy3_competitor_"+toLocation).css("display", "inline");
	
	var p1 = $("#gy3_competitor_"+myLocation).html();
	var p2 = $("#gy3_competitor_"+toLocation).html();
	$("#gy3_competitor_"+myLocation).html(p2);
	$("#gy3_competitor_"+toLocation).html(p1);
	myLocation = toLocation;
}
function exchangeAnotherAndNull(msg) {
	var player_kong = msg.substr(3).split(".");
	if($("#gy3_competitor_img_"+player_kong[0]+" .gy3_house_owner").css("display")!="none") {
		$("#gy3_competitor_img_"+player_kong[0]+" .gy3_house_owner").css("display", "none");
		$("#gy3_competitor_img_"+player_kong[1]+" .gy3_house_owner").css("display", "inline");
		
	} else if($("#gy3_competitor_img_"+myLocation+" .gy3_house_owner").css("display")!="none") {
		$("#gy3_competitor_img_"+player_kong[0]+" .gy3_exit").css("display", "none");
		$("#gy3_competitor_img_"+player_kong[1]+" .gy3_exit").css("display", "inline");
	}
	
	$("#gy3_competitor_img_"+player_kong[0]+" .gy3_exchange").css("display", "none");
	$("#gy3_competitor_img_"+player_kong[1]+" .gy3_exchange").css("display", "inline");
	
	$("#gy3_competitor_"+player_kong[0]).css("display", "none");
	$("#gy3_competitor_"+player_kong[1]).css("display", "inline");
	
	var p1 = $("#gy3_competitor_"+player_kong[0]).html();
	var p2 = $("#gy3_competitor_"+player_kong[1]).html();
	$("#gy3_competitor_"+player_kong[0]).html(p2);
	$("#gy3_competitor_"+player_kong[1]).html(p1);
}
function exchange22GY3detail() {
	console.log("myLocation:"+myLocation+",toLocation:"+toLocation);
	console.log("#gy3_competitor_img_"+toLocation+" .gy3_exchange");
//	$("#gy3_competitor_img_"+toLocation+" .gy3_exchange").css("display", "none");//无效
//	$("#gy3_competitor_img_"+myLocation+" .gy3_exchange").css("display", "inline");
	
	//我是房主
	if($("#gy3_competitor_img_"+myLocation+" .gy3_house_owner").css("display")!="none") {
		console.log("#gy3_competitor_img_"+myLocation+" .gy3_house_owner");
		$("#gy3_competitor_img_"+myLocation+" .gy3_house_owner").css("display", "none");//无效
		$("#gy3_competitor_img_"+toLocation+" .gy3_house_owner").css("display", "inline");//无效
		$("#gy3_competitor_img_"+toLocation+" .gy3_exit").css("display", "none");
		$("#gy3_competitor_img_"+myLocation+" .gy3_exit").css("display", "inline");
	} else if($("#gy3_competitor_img_"+toLocation+" .gy3_house_owner").css("display")!="none") {
		$("#gy3_competitor_img_"+toLocation+" .gy3_house_owner").css("display", "none");
		$("#gy3_competitor_img_"+myLocation+" .gy3_house_owner").css("display", "inline");
	}
	
	var p1 = $("#gy3_competitor_"+myLocation).html();
	var p2 = $("#gy3_competitor_"+toLocation).html();
	$("#gy3_competitor_"+myLocation).html(p2);
	$("#gy3_competitor_"+toLocation).html(p1);
	myLocation = toLocation;
//	alert("myLocation:"+myLocation);
}
function exchange2GY3detail(ex2) {
	var exchange = Array("none","none");
	var exit = Array("none","none");
	//房主
	if($("#gy3_competitor_img_"+ex2[0]+" .gy3_house_owner").css("display")!="none") {
		$("#gy3_competitor_img_"+ex2[0]+" .gy3_house_owner").css("display", "none");//无效
		$("#gy3_competitor_img_"+ex2[1]+" .gy3_house_owner").css("display", "inline");//无效
	} else if($("#gy3_competitor_img_"+ex2[1]+" .gy3_house_owner").css("display")!="none") {
		$("#gy3_competitor_img_"+ex2[1]+" .gy3_house_owner").css("display", "none");
		$("#gy3_competitor_img_"+ex2[0]+" .gy3_house_owner").css("display", "inline");
	}
	//exit
	if($("#gy3_competitor_img_"+ex2[0]+" .gy3_exit").css("display")!="none") {
		exit[1] = "inline";
	}
	if($("#gy3_competitor_img_"+ex2[1]+" .gy3_exit").css("display")!="none") {
		exit[0] = "inline";
	}
	//exchange
	if($("#gy3_competitor_img_"+ex2[0]+" .gy3_exchange").css("display")!="none") {
		exchange[1] = "inline";
	}
	if($("#gy3_competitor_img_"+ex2[1]+" .gy3_exchange").css("display")!="none") {
		exchange[0] = "inline";
	}
	$("#gy3_competitor_img_"+ex2[0]+" .gy3_exit").css("display",exit[0]);
	$("#gy3_competitor_img_"+ex2[1]+" .gy3_exit").css("display",exit[1]);
	$("#gy3_competitor_img_"+ex2[0]+" .gy3_exchange").css("display", exchange[0]);
	$("#gy3_competitor_img_"+ex2[1]+" .gy3_exchange").css("display", exchange[1]);
	
	var p1 = $("#gy3_competitor_"+ex2[0]).html();
	var p2 = $("#gy3_competitor_"+ex2[1]).html();
	$("#gy3_competitor_"+ex2[0]).html(p2);
	$("#gy3_competitor_"+ex2[1]).html(p1);
	if(myLocation == ex2[0]) {
		myLocation =ex2[1]
	} else if(myLocation == ex2[1]) {
		myLocation =ex2[0]
	}
}
function moveToBlack() {
	exchangeGYdetail();
	$("#black_house_owner").css("display", "inline");
	$("#white_house_owner").css("display", "none");
	
	$("#gy_competitor_white").css("display","none");
	$("#gy_competitor_black").css("display","inline");
}
function moveToWhite() {
	exchangeGYdetail();
	$("#black_house_owner").css("display", "none");
	$("#white_house_owner").css("display", "inline");
	
	$("#gy_competitor_white").css("display","inline");
	$("#gy_competitor_black").css("display","none");
}
function oneToAnother(one, another) {
	exchangeGYdetail();
	$("#black_house_owner").css("display", "none");
	$("#white_house_owner").css("display", "inline");
	
	$("#gy_competitor_white").css("display","inline");
	$("#gy_competitor_black").css("display","none");
}
//s03
$(document).ready(function() {
	$("#yaoqing_confirm").click(function() {
		if(invitationModel == 2) {
			nzqGameWebSocket.send("03."+roomId);
		} else {
			nzqGameWebSocket.send("19."+roomId);
		}
		
		$("#yaoqing").css("display", "none");
	})
})
//s06
$(document).ready(function() {
	$("#yaoqing_refuse").click(function() {
		if(invitationModel == 2) {
			nzqGameWebSocket.send("06."+yaoxf);
		} else {
			nzqGameWebSocket.send("21."+yaoxf);
		}
		
		$("#yaoqing").css("display", "none");
	})
})
//s08
$(document).ready(function() {
	$("#tick_white").click(function() {
		nzqGameWebSocket.send("08");
//		$("#white_house_owner").css("display", "none");
		$("#tick_white").css("display", "none");
		$("#change_white").css("display", "none");
		$("#gy_competitor_white").css("display", "none");
	})
})
//t
//s08
$(document).ready(function() {
	$("#tick_black").click(function() {
		nzqGameWebSocket.send("08");
		$("#black_house_owner").css("display", "none");
		$("#tick_black").css("display", "none");
		$("#change_black").css("display", "none");
		$("#gy_competitor_black").css("display", "none");
	})
})
//s09
$(document).ready(function() {
	$(".gy_exchange").click(function() {
		nzqGameWebSocket.send("09");
	})
})
$(document).ready(function() {
	$("#change_white").click(function() {
		$("#gy_white_count").html("5");
		$("#change_white").css("display", "none");
		$("#gy_white_cover").css("display", "inline");
		Time=setInterval(function(){
			console.log("miao:"+$("#gy3_white_count").html());
					$("#gy_white_count").html($("#gy_white_count").html()*1-1);
					if($("#gy_white_count").html()=="0") {
						$("#gy_white_cover").css("display", "none");
						$("#change_white").css("display", "inline");
						clearInterval(Time);
					}
				}, 1000);
	})
})
$(document).ready(function() {
	$("#change_black").click(function() {
		$("#gy_black_count").html("5");
		$("#change_black").css("display", "none");
		$("#gy_black_cover").css("display", "inline");
		Time=setInterval(function(){
//			console.log("miao:"+$("#gy3_black_count").html());
					$("#gy_black_count").html($("#gy_black_count").html()*1-1);
					if($("#gy_black_count").html()=="0") {
						$("#gy_black_cover").css("display", "none");
						$("#change_black").css("display", "inline");
						clearInterval(Time);
					}
				}, 1000);
	})
})
//s10
$(document).ready(function() {
	$(".gy_exbutton_yes").click(function() {
		clearInterval(Time1);
		nzqGameWebSocket.send("10.1");
		
		if(myLocation == "black") {
			$("#change_white").css("display", "inline");
			$("#gy_exchange_request_white").css("display", "none");
		} else {
			$("#change_black").css("display", "inline");
			$("#gy_exchange_request_black").css("display", "none");
		}
		changeLocationBetweenTwoPlayer();
	})
})
$(document).ready(function() {
	$(".gy_exbutton_no").click(function() {
		clearInterval(Time1);
		nzqGameWebSocket.send("10.0");
		if(myLocation == "black") {
			$("#change_white").css("display", "inline");
			$("#gy_exchange_request_white").css("display", "none");
		} else {
			$("#change_black").css("display", "inline");
			$("#gy_exchange_request_black").css("display", "none");
		}
	})
})

$(document).ready(function() {
	$("#gy_start").click(function() {
		nzqGameWebSocket.send("11");
		beginGy5();
	})
})
//s32
//s35
$(document).ready(function() {
	$("#confirm_game").click(function() {
		if(pPModel == 2) {
			nzqGameWebSocket.send("32");
		} else {
			nzqGameWebSocket.send("35");
		}
		
		$("#match_pipei_wait").css("display", "inline");
		$("#pp_wait_count").html("00:00");
		var timer = 0;
		var min;
		var s;
		var dianjibiao="";
		TimePP=setInterval(function(){
			timer += 1;
			if(timer<10) {
				dianjibiao = "00:0"+timer;
			} else if(timer<60) {
				dianjibiao = "00:"+timer;
			} else {
				min = Math.floor(timer/60);
				s = timer % 60;
				if(min<10) {
					if(s<10) {
						dianjibiao = "0"+min+":0"+s;
					} else {
						dianjibiao = "0"+min+":"+s;
					}
				} else{
					$("#match_pipei_wait").css("display", "none");
					alert("太久未匹配到对手，已退出匹配队列");
					clearInterval(TimePP);
				}
			}
			$("#pp_wait_count").html(dianjibiao);
		}, 1000);
	})
})
$(document).ready(function() {
	$("#pz2_start_button").click(function() {
		nzqGameWebSocket.send("33");
		$("#pz2_"+myLocation+" .pz2_photo_cover").css("display", "none");
		$("#pz2_number").html($("#pz2_number").html()*1 + 1);
		if($("#pz2_number").html()=="2") {
			//开
			clearInterval(TimePz5);
			$("#match_ready_pz2").css("display", "none");
//			alert("开始");
			beginPp5();
		}
	})
})
//s36
$(document).ready(function() {
	$("#pz3_start_button").click(function() {
		nzqGameWebSocket.send("36"+myLocation);
		$("#pz3_"+myLocation+" .pz3_photo_cover").css("display", "none");
		$("#pz3_number").html($("#pz3_number").html()*1 + 1);
		if($("#pz3_number").html()=="3") {
			//开
			clearInterval(TimePz4);
			$("#match_ready_pz3").css("display", "none");
//			alert("开始");
			beginPp4();
		}
	})
})
//end yuezhan
//s20
$(document).ready(function() {
	$("#gy3_to_Black").click(function() {
		nzqGameWebSocket.send("20.black");
		toLocation = "black";
	})
})
//s20
$(document).ready(function() {
	$("#gy3_to_White").click(function() {
		nzqGameWebSocket.send("20.white");
		toLocation = "white";
	})
})
//s20
$(document).ready(function() {
	$("#gy3_to_Red").click(function() {
		nzqGameWebSocket.send("20.red");
		toLocation = "red";
	})
})

//23
$(document).ready(function() {
	$("#gy3_competitor_img_black .gy3_exit").click(function() {
		nzqGameWebSocket.send("23.black");
		
		$("#gy3_competitor_img_black .gy3_exit").css("display", "none");
		$("#gy3_competitor_img_black .gy3_exchange").css("display", "none");
		$("#gy3_competitor_black").css("display", "none");
	})
})
//s23
$(document).ready(function() {
	$("#gy3_competitor_img_white .gy3_exit").click(function() {
		nzqGameWebSocket.send("23.white");
		
		$("#gy3_competitor_img_white .gy3_exit").css("display", "none");
		$("#gy3_competitor_img_white .gy3_exchange").css("display", "none");
		$("#gy3_competitor_white").css("display", "none");
	})
})
//s23
$(document).ready(function() {
	$("#gy3_competitor_img_red .gy3_exit").click(function() {
		nzqGameWebSocket.send("23.red");
		
		$("#gy3_competitor_img_red .gy3_exit").css("display", "none");
		$("#gy3_competitor_img_red .gy3_exchange").css("display", "none");
		$("#gy3_competitor_red").css("display", "none");
	})
})

//s24
$(document).ready(function() {
	$("#gy3_competitor_img_black .gy3_exchange").click(function() {
		nzqGameWebSocket.send("24.black");
		
		$("#gy3_black_count").html("5");
		$(".gy3_exchange").css("display", "none");
		$("#gy3_black_cover").css("display", "inline");
		Time=setInterval(function(){
			console.log("miao:"+$("#gy3_black_count").html());
					$("#gy3_black_count").html($("#gy3_black_count").html()*1-1);
					if($("#gy3_black_count").html()=="0") {
						$("#gy3_black_cover").css("display", "none");
						showOtherExchange();
						clearInterval(Time);
					}
				}, 1000);
	})
})
//s24
$(document).ready(function() {
	$("#gy3_competitor_img_white .gy3_exchange").click(function() {
		nzqGameWebSocket.send("24.white");
		
		$("#gy3_white_count").html("5");
		$(".gy3_exchange").css("display", "none");
		$("#gy3_white_cover").css("display", "inline");
		Time=setInterval(function(){
			console.log("miao:"+$("#gy3_white_count").html());
					$("#gy3_white_count").html($("#gy3_white_count").html()*1-1);
					if($("#gy3_white_count").html()=="0") {
						$("#gy3_white_cover").css("display", "none");
						showOtherExchange();
						clearInterval(Time);
					}
				}, 1000);
	})
})
//s24
$(document).ready(function() {
	$("#gy3_competitor_img_red .gy3_exchange").click(function() {
		nzqGameWebSocket.send("24.red");
		
		$("#gy3_red_count").html("5");
		$(".gy3_exchange").css("display", "none");
		$("#gy3_red_cover").css("display", "inline");
		Time=setInterval(function(){
			console.log("miao:"+$("#gy3_black_count").html());
					$("#gy3_red_count").html($("#gy3_red_count").html()*1-1);
					if($("#gy3_red_count").html()=="0") {
						$("#gy3_red_cover").css("display", "none");
						showOtherExchange();
						clearInterval(Time);
					}
				}, 1000);
	})
})
//s25
$(document).ready(function() {
	$("#agree_black").click(function() {
		nzqGameWebSocket.send("25.1.black");
		
		$("#gy3_exchange_request_black").css("display", "none");
		clearInterval(Time1);
		
		toLocation = "black";
		exchange22GY3detail();
		showOtherExchange()
	})
})
$(document).ready(function() {
	$("#agree_white").click(function() {
		nzqGameWebSocket.send("25.1.white");
		
		$("#gy3_exchange_request_white").css("display", "none");
		clearInterval(Time1);
		
		toLocation = "white";
		exchange22GY3detail();
		showOtherExchange()
	})
})
$(document).ready(function() {
	$("#agree_red").click(function() {
		nzqGameWebSocket.send("25.1.red");
		
		$("#gy3_exchange_request_red").css("display", "none");
		clearInterval(Time1);
		
		toLocation = "red";
		exchange22GY3detail();
		showOtherExchange()
	})
})

$(document).ready(function() {
	$("#refuse_black").click(function() {
		nzqGameWebSocket.send("25.0.black");
		$("#gy3_exchange_request_black").css("display", "none");
		clearInterval(Time1);
		showOtherExchange()
	})
})
$(document).ready(function() {
	$("#refuse_white").click(function() {
		nzqGameWebSocket.send("25.0.black");
		$("#gy3_exchange_request_black").css("display", "none");
		clearInterval(Time1);
		showOtherExchange()
	})
})
$(document).ready(function() {
	$("#refuse_red").click(function() {
		nzqGameWebSocket.send("25.0.red");
		$("#gy3_exchange_request_black").css("display", "none");
		clearInterval(Time1);
		showOtherExchange()
	})
})

$(document).ready(function() {
	$("#gy3_start").click(function() {
		nzqGameWebSocket.send("26");
		beginGy4();
	})
})
