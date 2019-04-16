function genFriendGroup(group) {
    let groupHtml = '<div class="group"><div class="group_detail" id="' +
        group.id +
        '" onclick="showFriends(this.id)"><div class="group_arrow"><img src="/nzq/img/right.png" style="width: 100%;" imgid="' +
        group.id +
        '" /></div><div class="group_name">' +
        group.name +
        '</div><div class="online_number"><span class="f_online" >' +
        0 +
        '</span>/<span class="f_total" >' +
        0 +
        '</span></div></div><div class="panel" style="display: none;" groupid="' +
        group.id +
        '"><div class="online"></div><div class="offline"></div></div></div>';
    return groupHtml;
}
function genFriend(friend) {
    let name = friend.remark?friend.remark:friend.nickname;
    let onOff = friend.status==1?'[在线]':'[离线]';
    let friendHtml = '<div xf="'
        + friend.id
        + '" class="friend_msg" onclick="showChatDetail('+ friend.id+ ')"><img class="friend_msg_photo" src="/nzq/photo/'
        + friend.id
        + '.jpg" /><div class="friend_msg_name">'
        + name
        + '</div><div class="friend_msg_content">'
        + '[在线]'
        + '</div></div>';
    return friendHtml;
}

function genMyText(text) {
    let textHtml = '<div class="outerChat">'
        + '<img class="outerChat_photo i_photo" src="/nzq/photo/' + imParam.myXf + '.jpg"/>'
        + '<div class="outerChat_msg i_msg outerChat_msg_blue">' + text + '</div>'
        + '</div>';
    $("[xf_no=" + imParam.onChatXf + "]").append(textHtml);
}

function genTextByChatInfo(chatInfo) {
    let flag;
    let xf;
    if (chatInfo.type == ChatInfoType.SEND) {
        flag = 'i';
        xf = imParam.myXf;
    } else {
        flag = 'f';
        xf=chatInfo.xf;
    }
    let textHtml = '<div class="outerChat">'
        + '<img class="outerChat_photo '+flag+'_photo" src="/nzq/photo/' + xf + '.jpg"/>'
        + '<div class="outerChat_msg '+flag+'_msg outerChat_msg_blue">' + chatInfo.text + '</div>'
        + '</div>';
    imDomObj.chatBox.append(textHtml);
}
// todo 去dom中查名字，杜绝,serverTime渲染页面
function createSession(chatInfo) {
    let d = new Date();
    let session = '<div class="msg_window" friendid="' + chatInfo.xf
        + '" onclick="showChatDetail(' + chatInfo.xf + ')">'
        + '<img class="friend_photo" src="/nzq/photo/' + chatInfo.xf
        + '.jpg" />' + '<div class="friend_name">'
        + $("div[xf = " + chatInfo.xf + "] .friend_msg_name").html() + '</div>'
        + '<div class="msg_time">'+d.getHours()+":"+d.getMinutes()+'</div>'
        + '<div class="content">'+chatInfo.text+'</div>' + '<div class="number">0</div>'
        + '</div>';
    imDomObj.session.prepend(session);
}