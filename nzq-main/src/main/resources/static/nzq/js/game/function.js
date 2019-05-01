/**
 * 创建2人房间
 */
function create2Room() {
    // 废弃
    gameParams.myPosition=Position.黑;
    // 显示那种自定义房间
    gameVue.showCustomRoom=RoomSize.TWO;
    // 初始化我的位置为黑色方
    gameVue.myPosition=Position.黑;
    // 设置房主位置（自动显示房主的开始按钮）
    gameVue.owner=Position.黑;
    // 设置我的最新信息
    gameVue.room.black={xf:myInfoVue.myInfo.id, name: myInfoVue.myInfo.nickname}
}

/**
 * 创建3人房间
 */
function create3Room() {
    gameParams.myPosition=Position.黑;

    $("#gy3_start").css("display", "inline");
    $("#gy3_competitor_black .gy3_competitor_photo").attr("src", "/nzq/photo/" + $("#id_container").html() + ".jpg");
    $("#gy3_competitor_black .gy3_competitor_name").html($("#myinfo_name").html());

    $("#gy3_competitor_img_black .gy3_house_owner").css("display", "inline");
    $("#gy3_competitor_img_black .gy3_exit").css("display", "none");
    $("#gy3_competitor_img_black .gy3_exchange").css("display", "none");
    $("#gy3_competitor_black").css("display", "inline");

    $("#gy3_competitor_img_white .gy3_house_owner").css("display", "none");
    $("#gy3_competitor_img_white .gy3_exit").css("display", "none");
    $("#gy3_competitor_img_white .gy3_exchange").css("display", "none");
    $("#gy3_competitor_white").css("display", "none");

    $("#gy3_competitor_img_red .gy3_house_owner").css("display", "none");
    $("#gy3_competitor_img_red .gy3_exit").css("display", "none");
    $("#gy3_competitor_img_red .gy3_exchange").css("display", "none");
    $("#gy3_competitor_red").css("display", "none");

    $("#game_yue3_competitor").css("display", "inline");
    $("#game_yue_competitor").css("display", "inline");
}
