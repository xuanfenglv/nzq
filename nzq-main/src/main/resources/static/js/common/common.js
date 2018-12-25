let CommonUtils = {
	checkRet: function(msg) {
		let ret = data.ret;
		switch(ret) {
			case 1:
				return true;
			case 2:
				//未登录
				layer.msg(tip, {
					icon: 0,
					time: 1000
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
		return false;
	}
}

class ChatInfo {
	constructor(xf,type,timestamp,text) {
		this.xf = xf;
		this.type=type;
		this.timestamp=timestamp;
		this.text=text;
	}
}
let chatInfoUtil = {
	map:new Map(),
	add:function(chatInfo) {
		let chatList = this.map.get(chatInfo.xf);
		if(!chaList){
			chatList = [];
			this.map.set(chatInfo.xf,chatList);
		}
		chatList.unshift(chatInfo);
	}
}
