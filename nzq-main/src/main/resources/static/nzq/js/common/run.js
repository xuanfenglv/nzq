//执行一些初始化方法
$(()=>{
    // 拿到xf号
    getMyXf();
	// 初始化IM websocket
	imWs = new WebSocketUtil("聊天连接",chatWsAddress,new ChatHandlerManager(),chatInitMethod);
    // 查询好友申请（发送&接受）
    initApplication();
	
})