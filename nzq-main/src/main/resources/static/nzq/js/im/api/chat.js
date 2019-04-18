function getOtherUserInfo(xf,callback) {
    let url = '/users/strangers/'+xf;
    httpUtil.get(url, null, callback);
}

function getOtherUserInfos(nickname,sex,grade,callback) {
    let url = '/users/strangers';
    httpUtil.get(url, {nickname: nickname, sex: sex, grade: grade}, callback);
}
function getApp() {
    let url = `/applications/${imParam.myXf}`;
    httpUtil.get(url,null,genApp)
}