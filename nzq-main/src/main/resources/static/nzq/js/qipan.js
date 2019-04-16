var myOrder;
var totalNum = 0;

var qipan = new Array();
for (var i = 0; i < 15; i++) {
	qipan[i] = new Array();
	for (var j = 0; j < 15; j++) { //一维数组里面每个元素数组可以包含的数量p，p也是一个变量；
		qipan[i][j] = 0;
	}
}

function playDa() {
	var audio = document.createElement("audio");
	audio.src = "/nzq/sound/luozi3.mp3";
	audio.play();
}
function dropIn5(row, column) {
	//alert("进入控制");
	console.log("对方下在："+row+"行"+column+"列");
	var board = document.getElementById("board");
	
	playDa();
	if (totalNum % 2 == 0) {
		//alert("control1");
		board.rows[row].cells[column].style.backgroundImage = "url(/nzq/qizi_img/black.png)";
		qipan[row][column] = 1;
		totalNum += 1;
//		showQipan();
		if (isWin(row, column)) {
			daojishi_bar_stop();
			$("#game_fail").css("display", "inline");
		} else {
			daojishi3();
		}
		$("#player1_daojishi").css("display", "none");
		clearInterval(Time1);

	} else if (totalNum % 2 == 1) {
		//alert("control2");
		board.rows[row].cells[column].style.backgroundImage = "url(/nzq/qizi_img/white.png)";
		qipan[row][column] = 2;
		totalNum += 1;
//		showQipan();
		if (isWin(row, column)) {
			daojishi_bar_stop();
			$("#game_fail").css("display", "inline");
		} else {
			daojishi3();
		}
		$("#player1_daojishi").css("display", "none");
		clearInterval(Time1);
	}
}
function dropIn4(row, column) {
	//alert("进入控制");
	console.log("对方下在："+row+"行"+column+"列");
	var board = document.getElementById("board");
	
	playDa();
	if (totalNum % 3 == 0) {
		//alert("control1");
		board.rows[row].cells[column].style.backgroundImage = "url(/nzq/qizi_img/black.png)";
		qipan[row][column] = 1;
		totalNum += 1;
//		showQipan();
		if (isWin3(row, column)) {
			daojishi_bar_stop();
			$("#game_fail").css("display", "inline");
		} else {
			controlDaojishi();
		}
	} else if (totalNum % 3 == 1) {
		//alert("control2");
		board.rows[row].cells[column].style.backgroundImage = "url(/nzq/qizi_img/white.png)";
		qipan[row][column] = 2;
		totalNum += 1;
//		showQipan();
		if (isWin3(row, column)) {
			daojishi_bar_stop();
			$("#game_fail").css("display", "inline");
		} else {
			controlDaojishi();
		}
	} else if (totalNum % 3 == 2) {
		//alert("control2");
		board.rows[row].cells[column].style.backgroundImage = "url(/nzq/qizi_img/red.png)";
		qipan[row][column] = 3;
		totalNum += 1;
//		showQipan();
		if (isWin3(row, column)) {
			daojishi_bar_stop();
			$("#game_fail").css("display", "inline");
		} else {
			controlDaojishi();
		}
	}
	closeBeforeDaojishi();
}

function controlDaojishi() {
	if(myLocation=="black") {
		if (totalNum % 3 == 0) {
			daojishi3();
		} else {
			daojishi2();
		}
	} else if(myLocation=="white") {
		if (totalNum % 3 == 0) {
			daojishi1();
		} else {
			daojishi3();
		}
	} else {
		if (totalNum % 3 == 1) {
			daojishi2();
		} else {
			daojishi3();
		}
	}
}
function controlDaojishiAfterIDrop() {
	if(myLocation=="white") {
		daojishi2();
	} else {
		daojishi1();
	}
}
function closeBeforeDaojishi() {
	if(myLocation=="red") {
		if (totalNum % 3 == 2) {
			$("#player2_daojishi").css("display", "none");
			clearInterval(Time2);
		} else {
			$("#player1_daojishi").css("display", "none");
			clearInterval(Time1);
		}
	} else {
		if (totalNum % 3 == 0) {
			$("#player2_daojishi").css("display", "none");
			clearInterval(Time2);
		} else {
			$("#player1_daojishi").css("display", "none");
			clearInterval(Time1);
		}
	}
}
$(document).ready(function() {
	$("td").click(function() {
		var row = $(this).parent().index();
		var column = $(this).index();

		if(qipan[row][column] == 0) { //空位置
			if(roomModel == 2 ) {
			if(totalNum % 2 == myOrder) {
				playDa();
				if(myOrder == 0) {
					$(this).css("background-image","url(/nzq/qizi_img/black.png)");
					qipan[row][column] = 1;
					nzqGameWebSocket.send("12" + row + "." + column);
					totalNum += 1;
					showQipan(); 
					if(isWin(row, column)) {
						daojishi_bar_stop();
						//nzqGameWebSocket.send("16");
						$("#game_success").css("display", "inline");
					} else {
						daojishi1();
					}
				} else if(myOrder == 1) {
					$(this).css("background-image", "url(/nzq/qizi_img/white.png)");
					qipan[row][column] = 2;
					nzqGameWebSocket.send("12" + row + "." + column);
					totalNum += 1;
					showQipan();
					if(isWin(row, column)) {
						daojishi_bar_stop();
						//nzqGameWebSocket.send("16");
						$("#game_success").css("display", "inline");
					} else {
						daojishi1();
					}
				}
				$("#player3_daojishi").css("display", "none");
				clearInterval(Time3);

			} else {
				if(totalNum % 2 == 0) {
//					alert("请等待黑方下棋");
					gameWarn("请等待黑方下棋")
				}
				if(totalNum % 2 == 1) {
//					alert("请等待白方下棋");
					gameWarn("请等待白方下棋")
				}
			}
			} else {//四子棋模式
				if (totalNum % 3 == myOrder) {
					if (myOrder == 0) {
						$(this).css("background-image",
								"url(/nzq/qizi_img/black.png)");
						qipan[row][column] = 1;
						nzqGameWebSocket.send("27"+row + "." + column);
						totalNum += 1;
						if (isWin3(row, column)) {
							daojishi_bar_stop();
							//nzqGameWebSocket.send("16");
							$("#game_success").css("display", "inline");
						} else {
							controlDaojishiAfterIDrop();
						}
						
					} else if (myOrder == 1) {
						$(this).css("background-image",
								"url(/nzq/qizi_img/white.png)");
						qipan[row][column] = 2;
						nzqGameWebSocket.send("27"+row + "." + column);
						totalNum += 1;
						if (isWin3(row, column)) {
							daojishi_bar_stop();
							//nzqGameWebSocket.send("16");
							$("#game_success").css("display", "inline");
						} else {
							controlDaojishiAfterIDrop();
						}
						
					} else if (myOrder == 2) {
						$(this).css("background-image",
								"url(/nzq/qizi_img/red.png)");
						qipan[row][column] = 3;
						nzqGameWebSocket.send("27"+row + "." + column);
						totalNum += 1;
						if (isWin3(row, column)) {
							daojishi_bar_stop();
							//nzqGameWebSocket.send("16");
							$("#game_success").css("display", "inline");
						} else {
							controlDaojishiAfterIDrop();
						}
					}
					$("#player3_daojishi").css("display", "none");
					clearInterval(Time3);

				} else {
					if (totalNum % 3 == 0) {
//						alert("请等待黑方下棋");
						gameWarn("请等待黑方下棋")
					}
					if (totalNum % 3 == 1) {
//						alert("请等待白方下棋");
						gameWarn("请等待白方下棋")
					}
					if (totalNum % 3 == 2) {
//						alert("请等待红方下棋");
						gameWarn("请等待红方下棋")
					}
				}
			}

		}

	})
})
function reSet() {
	var board = document.getElementById("board");

	for (var i = 0; i < board.rows.length; i++) {
		for (var j = 0; j < board.rows[i].cells.length; j++) {
			board.rows[i].cells[j].style.background = "";
		}
	}
	for (var i = 0; i < 15; i++) {
		for (var j = 0; j < 15; j++) {
			//一维数组里面每个元素数组可以包含的数量p，p也是一个变量；
			qipan[i][j] = 0;
		}
	}
	totalNum = 0;
}
function isWin(row, column) {
	if(isRowWin(row, column) || isColWin(row, column) || isX1Win(row, column) || isX2Win(row, column)) {
		return true;
	} else {
		return false;
	}
}

function isRowWin(row, column) {
	var sum = 1;
	var c = qipan[row][column];
	for(var l = 1; l <= getMin2(4, column - 0); l++) {
		if(c == qipan[row][column - l]) {
			sum += 1;
		} else {
			break;
		}
	}
	if(sum == 5) {
		return true;
	}

	for(var r = 1; r <= getMin2(4, 14 - column); r++) {
		if(c == qipan[row][column + r]) {
			sum += 1;
		} else {
			break;
		}
	}
	if(sum > 4) {
		return true;
	} else {
		return false;
	}
}

function isColWin(row, column) {
	var sum = 1;
	var c = qipan[row][column];
	for(var t = 1; t <= getMin2(4, (row - 0)); t++) {
		if(c == qipan[row - t][column]) {
			sum += 1;
		} else {
			break;
		}
	}
	if(sum == 5) {
		return true;
	}

	for(var bb = 1; bb <= getMin2(4, 14 - row); bb++) {
		if(c == qipan[row + bb][column]) {
			sum += 1;
		} else {
			break;
		}
	}
	if(sum > 4) {
		return true;
	} else {
		return false;
	}
}

function isX1Win(row, column) {
	var sum = 1;
	var c = qipan[row][column];
	for(var l = 1; l <= getMin3(4, column - 0, row - 0); l++) { //三元式不加括号，当行列都是1时，等于2
		if(c == qipan[row - l][column - l]) {
			sum += 1;
		} else {
			break;
		}
	}

	if(sum == 5) {
		return true;
	}

	for(var r = 1; r <= getMin3(4, 14 - column, 14 - row); r++) {
		if(c == qipan[row + r][column + r]) {
			sum += 1;
		} else {
			break;
		}
	}

	if(sum > 4) {
		return true;
	} else {
		return false;
	}
}

function isX2Win(row, column) {
	var sum = 1;
	var c = qipan[row][column];
	for(var l = 1; l <= getMin3(4, 14 - column, row - 0); l++) {
		if(c == qipan[row - l][column + l]) {
			sum += 1;
		} else {
			break;
		}
	}

	if(sum == 5) {
		return true;
	}

	for(var r = 1; r <= getMin3(4, column - 0, 14 - row); r++) {
		if(c == qipan[row + r][column - r]) {
			sum += 1;
		} else {
			break;
		}
	}
	if(sum > 4) {
		return true;
	} else {
		return false;
	}
}

function getMin2(no1, no2) {
	return(no1 <= no2 ? no1 : no2);
}

function getMin3(no1, no2, no3) {
	return getMin2(getMin2(no1, no2), no3);
}

function isWin3(row, column) {
	if (isRowWin3(row, column) || isColWin3(row, column)
			|| isX1Win3(row, column) || isX2Win3(row, column)) {
		return true;
	} else {
		return false;
	}
}
function isRowWin3(row, column) {
	var sum = 1;
	var c = qipan[row][column];
	//			alert("row:"+c)
	for (var l = 1; l <= getMin2(3, column - 0); l++) {
		if (c == qipan[row][column - l]) {
			sum += 1;
			//					alert("l:"+sum)
		} else {
			break;
		}
	}

	if (sum == 4) {
		return true;
	}
	
	for (var r = 1; r <= getMin2(3, 14 - column); r++) {
		if (c == qipan[row][column + r]) {
			sum += 1;
			//					alert("r:"+sum)
		} else {
			break;
		}
	}
	if (sum > 3) {
		return true;
	} else {
		return false;
	}
}

function isColWin3(row, column) {
	var sum = 1;
	var c = qipan[row][column];
	//			alert("col:"+c)
	for (var t = 1; t <= getMin2(3, (row - 0)); t++) {
		if (c == qipan[row - t][column]) {
			sum += 1;
			//					alert("t:"+sum)
		} else {
			break;
		}
	}

	if (sum == 4) {
	console.log("竖胜利："+sum);
		return true;
	}
	
	for (var b = 1; b <= getMin2(3, 14 - row); b++) {
		if (c == qipan[row + b][column]) {
			sum += 1;
			//					alert("b:"+sum)
		} else {
			break;
		}
	}
	if (sum > 3) {
		return true;
	} else {
		return false;
	}
}

function isX1Win3(row, column) {
	var sum = 1;
	var c = qipan[row][column];
	//			alert((column-0<row-0)?column-0:row-0);
	for (var l = 1; l <= getMin3(3, column - 0, row - 0); l++) {//三元式不加括号，当行列都是1时，等于2
		//				alert("l:"+l);
		if (c == qipan[row - l][column - l]) {
			sum += 1;
			//					alert("X1t:"+sum)
		} else {
			break;
		}
	}
	//			alert("X1t1 sum:"+sum)
	if (sum == 4) {
		return true;
	}
	
		for (var r = 1; r <= getMin3(3, 14 - column, 14 - row); r++) {
			if (c == qipan[row + r][column + r]) {
				sum += 1;
				//					alert("X1b:"+sum)
			} else {
				break;
			}
		}

	//			alert("X1t2 sum:"+sum)
	if (sum > 3) {
		//				alert("X1t3 sum:"+sum)
		return true;
	} else {
		return false;
	}
}

function isX2Win3(row, column) {
	var sum = 1;
	var c = qipan[row][column];
	//			alert("X2:"+c)
	for (var l = 1; l <= getMin3(4, 14 - column, row - 0); l++) {
		if (c == qipan[row - l][column + l]) {
			sum += 1;
			//					alert("l:"+sum)
		} else {
			break;
		}
	}
	
	if (sum == 4) {
		return true;
	}
	
	for (var r = 1; r <= getMin3(3, column - 0, 14 - row); r++) {
		if (c == qipan[row + r][column - r]) {
			sum += 1;
			//					alert("r:"+sum)
		} else {
			break;
		}
	}
	if (sum > 3) {
		return true;
	} else {
		return false;
	}
}
function showQipan() {
	var hang = "";
	for (var ii = 0; ii < 15; ii++) {
		console.log("第"+ii+"行");
		for (var jj = 0; jj < 15; jj++) {
			hang += qipan[ii][jj] + "  ";
		}
		console.log(hang);
		hang = "";
	}
}