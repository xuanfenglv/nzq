$(() => {
    // 退出聊天窗口
    $("#msg_number").click(function () {
        imDomObj.chatPage.css("display", "none");
        // 清空聊天气泡
        imDomObj.chatBox.html(emptyStr);
        // 清空当前聊天人
        imParam.onChatXf = null;
    })

    // 顶部切换按钮
    $("#friend").click(function () {
        $("#friendlist").css("display", "inline");
        $("#msg").css({
            "background-color": "#1296db",
            "color": "white"
        });
        $("#friend").css({
            "background": "white",
            "color": "#1296db"
        });
        $("#addfriend").css("display", "none");
        $("#editgroup").css("display", "inline");
    })

    $("#msg").click(function () {
        $("#friendlist").css("display", "none");
        $("#friend").css({
            "background-color": "#1296db",
            "color": "white"
        });
        $("#msg").css({
            "background": "white",
            "color": "#1296db"
        });
        $("#addfriend").css("display", "inline");
        $("#editgroup").css("display", "none");
    })


})
