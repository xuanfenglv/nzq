let gameVue = new Vue({
    el: '#game',
    data: {
        showGameWarn:false,
        mainMenuButton: 1,
        matchPipeiWait:false,
        showMatchSize:MatchSize.NOTHING,
        showCustomRoom:RoomSize.NOTHING,
        onlineFriendList: [],
        offlineFriendList: [],
        room:{
            black:{
                xf:null,
                name:null,
                showExchange:false,
                exchangeTimeResponse:0,
                exchangeTimeNotice:0
            },
            white:{
                xf:null,
                name:null,
                showExchange:false,
                exchangeTimeResponse:0,
                exchangeTimeNotice:0
            },
            owner:Position.黑,
            myPosition:Position.黑,
            canStart:false
        }
    },
    computed:{
        isMyRoom () {
            return this.owner==this.myPosition;
        }
    },
    methods: {
        changeMainPage: function (pageId) {
            this.mainMenuButton = pageId;
        },
        getNzqStatus: function (status) {
            switch (status) {
                case(0):
                    return '离线';
                case(1):
                    return '闲逛';
                case(2):
                    return '房间中';
                case(3):
                    return '匹配中';
                case(4):
                    return '游戏中';
                case(5):
                    return '观战中';

            }
        },
        showYaoQing: function (status) {
            return NzqStatus.闲逛 == status;
        },
        createRoom: function () {
            let msg = new CreateRoomMsg(gameParams.roomSize);
            gameWs.sendMsg(msg);
        },
        quitRoom:function () {
            let msg = new QuitRoomMsg();
            gameWs.sendMsg(msg);
        },
        getPhotoUrl: function (xf) {
            console.log(xf+"=================")
            return "/nzq/photo/"+xf+".jpg"

        },
        sendInvitation:function (xf) {
            gameWs.sendMsg(new SendInvitationMsg(xf));
        }
    }
});
