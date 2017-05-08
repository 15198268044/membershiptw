/**
 * Created by Administrator on 2017/4/10.
 */
$(function () {
    $(".forget").click(function(){
        window.location.href="/webpage/forgetPassword.html";
    })
    $("#submit").click(function () {
        loginUp();
    })
    $(document).keydown(function(event) {
        if (event.keyCode == 13) {
            loginUp();
        }
    });
});
function loginUp(){
    var account=$("#account").val();
    var password=$("#password").val();
    $(".floorRight input").focus(function () {
        $(".err").text('')
    });
    if(empty(account)){
        $(".err").text('用户名不能为空');
        return false;
    }
    if(empty(password)){
        $(".err").text('密码不能为空');
        return false;
    }
    var param={
        account:account,
        password:password
    };







    var result=invokeService('/web/vipuser/userlogin.do',param,'post');
    if(result.status!=1000){
        prompt(result.message);
        return false;
    }
    var se =  sessionStorage;
    se.userId=result.date.userId;
    se.username=result.date.vipname;
    se.phone=result.date.phone;
    se.email=result.date.email;
    window.location.href='/webpage/index.html'
}