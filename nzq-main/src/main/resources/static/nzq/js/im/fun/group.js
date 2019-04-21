function editGroup() {
    // 添加分组
    if (imParam.editGroupStatus == EditGroupStatus.ADD.id) {
        let groupName = imDomObj.groupName.val();
        addFriendGroup(groupName);
    } else { // 修改分组名字
        let groupName = imDomObj.groupName.val();
        let group = {id:imParam.onEditGroupId,name:groupName};
        updateFriendGroup(group);
    }
}
// 编辑页-开
function showEditGroupName(groupId) {
    imParam.editGroupStatus=EditGroupStatus.EDIT;
    imParam.onEditGroupId=groupId;
    $("#edit_groupname_menu_name").html(EditGroupStatus.EDIT.name);
    // 从dom中带入
    // todo
    $("#edit_groupname_menu_input").val($("[mygroupid = "+groupId+"]").html());
    $("#edit_groupname").css("display","inline");
}
// 删除页-开
function showDeleteGroup(groupId) {
    $("#del_group").css("display", "inline");
    imParam.onEditGroupId = groupId;
}

function groupToGroup(deleteGroupId, defaultGroupId) {
    var group1 = $("#"+deleteGroupId).parent();
    var group2 = $("#"+defaultGroupId).parent();
    var online = group1.children(".panel").children(".online");
    var offline = group1.children(".panel").children(".offline");

    // 把删除的分组下的好友dom移动过去(简单粗暴)
    group2.children(".panel").children(".online").append(online.html());
    group2.children(".panel").children(".offline").append(offline.html());

    // 更新在线、总人数
    groupNoInfo.move(deleteGroupId, defaultGroupId);

    // 移除dom
    $("[mygroupid = "+deleteGroupId+"]").parent().remove();
    $("#"+deleteGroupId).parent().remove();

}
