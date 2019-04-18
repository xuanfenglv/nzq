/**
 * 发送消息时生成自己的气泡
 * @param text
 */
function genMyText(text) {
    let textHtml = '<div class="outerChat">'
        + '<img class="outerChat_photo i_photo" src="/nzq/photo/' + imParam.myXf + '.jpg"/>'
        + '<div class="outerChat_msg i_msg outerChat_msg_blue">' + text + '</div>'
        + '</div>';
    $("[xf_no=" + imParam.onChatXf + "]").append(textHtml);
}

/**
 * 根据聊天信息生成气泡
 * @param chatInfo
 */
function genTextByChatInfo(chatInfo) {
    let flag;
    let xf;
    if (chatInfo.type == ChatInfoType.SEND) {
        flag = 'i';
        xf = imParam.myXf;
    } else {
        flag = 'f';
        xf = chatInfo.xf;
    }
    let textHtml = '<div class="outerChat">'
        + '<img class="outerChat_photo ' + flag + '_photo" src="/nzq/photo/' + xf + '.jpg"/>'
        + '<div class="outerChat_msg ' + flag + '_msg outerChat_msg_blue">' + chatInfo.text + '</div>'
        + '</div>';
    imDomObj.chatBox.append(textHtml);
}

/**
 * 创建会话
 * @param chatInfo
 */
function createSession(chatInfo) {
    let d = new Date();
    let name = friendInfoUtil.getName(chatInfo.xf);
    let session = '<div class="msg_window" friendid="' + chatInfo.xf
        + '" onclick="showChatDetail(' + chatInfo.xf + ')">'
        + '<img class="friend_photo" src="/nzq/photo/' + chatInfo.xf
        + '.jpg" />' +
        '   <div class="friend_name">'
        +       name +
        '   </div>'
        + '<div class="msg_time">' + chatInfo.serverTime + '</div>'
        + '<div class="content">' + chatInfo.text + '</div>' + '<div class="number">0</div>'
        + '</div>';
    imDomObj.session.prepend(session);
}