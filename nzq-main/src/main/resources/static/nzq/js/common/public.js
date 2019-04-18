// 全局变量

let imWs = null;
let imDomObj;
let groupList;


$(() => {
    imDomObj = {
        groupBox: $("#fl_group"),
        totalUnreadMsgNo: $(".newMsg_num"),
        chatBox: $(".chat_box"),
        session: $("#msglist"),
        idContainer: $("#id_container"),
        photo: $("#photo"),
        chatPage: $("#chat_detail"),
        searchFriendByXf: $("#search_friend_xf"),
        searchFriendResult: $("#search_friend_result"),
        // 验证消息
        validText: $("#add_friend_msg"),
        // 分组下拉列表
        groupList: $("#group"),
        // 接受申请时的分组
        groupListInAcceptApp: $("#group1"),
        // 接受申请时的备注
        remarkInAcceptApp: $("#remark1"),
        acceptApplicationPage: $("#acceptApplication"),
        // 添加好友备注
        remark: $("#remark"),
        appSend: $("#application_send"),
        appReceive: $("#application_receive"),
        searchFriendNickname: $("#search_friend_nickname"),
        searchFriendGrade: $("#search_friend_grade"),
        chatFriendName: $("#f_name"),
        chatFriendStatus: $("#f_state")
    }
})

let imParam = {
    myXf: null,
    // 正在聊天账号（点开聊天页面时赋值，关闭聊天页面时置为null）
    onChatXf: null,
    receiveApplicationXf: null,
    totalUnreadMsgNo: 0,
    searchSex: null,
    acceptAppId: null,
    // 累加未读消息数
    addTotalUnreadMsgNo: function () {
        this.totalUnreadMsgNo++;
        // 更新dom
        imDomObj.totalUnreadMsgNo.html(this.totalUnreadMsgNo);
        if (this.totalUnreadMsgNo > 0) {
            imDomObj.totalUnreadMsgNo.css("display", "inline");
        }
    },
    // 减少未读消息数
    subTotalUnreadMsgNo: function (no) {
        this.totalUnreadMsgNo -= no;
        // 更新dom
        imDomObj.totalUnreadMsgNo.html(this.totalUnreadMsgNo);
        if (this.totalUnreadMsgNo <= 0) {
            imDomObj.totalUnreadMsgNo.css("display", "none");
        }
    }
}

