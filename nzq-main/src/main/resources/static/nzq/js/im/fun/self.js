let myInfoVue = new Vue({
    el: '#myinfo',
    data: {
        myInfo:{
            id: 10001,
            nickname: "王尼玛",
            sex: 1,
            age: 25,
            birthday: "1995-01-01",
            tel: "13833251234",
            grade: 1,
            money: 120
        },
        editMyInfo:{
            nickname: "王尼玛",
            sex: 1,
            birthday: "1995-01-01",
            tel: "13833251234"
        }
    }
});

function editStatus() {
    $(".myinfo_edit").css("display", "inline");
    $("#myinfo_name_edit").css("display", "inline");
    $("#select_my_sex").css("display", "inline");

    $("#myinfo_name").css("display", "none");
    $("#my_birth").css("display", "none");
    $("#my_phone").css("display", "none");
    $("#my_sex").css("display", "none");

    $("#myinfo_return").css("display", "none");
    $("#myinfo_quit_edit").css("display", "inline");

    $("#myinfo_edit_sign").attr("src", "/nzq/img/submit_sign.png");
}
function showStatus() {
    $("#myinfo_name").css("display", "inline");
    $("#my_birth").css("display", "inline");
    $("#my_phone").css("display", "inline");
    $("#my_sex").css("display", "inline");

    $(".myinfo_edit").css("display", "none");
    $("#myinfo_name_edit").css("display", "none");
    $("#select_my_sex").css("display", "none");

    $("#myinfo_quit_edit").css("display", "none");
    $("#myinfo_return").css("display", "inline");

    $("#myinfo_edit_sign").attr("src", "/nzq/img/edit_sign.png");
}
