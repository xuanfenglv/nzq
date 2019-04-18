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
    let msg = AcceptAppMsg(imParam.acceptAppId);
    imWs.sendMsg(msg);
}
/**
 * 拒绝申请
 * @param send_xf
 */
function refuseApplication(send_xf) {
}

/**
 * 初始化申请
 */
function initApplication() {
    getApp();
}