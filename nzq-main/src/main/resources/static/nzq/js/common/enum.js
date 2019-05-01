let ChatInfoType = {
    SEND: 0,
    RECEIVE: 1
}
let UserStatus = {
    OFFLINE: 0,
    ONLINE: 1
}
let PushMsgType = {
    RESPONSE: 0,
    NOTICE: 1
}
let LogLevel = {
    ALL: {id: 0, name: "ALL"},
    TRACE: {id: 1, name: "TRACE"},
    DEBUG: {id: 2, name: "DEBUG"},
    INFO: {id: 3, name: "INFO"},
    WARN: {id: 4, name: "WARN"},
    ERROR: {id: 5, name: "ERROR"},
    FATAL: {id: 6, name: "FATAL"},
    OFF: {id: 7, name: "OFF"}
}
// 申请状态
let AppStatus = {
    SEND: 0,
    ADD: 1,
    REJECT: 2
}
// 编辑分组状态
let EditGroupStatus = {
    ADD: {id: 0, name: '添加分组'},
    EDIT: {id: 1, name: '编辑分组'}
}
let sound = {
    ONLINE: '/nzq/sound/online.wav',
    OFFLINE: '/nzq/sound/offline.wav',
    NEW_MESSAGE: '/nzq/sound/dididi.wav',
    DROP_CHESS: '/nzq/sound/luozi3.mp3'
};

// game===============================
// 房间大小
let MatchSize = {
    NOTHING:0,//无房间
    TWO: 2,// 双人
    THREE: 3// 三人
}
// 房间大小
let RoomSize = {
    NOTHING:0,//无房间
    TWO: 2,// 双人
    THREE: 3// 三人
}

let NzqStatus = {
    离线: 0,
    闲逛: 1,
    房间中: 2,
    匹配中: 3,
    游戏中: 4,
    观战中: 5
}

let Position={
    无:0,
    黑:1,
    白:2,
    红:3
}
