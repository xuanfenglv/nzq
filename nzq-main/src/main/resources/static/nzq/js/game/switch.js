let gameSwitchVue = new Vue({
    el: '#game',
    data:{
        mainMenuButton:1
    },
    methods:{
        changeMainPage:function (pageId) {
            this.mainMenuButton=pageId;
        }
    }
});
