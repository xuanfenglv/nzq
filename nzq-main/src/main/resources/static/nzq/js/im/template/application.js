//todo 新好友红点
/**
 * 用于初始化申请
 * @param apps
 */
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
                let appSend = '<div application_id="' + app.id + '" class="bar" >'
                    + '<img src="/nzq/photo/' + app.receiveXf + '.jpg" class="application_photo" />'
                    + '<div class="app_container">'
                    + '	<div class="app_name">' + app.receiveNickname + '</div>'
                    + '	<div class="app_condition">' + statusText + '</div>'
                    + '</div>'
                    + '<div class="app_del" onclick="removeApplication(' + app.id + ')">删除</div>'
                    + '</div>';
                imDomObj.appSend.append(appSend);
            } else { //我接受的申请
                var statusText;
                if (app.status == AppStatus.SEND) {
                    statusText = '<div class="accept" onclick="showAcceptApplication(' + app.id + ')">同意</div>'
                        + '<div class="refuse" onclick="refuseApplication(' + app.id + ')">拒绝</div>';
                } else if (app.status == AppStatus.ADD) {
                    statusText = "已添加";
                } else if (app.status == AppStatus.REJECT) {
                    statusText = "已拒绝";
                }

                let appReceive = '<div application_id="' + app.id + '" class="bar">'
                    + '<img src="/nzq/photo/' + app.sendXf + '.jpg" class="application_photo" />'
                    + '<div class="app_container">'
                    + '	<div class="app_name">' + app.sendNickname + '</div>'
                    + '	<div class="app_condition">' + app.text + '</div>'
                    + '</div>'
                    + '<div class="a_r">'
                    + statusText
                    + '</div>'
                    + '<div class="app_del" onclick="removeApplication(' + app.id + ')">删除</div>'
                    + '</div>';
                imDomObj.appReceive.append(appReceive);
            }
        });
    }
}

/**
 * 我发送的申请（响应）
 * @param app
 */
function genMyApp(app) {
    // 移除旧的
    if ($("[application_id = " + app.id + "]").length > 0) {
        $("[application_id = " + app.id + "]").remove();
    }
    let statusText = '已发送验证消息';
    let appSend = '<div application_id="' + app.id + '" class="bar" >'
        + '<img src="/nzq/photo/' + app.xf + '.jpg" class="application_photo" />'
        + '<div class="app_container">'
        + '	<div class="app_name">' + app.nickname + '</div>'
        + '	<div class="app_condition">' + statusText + '</div>'
        + '</div>'
        + '<div class="app_del" onclick="removeApplication(' + app.xf + ')">删除</div>'
        + '</div>';
    imDomObj.appSend.prepend(appSend);
}

/**
 * 我接受的申请（推送）
 * @param app
 */
function genReceiveApp(app) {
    // 移除旧的
    if ($("[application_id = " + app.id + "]").length > 0) {
        $("[application_id = " + app.id + "]").remove();
    }

    // 插入新的
    let appReceive = '<div application_id="' + app.id + '" class="bar">'
        + '<img src="/nzq/photo/' + app.xf + '.jpg" class="application_photo" />'
        + '<div class="app_container">'
        + '	<div class="app_name">' + app.nickname + '</div>'
        + '	<div class="app_condition">' + app.text + '</div>'
        + '</div>'
        + '<div class="a_r">'
        + '	<div class="accept" onclick="showAcceptApplication(' + app.id + ')">同意</div>'
        + '	<div class="refuse" onclick="refuseApplication(' + app.id + ')">拒绝</div>'
        + '</div>'
        + '<div class="app_del" onclick="removeApplication(' + app.id + ')">删除</div>'
        + '</div>';
    imDomObj.appReceive.prepend(appReceive);
}
