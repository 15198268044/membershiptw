var menu={
    getMenu:[
    {
        text:"我的收入",leaf:true,css:"./css/withdrawals.css",url:"./withdrawals.html",alias:"income",icon:"&#xe63a;",children:[]
    },
    {
        text:"提现账号",leaf:true,css:"./css/cash.css",url:"./cash.html",alias:"cash",icon:"&#xe60a;",children:[]
    },
    {
        text:"收入概览",leaf:true,css:"./css/income.css",url:"./income.html",alias:"incomeOverview",icon:"&#xe612;",children:[]
    },
    {
        text:"提现记录",leaf:true,css:"./css/withdrawalsRecords.css",url:"./withdrawalsRecords.html",alias:"withdrawalsRecords",icon:"&#xe603;",children:[]
    },
    {
        text:"我的推荐",leaf:false,css:"./css/recommend.css",url:"./recommend.html",alias:"recommend",icon:"&#xe602;",children:[]
    },
    {
        text:"修改密码",leaf:false,css:"./css/modifyPassword.css",url:"./modifyPassword.html",alias:"modifyPassword",icon:"&#xe610;",children:[]
    },
    {
        text:"个人资料",leaf:false,css:"./css/personalData.css",url:"./personalData.html",alias:"personalData",icon:"&#xe629;",children:[]
    }
]}


// 获取菜单
function getMenus(){
    var html='';
    html="<div class='title'>财务 / Financial</div>"+
        "<ul>"
    for(var i=0;i<menu.getMenu.length;i++){
        if(menu.getMenu[i].leaf){
            html+="<li><a href='#/"+menu.getMenu[i].alias+"'>"+
                "<i class='iconfont'>"+menu.getMenu[i].icon+"</i>"+
                "<span>"+menu.getMenu[i].text+"</span>"+
                "</li></a>"
        }
    }
    html+="</ul>"+
        "<div class='title'>个人设置 / User</div>"+
        "<ul>"
    for(var j=0;j<menu.getMenu.length;j++){
        if(!menu.getMenu[j].leaf){
            html+=
                "<li><a href='#/"+menu.getMenu[j].alias+"'>"+
                "<i class='iconfont'>"+menu.getMenu[j].icon+"</i>"+
                "<span>"+menu.getMenu[j].text+"</span>"+
                "</li></a>"
        }
    }
    html+="</ul>"
    return html;
}
// 路由跳转
function Router(){
    this.routes = {};
    this.curUrl = '';
    this.route = function(path, callback){
        this.routes[path] = callback || function(){};
    };
    this.refresh = function(){
        this.curUrl = location.hash.slice(1) || '/';
        this.routes[this.curUrl]();
    };
    this.init = function(){
        window.addEventListener('load', this.refresh.bind(this), false);
        window.addEventListener('hashchange', this.refresh.bind(this), false);
    }
}

var R = new Router();
R.init();
R.route('/', function() {
    getpage(menu.getMenu[0].url,menu.getMenu[0].css);
});
R.route('/income', function() {
    getpage(menu.getMenu[0].url,menu.getMenu[0].css)
});
R.route('/cash', function() {
    getpage(menu.getMenu[1].url,menu.getMenu[1].css)
});
R.route('/incomeOverview', function() {
    getpage(menu.getMenu[2].url,menu.getMenu[2].css)
});
R.route('/withdrawalsRecords', function() {
    getpage(menu.getMenu[3].url,menu.getMenu[3].css)
});
R.route('/recommend', function() {
    getpage(menu.getMenu[4].url,menu.getMenu[4].css)
});
R.route('/modifyPassword', function() {
    getpage(menu.getMenu[5].url,menu.getMenu[5].css)
});
R.route('/personalData', function() {
    getpage(menu.getMenu[6].url,menu.getMenu[6].css)
});
R.route('/profit', function() {
    getpage("/webpage/profit.html","/webpage/css/profit.css")
});
// 跳转页面

function getpage(url,cssUrl){
    $("#content").html("");
    $("link[id=localCss]").remove();
    loadCss(cssUrl);
    function loadCss(cssUrl){
        var link = document.createElement( "link" );
        link.type = "text/css";
        link.rel = "stylesheet";
        link.id = "localCss";
        link.href = cssUrl;
        document.getElementsByTagName( "head" )[0].appendChild( link );
    }
    $.get(url,function(data){
        $("#content").html(data);
    });
}