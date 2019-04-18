$(() => {
    // 退出聊天窗口
    $("#msg_number").click(function () {
        imDomObj.chatPage.css("display", "none");
        // 清空聊天气泡
        imDomObj.chatBox.html(emptyStr);
        // 清空当前聊天人
        imParam.onChatXf = null;
    })

    $("#friend").click(function () {
        $("#friendlist").css("display", "inline");
        $("#msg").css({
            "background-color": "#1296db",
            "color": "white"
        });
        $("#friend").css({
            "background": "white",
            "color": "#1296db"
        });
        $("#addfriend").css("display", "none");
        $("#editgroup").css("display", "inline");
    })

    $("#msg").click(function () {
        $("#friendlist").css("display", "none");
        $("#friend").css({
            "background-color": "#1296db",
            "color": "white"
        });
        $("#msg").css({
            "background": "white",
            "color": "#1296db"
        });
        $("#addfriend").css("display", "inline");
        $("#editgroup").css("display", "none");
    })

    $("#newfriend").click(function () {
        $("#application").css("display", "inline");
    })

    // todo dom
    $("#f_detail").click(function () {
        $("#friend_note .note").html($("[xf = " + imParam.onChatXf + "] .friend_msg_name").html());
        $("#group2").html($("[xf = " + imParam.onChatXf + "]").parent().parent().parent().children(".group_detail").children(".group_name").html());
        $("#chat_friendset").css("display", "inline");
    })

    $("#chat_friendset_return").click(function () {
        $("#chat_friendset").css("display", "none");
    })

    $("#application_return").click(function () {
        $("#application").css("display", "none");
    })

    // 打开好友信息页 todo 字段不全
    $("#friend_info").click(function () {
        showRunning("正在获取好友资料");
        getOtherUserInfo(imParam.onChatXf, (data) => {
            endRunning();
            $("#friendinfo").css("display", "inline");
            $("#friendinfo_name").html(data.nickname);
            $("#friendinfo_sex").html(data.sex);
            $("#friendinfo_age").html(data.age);
            $("#friendinfo_birth").html(data.s_birthday);
            $("#friendinfo_phone").html(data.telephone);
            $("#friendinfo_grade").html(data.grade);
            $("#friendinfo_money").html(data.money);
        })
    })
    // 关闭
    $("#friendinfo_return").click(function () {
        $("#friendinfo").css("display", "none");
    })
    // 编辑分组
    // 开 todo 分组用一个map存
    $("#editgroup").click(function () {
        $("#my_groups").html("");
        let group;
        let id;
        let name;
        let group1;
        let groups = document.getElementsByClassName("group_detail");
        for (var i = 0; i < groups.length; i++) {
            group = groups[i];
            id = group.id;
            name = group.getElementsByClassName("group_name")[0].innerHTML;
            if (i == 0) {
                group1 = '<div class="mygroup">'
                    + '<div class="groupname" mygroupid="' + id + '" onclick="showEditGroupName(' + id + ')" >' + name + '</div>'
                    + '</div>';
            } else {
                group1 = '<div class="mygroup">'
                    + '<div class="groupname" mygroupid="' + id + '" onclick="showEditGroupName(' + id + ')" >' + name + '</div>'
                    + '<img class="delete_group" title="删除分组" onclick="showDeleteGroup(' + id + ')" src="/nzq/img/delete_group.png" />'
                    + '</div>';
            }

            $("#my_groups").html($("#my_groups").html() + group1);
            console.log(id + " " + name);
        }
        $("#manage_group").css("display", "inline");
    })
    // 查找好友====================================================================================
    // 开
    $("#addfriend").click(function () {
        $("#search_friend").css("display", "inline");
    })
    // 关
    $("#search_friend_return").click(function () {
        $("#search_friend").css("display", "none");

    })
    // 点击女
    $("#w").click(function () {
        imParam.searchSex = 0;
        $("#w").css({"background-color": "#1296db", "color": "white"});
        $("#m").css({"background-color": "white", "color": "black"});
        $("#n").css({"background-color": "white", "color": "black"});
    })
    // 点击男
    $("#m").click(function () {
        imParam.searchSex = 1;
        $("#m").css({"background-color": "#1296db", "color": "white"});
        $("#w").css({"background-color": "white", "color": "black"});
        $("#n").css({"background-color": "white", "color": "black"});
    })
    // 点击性别不限
    $("#n").click(function () {
        imParam.searchSex = null;
        $("#n").css({"background-color": "#1296db", "color": "white"});
        $("#w").css({"background-color": "white", "color": "black"});
        $("#m").css({"background-color": "white", "color": "black"});
    })
    // 搜索固定账号好友
    $("#search_photo").click(function () {
        var xf = $("#search_friend_xf").val();
        if (xf == "") {
            alert("请输入xf号后再点击查询！！！");
            return;
        } else if (!(/^[1-9]\d{4,9}$/.test(xf))) {
            alert("请输入正确的xf号格式！！！");
            return;
        }
        getOtherUserInfo(xf, data => {
            if (data) {
                genSearchFriend(data);
            } else {
                alert("无搜索结果");
            }
        })
    })
    // 条件搜索好友
    $("#condition_button").click(function () {
        // showRunning("搜索中");
        var nickname = imDomObj.searchFriendNickname.val();
        var grade = imDomObj.searchFriendGrade.val();
        getOtherUserInfos(nickname, imParam.searchSex, grade, data => {
            if (data) {
                genSearchFriends(data);
            } else {
                alert("无搜索结果");
            }
        })
    })
    // 添加好友====================================================================================
    // 关
    $("#add_friend_return").click(function () {
        $("#add_friend").css("display", "none");
    })
    //send add friend application
    $("#add_friend_send").click(function () {

        // 验证消息
        let text = imDomObj.validText.val();
        // 分组id
        let groupId = imDomObj.groupList.val();
        // 好友备注
        let remark = imDomObj.remark.val();

        let sendAppMsg = new SendAppMsg(imParam.receiveApplicationXf,text,groupId,remark);
        imWs.sendMsg(sendAppMsg);
    })
})
