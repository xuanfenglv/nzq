// 全局变量
// 正在聊天账号（点开聊天页面时赋值，关闭聊天页面时置为null）
var onChatXf = null;
var myXf = null;
var imWs = null;
let imDomObj;

let imParam = {
    totalUnreadMsgNo:0,
    // 累加未读消息数
    addTotalUnreadMsgNo:function () {
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

$(()=>{
    imDomObj={
        totalUnreadMsgNo:$("#msg_number_val"),
        chatBox: $(".chat_box"),
        session:$("#msglist")
    }
})