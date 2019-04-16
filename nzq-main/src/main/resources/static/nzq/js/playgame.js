var Time1;
var Time2;
var Time3;
$(document).ready(function() {
	$("#inner_chat_menu").click(function() {
//		alert("Fff");
		 $("#inner_chat_fast").toggle();
	})
})
$(document).ready(function() {
	$("#fast_emoticon").click(function() {
		$("#fast_msg").css("background-color","#F7F9F4");
		$("#fast_emoticon").css("background-color","lightgray");
		$("#inner_chat_emoticon").css("display", "inline");
		$("#inner_chat_fastmsg").css("display", "none");
	})
})
$(document).ready(function() {
	$("#fast_msg").click(function() {
		$("#fast_msg").css("background-color","lightgray");
		$("#fast_emoticon").css("background-color","#F7F9F4");
		$("#inner_chat_emoticon").css("display", "none");
		$("#inner_chat_fastmsg").css("display", "inline");
	})
})
$(document).ready(function() {
	$("#inner_chat_send").click(function() {
		daojishi_bar();
		$("#player1_daojishi_count").html("3");
		$("#player1_daojishi").css("display", "inline");
		var Time=setInterval(function(){
			$("#player1_daojishi_count").html($("#player1_daojishi_count").html()*1-1);
			if($("#player1_daojishi_count").html()=="0") {
				$("#player1_daojishi").css("display", "none");
				clearInterval(Time);
			}
		}, 1000);
	})
})
function daojishi(num) {
	daojishi_bar();
	$("#player"+num+"_daojishi_count").html("10");
	$("#player"+num+"_daojishi").css("display", "inline");
	var Time=setInterval(function(){
		$("#player"+num+"_daojishi_count").html($("#player"+num+"_daojishi_count").html()*1-1);
		if($("#player"+num+"_daojishi_count").html()=="0") {
			$("#player"+num+"_daojishi").css("display", "none");
			clearInterval(Time);
		}
	}, 1000);
}
function daojishi1() {
	daojishi_bar();
	$("#player1_daojishi_count").html("30");
	$("#player1_daojishi").css("display", "inline");
	Time1=setInterval(function(){
		$("#player1_daojishi_count").html($("#player1_daojishi_count").html()*1-1);
		if($("#player1_daojishi_count").html()=="0") {
			$("#player1_daojishi").css("display", "none");
			clearInterval(Time1);
			alert("对方超时未下，判你赢,开不开心");
			reSet();
			$("#wuziqi_game").css("display", "none");
		}
	}, 1000);
}
function daojishi2() {
	daojishi_bar();
	$("#player2_daojishi_count").html("30");
	$("#player2_daojishi").css("display", "inline");
	Time2=setInterval(function(){
		$("#player2_daojishi_count").html($("#player2_daojishi_count").html()*1-1);
		if($("#player2_daojishi_count").html()=="0") {
			$("#player2_daojishi").css("display", "none");
			clearInterval(Time2);
			$("#game_success").css("display", "inline");
		}
	}, 1000);
}
function daojishi3() {
	daojishi_bar();
	$("#player3_daojishi_count").html("30");
	$("#player3_daojishi").css("display", "inline");
	Time3=setInterval(function(){
		$("#player3_daojishi_count").html($("#player3_daojishi_count").html()*1-1);
		if($("#player3_daojishi_count").html()=="0") {
			$("#player3_daojishi").css("display", "none");
			clearInterval(Time3);
			$("#game_fail").css("display", "inline");
		}
	}, 1000);
}
function daojishi_bar() {
	$("#daojishi").stop();
	$("#daojishi").css("width","0px");
	$("#daojishi").animate({width:'600px'},30000);
}
function daojishi_bar_stop() {
	$("#daojishi").stop();
	$("#daojishi").css("width","0px");
}
$(document).ready(function() {
	$(".emo_contain").click(function() {
		var id = $(this).attr("emoid");
		sendEmoticon(id);
	})
})
function showReceiveEmoticon(emoid) {
	showPlayerEmoticon(1, emoid);
}
function showReceiveEmoticon3(loc_emoid) {
	var loc = loc_emoid[0];
	var emoid = loc_emoid[1];
	if(loc == "black") {
		showPlayerEmoticon(1, emoid);
	} else if(loc == "red") {
		showPlayerEmoticon(2, emoid);
	} else {
		if(myLocation == "black") {
			showPlayerEmoticon(1, emoid);
		} else {
			showPlayerEmoticon(2, emoid);
		}
	}
}
function showPlayerEmoticon(playerid, emoid) {
	$("#player"+playerid+"_emoticon").stop(true, true);
	$("#player"+playerid+"_emoticon").attr("src", "/nzq/emoticon/"+emoid+".gif");
	$("#player"+playerid+"_emoticon").show(500).delay(1300).hide(500);
}
function showReceiveEmoticon(emoid) {
	$("#player1_emoticon").stop(true, true);
	$("#player1_emoticon").attr("src", "/nzq/emoticon/"+emoid+".gif");
	$("#player1_emoticon").show(500).delay(1300).hide(500);
}
function sendEmoticon(emoid) {
	$("#player3_emoticon").stop(true, true);
	$("#player3_emoticon").attr("src", "/nzq/emoticon/"+emoid+".gif");
	$("#player3_emoticon").show(500).delay(1300).hide(500);
	if(roomModel == 2) {
		nzqGameWebSocket.send("14" + emoid);
	} else {
		nzqGameWebSocket.send("29." + myLocation + "." + emoid);
	}
	
}
$(document).ready(function() {
	$(".voice").click(function() {
		var id = $(this).attr("voiceid");
		sendVoice(id);
	})
})
function sendVoice(voiceid) {
	//$("#player3_emoticon").stop(true, true);
	///$("#player3_emoticon").attr("src", "/nzq/emoticon/"+emoid+".gif");
	//$("#player3_emoticon").show(500).delay(1300).hide(500);
	if(roomModel == 2) {
		nzqGameWebSocket.send("15" + voiceid);
	} else {
		nzqGameWebSocket.send("30." + myLocation + "." + voiceid);
	}
	
	playVoice(voiceid);
}
function playVoice(voiceid) {
	var audio = document.createElement("audio");
	audio.src = "/nzq/voice/wav/"+voiceid+".wav";
	audio.play();
}
function playVoice3(loc_voiceid) {
	var loc = loc_voiceid[0];
	var voiceid = loc_voiceid[1];
	//小喇叭
	var audio = document.createElement("audio");
	audio.src = "/nzq/voice/wav/"+voiceid+".wav";
	audio.play();
}
$(document).ready(function() {
	$("#inner_chat_send").click(function() {
		var innerMsg = $("#inner_chat_input").val();
		if(roomModel ==2) {
			nzqGameWebSocket.send("13" + innerMsg);
		} else {
			nzqGameWebSocket.send("28." + myLocation + "." + innerMsg);
		}
		addMyInnerMsg(innerMsg);
	})
})
function addMyInnerMsg(innerMsg) {
	var innerQipao ='<div class="innerChat">'
		+'	<img class="innerChat_photo"  src="/nzq/photo/'+myxf+'.jpg"/>'
		+'	<div class="innerChat_msg innerChat_msg_'+myLocation+'">'
		+		innerMsg
		+'	</div>'
		+'</div>';
	$("#inner_chat_record").html($("#inner_chat_record").html()+innerQipao);
	
}
function addFriendInnerMsg(innerMsg) {
	var src;
	var location;
	if(myLocation == "black") {
		location = "white";
		src = $("#gy_competitor_white .gy_competitor_photo").attr("src");
	} else {
		location = "black";
		src = $("#gy_competitor_black .gy_competitor_photo").attr("src");
	}
	var innerQipao ='<div class="innerChat">'
		+'	<img class="innerChat_photo"  src="'+src+'"/>'
		+'	<div class="innerChat_msg innerChat_msg_'+location+'">'
		+		innerMsg
		+'	</div>'
		+'</div>';
	$("#inner_chat_record").html($("#inner_chat_record").html()+innerQipao);
	
}
function addFriendInnerMsg3(innerMsg) {
	var loc_msg = innerMsg.split(".");
	var src;
	var location = loc_msg[0];
	var msg = loc_msg[1];
	src = $("#gy3_competitor_"+location+" .gy3_competitor_photo").attr("src");
	var innerQipao ='<div class="innerChat">'
		+'	<img class="innerChat_photo"  src="'+src+'"/>'
		+'	<div class="innerChat_msg innerChat_msg_'+location+'">'
		+		msg
		+'	</div>'
		+'</div>';
	$("#inner_chat_record").html($("#inner_chat_record").html()+innerQipao);
	
}
$(document).ready(function() {
	$("#game_success_button").click(function() {
		$("#game_success").css("display", "none");
		reSet();
		$("#wuziqi_game").css("display", "none");
		nzqGameWebSocket.send("16");
	})
})
$(document).ready(function() {
	$("#game_fail_button").click(function() {
		$("#game_fail").css("display", "none");
		reSet();
		$("#wuziqi_game").css("display", "none");
		
	})
})
