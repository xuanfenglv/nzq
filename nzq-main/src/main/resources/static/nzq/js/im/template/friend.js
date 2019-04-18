/**
 * 生成单个好友
 * @param data
 */
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
/**
 * 生成多个好友
 * @param users
 */
function genSearchFriends(users) {
    imDomObj.searchFriendResult.html(emptyStr);
    users.forEach(user => {
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
/**
 * add friend dom
 * @param friend
 */
function genFriend(friend) {
    let name = friend.remark ? friend.remark : friend.nickname;
    let userStatus = friend.status == UserStatus.OFFLINE?'离线':'在线';

    let friendHtml = '<div xf="'
        + friend.xf
        + '" class="friend_msg" onclick="showChatDetail(' + friend.xf + ')"><img class="friend_msg_photo" src="/nzq/photo/'
        + friend.xf
        + '.jpg" /><div class="friend_msg_name">'
        + name
        + '</div><div class="friend_msg_content">'
        + '['+userStatus+']'
        + '</div></div>';

    if (friend.status == UserStatus.OFFLINE) {
        $("[groupid="+ friend.groupId+ "] .offline").append(friendHtml);
    } else {
        $("[groupid=" + friend.groupId + "] .online").append(friendHtml);
        groupNoInfo.addOnline(friend.groupId);
        groupNoInfo.addOnline(friend.groupId);
    }
    groupNoInfo.addTotal(friend.groupId);
}