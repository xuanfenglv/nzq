$(() => {
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
