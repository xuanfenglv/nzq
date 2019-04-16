$(()=>{
    $("#msg_number").click(function() {
        $("[xf_no=" + chat_xf + "]").css("display", "none");
        $("#chat_detail").css("display", "none");
    })
})
