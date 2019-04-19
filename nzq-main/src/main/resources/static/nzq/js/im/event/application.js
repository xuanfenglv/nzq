$(() => {

    // 发送申请页
    // 关
    $("#add_friend_return").click(function () {
        $("#add_friend").css("display", "none");
    })
    //发送申请按钮
    $("#add_friend_send").click(function () {

        // 验证消息
        let text = imDomObj.validText.val();
        // 分组id
        let groupId = imDomObj.groupList.val();
        // 好友备注
        let remark = imDomObj.remark.val();

        let sendAppMsg = new SendAppMsg(imParam.receiveApplicationXf,text,groupId,remark);
        imWs.sendMsg(sendAppMsg);
    })
    // 申请列表页面
    // 开
    $("#newfriend").click(function () {
        $("#application").css("display", "inline");
    })

    // 关
    $("#application_return").click(function () {
        $("#application").css("display", "none");
    })

    // 接受申请页
    // 关闭
    $("#accept_quit").click(function () {
        $("#acceptApplication").css("display", "none");
    })
    // 确定
    $("#accept_confirm").click(function () {
        acceptApplication();
    });
});
