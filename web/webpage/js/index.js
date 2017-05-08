$(function(){
    var se =  sessionStorage;
    $("#userName").html(se.username);
    $("#nav").html(getMenus());
    $("nav ul li").click(function(){
        var i =  $("nav ul li").index(this);
        $("nav ul li").removeClass("active").css({"border-left":"none","width":"200px"});
        $("nav ul li").eq(i).addClass("active").css({"border-left":"2px solid #DA3644","width":"198px"});
    });
    $(".exit").click(function () {
        prompt2('退出当前账号', function () {
            var data=invokeService('/web/vipuser/exitLogin.do',{},'get');
            if(data.status!=1000){
                prompt("退出失败!");
                return false;
            }
            window.location.href="/webpage/login.html";
        })
    })
    if(empty(se.userId)){
        window.location.href="/webpage/login.html";
    }
});

// web/vipuser/bindEmail.do
// web/sms/sendEmail.do email POST