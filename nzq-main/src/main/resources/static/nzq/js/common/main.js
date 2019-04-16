//执行一些初始化方法
$(()=>{
	// 初始化IM websocket
	let chatWs = new WebSocketUtil("聊天连接",chatWsAddress,new ChatHandlerManager(),chatInitMethod);
	// 初始化好友列表
	
	
})
