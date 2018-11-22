$.ajaxSetup({
	xhrFiled: {
		'withCredentials': true
	}
});
let httpUtil = {
	get: function(url, data, callback) {
		ajax(url, data, "get", callback);
	},
	post: function(url, data, callback) {
		ajax(url, data, "post", callback);
	},
	put: function(url, data, callback) {
		ajax(url, data, "put", callback);
	},
	delete: function(url, data, callback) {
		ajax(url, data, "delete", callback);
	}
}

//function get(url, data, callback) {
//	ajax(url, data, "get", callback);
//}
//
//function post(url, data, callback) {
//	ajax(url, data, "post", callback);
//}
//
//function put(url, data, callback) {
//	ajax(url, data, "put", callback);
//}
//
//function delete(url, data, callback) {
//	ajax(url, data, "delete", callback);
//}

function getService(data, callback) {
	get(server.service, {
		msg: JSON.stringify(data)
	}, callback);
}

function postService(data, callback) {
	post(server.service, {
		msg: JSON.stringify(data)
	}, callback);
}

function ajax(url, data, type, callback) {
	layer.load(1, {
		shade: true
	});

	console.log("发送请求：" + data);
	$.ajax({
		xhrFields: {
			withCredentials: true
		},
		crossDomain: true,
		type: type,
		url: url,
		data: data,
		timeout: 100000,
		dataType: "json",
		success: function(data) {
			console.log("收到响应：" + JSON.stringify(data));
			let ret = data.ret;
			let tip = data.errmsg;
			switch(ret) {
				case 1:
					if(callback) {
						callback(data);
					}
					break;
				case 2:
					//未登录
					layer.msg(tip, {
						icon: 0,
						time: 1000
					}, function() {
						location.href = loginPage;
					});
					break;
				case 3:
					// 参数不正确
					layer.msg(tip, {
						icon: 2,
						time: 1000
					});
					break;
				case 4:
					// 权限不足
					layer.msg(tip, {
						icon: 5,
						time: 1000
					});
					break;
				case 5:
					// 需要备注
					layer.msg(tip, {
						icon: 3,
						time: 1000
					});
					break;
				case 6:
					// 已被封号
					layer.msg('已被封号', {
						icon: 4,
						time: 1000
					});
					break;
				case 7:
					// 登录失败
					layer.msg(tip, {
						icon: 2,
						time: 1000
					});
					break;
				case 8:
					// 服务器故障
					layer.msg(tip, {
						icon: 2,
						time: 1000
					});
					break;
				case 9:
					// 服务器故障
					layer.msg(tip, {
						icon: 2,
						time: 1000
					});
					break;
				default:
					break;
			}
		},
		complete: function(XMLHttpRequest, status) { //求完成后最终执行参数
			layer.closeAll('loading');
			// 设置timeout的时间，通过检测complete时status的值判断请求是否超时，如果超时执行响应的操作
			if(status == 'timeout') { //超时,status还有success,error等值的情况
				layer.msg('请求超时', {
					icon: 5,
					time: 1000
				});
			} else if(status == 'error') {
				layer.msg('服务器故障', {
					icon: 2,
					time: 1000
				});
			}
		}
	});
}

function getServiceWithCheck(msg, callback) {
	let check = msg.checkProperty(msg);
	if(!check) return;
	getService(msg, callback);
}

function postServiceWithCheck(msg, callback) {
	let check = msg.checkProperty(msg);
	if(!check) return;
	postService(msg, callback);
}