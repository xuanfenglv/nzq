/**
 * 显示接受申请页面
 * @param appId
 */
function showAcceptApplication(appId) {
    imParam.acceptAppId = appId;
    // 清空并更新下拉
    imDomObj.groupListInAcceptApp.html(emptyStr);
    groupList.forEach(group => {
        imDomObj.groupListInAcceptApp.append('<option value="'+group.id+'">'+group.name+'</option>');
    });
    // 清空备注
    imDomObj.remarkInAcceptApp.val(emptyStr);
    // show接受申请页面
    imDomObj.acceptApplicationPage.css("display", "inline");
}

/**
 * 接受申请
 */
function acceptApplication() {
    let remark = imDomObj.remarkInAcceptApp.val();
    let groupId = imDomObj.groupListInAcceptApp.val();
    let msg = new AcceptAppMsg(imParam.acceptAppId,groupId,remark);
    imWs.sendMsg(msg);
}
/**
 * 拒绝申请
 * @param send_xf
 */
function refuseApplication(appId) {
    let msg = new RejectAppMsg(appId);
    imWs.sendMsg(msg);
}

/**
 * 初始化申请
 */
function initApplication() {
    getApp();
}
