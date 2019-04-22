function getOtherUserInfo(xf, callback) {
    let url = `/users/strangers/${xf}`;
    httpUtil.get(url, null, callback);
}

function getOtherUserInfos(nickname, sex, grade, callback) {
    let url = '/users/strangers';
    httpUtil.get(url, {nickname: nickname, sex: sex, grade: grade}, callback);
}

function getApp() {
    let url = `/applications/users/${imParam.myXf}`;
    httpUtil.get(url, null, genApp)
}

function removeApplication(appId) {
    let url = `/applications/${appId}`;
    httpUtil.delete(url, null, () => {
        $("[application_id=" + appId + "]").remove();
    });

}

function addFriendGroup(groupName) {
    let url = '/friend-groups';
    httpUtil.post(url, JSON.stringify({name: groupName}), data => {
        // 关闭添加分组页面
        $("#edit_groupname").css("display", "none");
        // 添加一个分组
        genFriendGroup({id: data, name: groupName})
    })
}

function updateFriendGroup(group) {
    let url = '/friend-groups';
    httpUtil.put(url, JSON.stringify(group), () => {
        // 关闭修改分组页面
        $("#edit_groupname").css("display", "none");
        // 更新一个分组
        refreshGroupName(group);
    })
}

function deleteFriendGroup(id) {
    let url = `/friend-groups/${id}`;
    httpUtil.delete(url, null, data => {
        // 删除页面关闭
        $("#del_group").css("display", "none");
        // 删除一个分组
        groupToGroup(id, data);
    })
}
