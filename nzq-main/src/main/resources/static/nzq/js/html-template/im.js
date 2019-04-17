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
    let name = friend.remark ? friend.remark : friend.nickname;
    let onOff = friend.status == 1 ? '[在线]' : '[离线]';
    let friendHtml = '<div xf="'
        + friend.id
        + '" class="friend_msg" onclick="showChatDetail(' + friend.id + ')"><img class="friend_msg_photo" src="/nzq/photo/'
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
        xf = chatInfo.xf;
    }
    let textHtml = '<div class="outerChat">'
        + '<img class="outerChat_photo ' + flag + '_photo" src="/nzq/photo/' + xf + '.jpg"/>'
        + '<div class="outerChat_msg ' + flag + '_msg outerChat_msg_blue">' + chatInfo.text + '</div>'
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
        + '<div class="msg_time">' + d.getHours() + ":" + d.getMinutes() + '</div>'
        + '<div class="content">' + chatInfo.text + '</div>' + '<div class="number">0</div>'
        + '</div>';
    imDomObj.session.prepend(session);
}

function genSearchFriend(data) {

    let searchFriend = '<div class="search_friend_item">'
        + '<div class="search_friend_one" search_friend_id="' + data.id + '">'
        + '<img class="friend_msg_photo" src="/nzq/photo/' + data.id + '.jpg" />'
        + '<div class="friend_msg_data">'
        + '<div class="search_friend_msg">'
        + '<div class="search_friend_msg_name">' + data.nickname + '</div>'
        + '<div class="search_friend_msg_xf">(' + data.id + ')</div>'
        + '</div>'
        + '<div class="friend_msg_detail">'
        + '<div class="friend_msg_sex_' + data.sex + '">'
        + '<img src="/nzq/img/' + data.sex + '.png" style="width: 10px;height:10px;margin-top: 1px;margin-left: 1px;" /> ' + 18 + ''
        + '</div>'
        + '<div class="friend_msg_grade">' + data.grade + '</div>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '<button class="add_button" onclick="showAddFriend(' + data.id + ')" >添加</button>'
        + '</div>';

    imDomObj.searchFriendResult.html(searchFriend);
}

//todo 计算年龄，是否是好友
function genSearchFriends(data) {
    imDomObj.searchFriendResult.html(emptyStr);
    data.forEach(user => {
        let add = '<button class="add_button" onclick="showAddFriend(' + user.id + ')" >添加</button>';
        let search_item = '<div class="search_friend_item">'
            + '<div class="search_friend_one" search_friend_id="' + user.id + '">'
            + '<img class="friend_msg_photo" src="/nzq/photo/' + user.id + '.jpg" />'
            + '<div class="friend_msg_data">'
            + '<div class="search_friend_msg">'
            + '<div class="search_friend_msg_name">' + user.nickname + '</div>'
            + '<div class="search_friend_msg_xf">(' + user.id + ')</div>'
            + '</div>'
            + '<div class="friend_msg_detail">'
            + '<div class="friend_msg_sex_' + user.sex + '">'
            + '<img src="/nzq/img/' + user.sex + '.png" style="width: 10px;height:10px;margin-top: 1px;margin-left: 1px;" /> ' + 18 + ''
            + '</div>'
            + '<div class="friend_msg_grade">' + user.grade + '</div>'
            + '</div>'
            + '</div>'
            + '</div>'
            + add
            + '</div>';
        imDomObj.searchFriendResult.append(search_item);
    })


}
//todo 新好友红点
function genApp(apps) {
    if (apps) {
        apps.forEach(app => {
            // 我发送的申请
            if (app.sendXf == imParam.myXf) {
                let statusText;
                if (app.status == AppStatus.SEND) {
                    statusText = '已发送验证消息';
                } else if (app.status == AppStatus.ADD) {
                    statusText = '对方已添加你为好友';
                } else if (app.status == AppStatus.REJECT) {
                    statusText = '拒绝了你的好友申请';
                }
                let appSend = '<div application_send_id="'+app.receiveXf+'" class="bar" >'
                    +'<img src="/nzq/photo/'+app.receiveXf+'.jpg" class="application_photo" />'
                    +'<div class="app_container">'
                    +'	<div class="app_name">'+app.receiveNickname+'</div>'
                    +'	<div class="app_condition">'+statusText+'</div>'
                    +'</div>'
                    +'<div class="app_del" onclick="removeSendApplication('+app.receiveXf+')">删除</div>'
                    +'</div>';
                imDomObj.appSend.append(appSend);
            } else { //我接受的申请
                var statusText;
                if (app.status == AppStatus.SEND) {
                    statusText = '<div class="accept" onclick="acceptApplication('+send_xf+')">同意</div>'
                        +'<div class="refuse" onclick="refuseApplication('+send_xf+')">拒绝</div>';
                } else if (app.status == AppStatus.ADD) {
                    statusText = "已添加";
                } else if (app.status == AppStatus.REJECT) {
                    statusText = "已拒绝";
                }

                let appReceive = '<div application_receive_id="'+app.sendXf+'" class="bar">'
                    +'<img src="/nzq/photo/'+app.sendXf+'.jpg" class="application_photo" />'
                    +'<div class="app_container">'
                    +'	<div class="app_name">'+app.sendNickname+'</div>'
                    +'	<div class="app_condition">'+app.text+'</div>'
                    +'</div>'
                    +'<div class="a_r">'
                    +statusText
                    +'</div>'
                    +'<div class="app_del" onclick="removeReceiveApplication('+app.sendXf+')">删除</div>'
                    +'</div>';
                imDomObj.appReceive.append(appReceive);
            }
        });
    }
}