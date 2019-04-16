// 显示某个分组的好友列表
function showFriends(id) {
    $("[groupid=" + id + "]").toggle();

    if ($("[groupid=" + id + "]").is(":visible")) {
        $("[imgid=" + id + "]").attr("src", "/nzq/img/down.png");
    } else {
        $("[imgid=" + id + "]").attr("src", "/nzq/img/right.png");
    }
}
// 显示好友聊天框
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