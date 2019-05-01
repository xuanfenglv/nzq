$(() => {
    // 匹配模式-四子棋
    $("#siziqi").click(function () {
        gameParams.roomSize = RoomSize.THREE;

        $("#siziqi .siziqi_sign").attr("src", "/nzq/img/siziqi_sign3.png");
        $("#wuziqi .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign.png");

        $("#wuziqi_introduce").css("visibility", "hidden");
        $("#siziqi_introduce").css("visibility", "visible");
        $("#game_pattern").css("left", "650px");

    })
    // 匹配模式-五子棋
    $("#wuziqi").click(function () {
        gameParams.roomSize = RoomSize.TWO;

        $("#wuziqi .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign2.png");
        $("#siziqi .siziqi_sign").attr("src", "/nzq/img/siziqi_sign.png");

        $("#wuziqi_introduce").css("visibility", "visible");
        $("#siziqi_introduce").css("visibility", "hidden");
        $("#game_pattern").css("left", "50px");

    })

    // 好友约战-四子琪
    $("#siziqi_f").click(function () {
        gameParams.roomSize = RoomSize.THREE;
        $("#siziqi_f .siziqi_sign").attr("src", "/nzq/img/siziqi_sign3.png");
        $("#wuziqi_f .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign.png");

        $("#wuziqi_introduce_f").css("visibility", "hidden");
        $("#siziqi_introduce_f").css("visibility", "visible");

    })

    // 好友约战-五子棋
    $("#wuziqi_f").click(function () {
        gameParams.roomSize = RoomSize.TWO;
        $("#wuziqi_f .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign2.png");
        $("#siziqi_f .siziqi_sign").attr("src", "/nzq/img/siziqi_sign.png");

        $("#wuziqi_introduce_f").css("visibility", "visible");
        $("#siziqi_introduce_f").css("visibility", "hidden");
    })

    // hover事件
    $("#siziqi").hover(function () {
            //鼠标经过的操作
            $("#siziqi .siziqi_sign").attr("src", "/nzq/img/siziqi_sign3.3.png");
        },
        function () {
            //鼠标离开的操作
            if ($("#siziqi_introduce").css("visibility") == "hidden") {
                $("#siziqi .siziqi_sign").attr("src", "/nzq/img/siziqi_sign.png");
            } else {
                $("#siziqi .siziqi_sign").attr("src", "/nzq/img/siziqi_sign3.png");
            }

        });


    $("#wuziqi").hover(function () {
            //鼠标经过的操作
            $("#wuziqi .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign2.2.png");
        },
        function () {
            //鼠标离开的操作
            if ($("#wuziqi_introduce").css("visibility") == "hidden") {
                $("#wuziqi .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign.png");
            } else {
                $("#wuziqi .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign2.png");
            }
        });


    $("#siziqi_f").hover(function () {
            //鼠标经过的操作
            $("#siziqi_f .siziqi_sign").attr("src", "/nzq/img/siziqi_sign3.3.png");
        },
        function () {
            //鼠标离开的操作
            if ($("#siziqi_introduce_f").css("visibility") == "hidden") {
                $("#siziqi_f .siziqi_sign").attr("src", "/nzq/img/siziqi_sign.png");
            } else {
                $("#siziqi_f .siziqi_sign").attr("src", "/nzq/img/siziqi_sign3.png");
            }

        });


    $("#wuziqi_f").hover(function () {
            //鼠标经过的操作
            $("#wuziqi_f .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign2.2.png");
        },
        function () {
            //鼠标离开的操作
            if ($("#wuziqi_introduce_f").css("visibility") == "hidden") {
                $("#wuziqi_f .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign.png");
            } else {
                $("#wuziqi_f .wuziqi_sign").attr("src", "/nzq/img/wuziqi_sign2.png");
            }
        });

    $("#confirm_game").click(function () {
        if (pPModel == 2) {
            nzqGameWebSocket.send("32");
        } else {
            nzqGameWebSocket.send("35");
        }

        $("#match_pipei_wait").css("display", "inline");
        $("#pp_wait_count").html("00:00");
        var timer = 0;
        var min;
        var s;
        var dianjibiao = "";
        TimePP = setInterval(function () {
            timer += 1;
            if (timer < 10) {
                dianjibiao = "00:0" + timer;
            } else if (timer < 60) {
                dianjibiao = "00:" + timer;
            } else {
                min = Math.floor(timer / 60);
                s = timer % 60;
                if (min < 10) {
                    if (s < 10) {
                        dianjibiao = "0" + min + ":0" + s;
                    } else {
                        dianjibiao = "0" + min + ":" + s;
                    }
                } else {
                    $("#match_pipei_wait").css("display", "none");
                    alert("太久未匹配到对手，已退出匹配队列");
                    clearInterval(TimePP);
                }
            }
            $("#pp_wait_count").html(dianjibiao);
        }, 1000);
    })
    $(".quit_game").click(function () {
        $("#play").css("display", "none");
        $("#person").css("display", "none");
        $("#commodity").css("display", "none");
        $("#store").css("display", "none");

        $("#homepage").css("display", "inline");
    })
})
