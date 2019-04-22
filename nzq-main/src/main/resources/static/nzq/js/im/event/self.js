$(() => {
    $("#photo").click(function () {
        $("#log_window").css("display", "inline");
        $("#myinfo").css("display", "inline");
    })
    $("#myinfo_return").click(function () {
        $("#log_window").css("display", "none");
        $("#myinfo").css("display", "none");
    })
    $("#myinfo_edit_sign").click(function() {
        if($("#myinfo_edit_sign").attr("src") == "/nzq/img/edit_sign.png") {
            editStatus();
        } else {
            updateMyUserInfo();
        }

    })
    $("#myinfo_quit_edit").click(function() {
        $("#myinfo_quit_edit").css("display", "none");
        $("#myinfo_return").css("display", "inline");
        showStatus();
    })
});
