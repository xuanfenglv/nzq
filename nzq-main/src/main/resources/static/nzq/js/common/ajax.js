// 全局设置允许发送凭证
$.ajaxSetup({
	xhrFiled: {
		'withCredentials': true
	}
});
// http工具类
var httpUtil = {
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

function ajax(url, data, type, callback) {
	// 蒙层
	//	layer.load(1, {
	//		shade: true
	//	});
	console.log("[发送请求][url:" + url + "][data:" + data + "]");
	$.ajax({
		xhrFields: {
			withCredentials: true
		},
		crossDomain: true,
        contentType: "application/json; charset=utf-8",
		type: type,
		url: url,
		data: data,
		timeout: 1000000,
		dataType: "json",
		success: function(result) {
			console.log("[收到响应][result:" + JSON.stringify(result) + "]");
			let success = CommonUtils.checkRet(result);
			if(success) {
				callback(result);
			}
		},
		complete: function(XMLHttpRequest, status) { //求完成后最终执行参数
			// layer.closeAll('loading');
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