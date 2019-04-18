function showTip(title) {
//	console.log("showtime");
    $("#chatTip").stop(true, true);
    $("#chatTip").html(title);
    $("#chatTip").fadeIn().delay(500).fadeOut();
}