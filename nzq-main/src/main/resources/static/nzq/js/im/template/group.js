/**
 * 生成分组
 * @param group
 */
function genFriendGroup(group) {
    // 插入外面的分组列表
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
    imDomObj.groupBox.append(groupHtml);

    // 插入分组管理中的分组列表
    let manageGroup = '<div class="mygroup">'
        +'<div class="groupname" mygroupid="'+group.id+'" onclick="showEditGroupName('+group.id+')" >'+group.name+'</div>'
        +'<img class="delete_group" title="删除分组" onclick="showDeleteGroup('+group.id+')" src="/nzq/img/delete_group.png" />'
        +'</div>';

    // manageGroup = '<div class="mygroup">'
    //     + '<div class="groupname" mygroupid="' + id + '" onclick="showEditGroupName(' + id + ')" >' + name + '</div>'
    //     + '</div>';
    imDomObj.manageGroupBox.append(manageGroup);
}
function genFriendGroups(groups) {
    groups.forEach(group => {
        genFriendGroup(group)
    });
}
/**
 * 更新分组名字
 */
function refreshGroupName(group) {
    $("[mygroupid = "+group.id+"]").html(group.name);
    $("#"+group.id+" .group_name").html(group.name);
}
