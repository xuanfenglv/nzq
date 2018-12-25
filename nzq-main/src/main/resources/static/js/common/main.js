$(()=>{
	let chatWs = new WebSocketUtil("聊天连接",chatWsAddress,new ChatHandlerManager(),chatInitMethod);
	
})
