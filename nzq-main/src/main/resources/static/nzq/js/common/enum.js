let ChatInfoType={
    SEND:0,
    RECEIVE:1
}
let UserStatus={
    OFFLINE:0,
    ONLINE:1
}
let PushMsgType={
    RESPONSE:0,
    NOTICE:1
}
let LogLevel={
    ALL:{id:0,name:"ALL"},
    TRACE:{id:1,name:"TRACE"},
    DEBUG:{id:2,name:"DEBUG"},
    INFO:{id:3,name:"INFO"},
    WARN:{id:4,name:"WARN"},
    ERROR:{id:5,name:"ERROR"},
    FATAL:{id:6,name:"FATAL"},
    OFF:{id:7,name:"OFF"}
}
// 申请状态
let AppStatus={
    SEND:0,
    ADD:1,
    REJECT:2
}