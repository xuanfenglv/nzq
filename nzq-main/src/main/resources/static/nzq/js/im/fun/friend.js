/**
 * 显示某个分组的好友列表
 * @param id
 */
function showFriends(id) {
    $("[groupid=" + id + "]").toggle();

    if ($("[groupid=" + id + "]").is(":visible")) {
        $("[imgid=" + id + "]").attr("src", "/nzq/img/down.png");
    } else {
        $("[imgid=" + id + "]").attr("src", "/nzq/img/right.png");
    }
}

/**
 * 显示添加好友页面
 * @param xf
 */
function showAddFriend(xf) {
    // 清空分组下拉列表
    $("#group").html("");
    // 给下拉列表赋值 todo 优化
    var groups = document.getElementsByClassName("group_detail");
    for (var i = 0; i < groups.length; i++) {
        let group = groups[i];
        let id = group.id;
        let name = group.getElementsByClassName("group_name")[0].innerHTML;
        $("#group").html($("#group").html() + '<option value="' + id + '">' + name + '</option>');
    }
    // 把一片dom copy过来
    $("#add_friend_data").html($("[search_friend_id = " + xf + "]").html());
    // 显示添加好友页面
    $("#add_friend").css("display", "inline");
    // 清空备注
    $("#remark").val("");
    // 设置全局变量
    imParam.receiveApplicationXf = xf;
}

function deleteFriend(xf) {
    let msg = new DeleteFriend(xf);
    imWs.sendMsg(msg);

}
