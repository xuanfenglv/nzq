
class GameHandlerManager {
    constructor() {
        this.handlerMapping = new Map();

        this.handlerMapping.set(1, new GameInitHandler());
        this.handlerMapping.set(2, new CreateRoomHandler());
        this.handlerMapping.set(10, new QuitRoomHandler());
        this.handlerMapping.set(24, new NzqGameFriendStatusChangeHandler());
    }
    handle(msg) {
        let handler = this.handlerMapping.get(msg.msgId);
        if(!handler) {
            alert("不存在的处理器");
            return false;
        }
        handler.handle(msg);
    }
}
