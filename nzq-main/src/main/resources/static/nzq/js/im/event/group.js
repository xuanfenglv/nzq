$(() => {
    // 分组管理
    // 关
    $("#end_manage_group").click(function() {
        $("#manage_group").css("display","none");
    })
    // 编辑分组-添加分组
    $("#add_group_text").click(function() {
        // 添加分组和编辑分组用的同一个组件
        imParam.editGroupStatus = EditGroupStatus.ADD.id;
        $("#edit_groupname_menu_name").html(EditGroupStatus.ADD.name);
        $("#edit_groupname_menu_input").val(emptyStr);
        $("#edit_groupname").css("display","inline");
    })
    // 取消编辑分组
    $("#edit_groupname_menu_quit").click(function() {
        $("#edit_groupname").css("display","none");
    })
    // 确认编辑分组
    $("#edit_groupname_menu_confirm").click(function() {
        editGroup();
    })
    // 删除分组
    $("#del_group_confirm").click(function() {
        deleteFriendGroup(imParam.onEditGroupId);
    })
    // 删除分组页-关
    $("#del_group_quit").click(function() {
        $("#del_group").css("display","none");
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


})
