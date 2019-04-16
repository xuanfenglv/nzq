//执行一些初始化方法
$(()=>{
	// 初始化IM websocket
	imWs = new WebSocketUtil("聊天连接",chatWsAddress,new ChatHandlerManager(),chatInitMethod);

	
	
})
