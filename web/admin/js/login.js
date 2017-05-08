/**
 * Created by Administrator on 2017/4/10.
 */
$(function () {
    $("#login").click(function () {
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
    $(".input input").focus(function () {
        $(".error").text('');
    });
    if(empty(account)){
        $(".error").text('用户名不能为空!');
        return false;
    }
    if(empty(password)){
        $(".error").text('密码不能为空!');
        return false;
    }
    var param={
        account:account,
        password:password
    };
    var result=invokeService('/admin/login.do',param,'post');
    if(result.status!=1000){
        prompt(result.message);
        return false;
    }
    var se =  sessionStorage;
    se.account=result.date.account;
    se.loginIp=result.date.loginIp;
    se.loginTime=result.date.loginTime;
    se.loginnum=result.date.loginnum;
    window.location.href='/admin/profitSharing.html'
}