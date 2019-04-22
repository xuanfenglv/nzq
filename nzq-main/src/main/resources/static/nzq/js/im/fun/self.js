let myInfoVue = new Vue({
    el: '#myinfo',
    data: {
        myInfo:{
            id: 10001,
            nickname: "王尼玛",
            sex: 1,
            age: 25,
            birthday: "1995-01-01",
            tel: "13833251234",
            grade: 1,
            money: 120
        },
        editMyInfo:{
            nickname: "王尼玛",
            sex: 1,
            birthday: "1995-01-01",
            tel: "13833251234"
        },
        openSelfInfo:true,
        isShow:true
    },
    methods:{
        quitMyInfo:function () {
           this.openSelfInfo=false;
        },
        editMyInfo:function () {
            if(this.isShow) {
                this.isShow=false;
            } else {
                updateMyUserInfo();
            }
        },
        quitEditMyInfo:function () {
            this.isShow=true;
        }
    }
});

function editStatus() {
    myInfoVue.isShow=false;
}
function showStatus() {
    myInfoVue.isShow=true;
}
