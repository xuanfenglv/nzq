/**
 * 生成分组
 * @param group
 */
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
    imDomObj.groupBox.append(groupHtml);
}