$(function () {

    $(".forget").click(function(){
        window.location.href="/webpage/login.html";
    })
    $("#submit").click(function () {
        forgetPassword();
    })
    $(document).keydown(function(event) {
        if (event.keyCode == 13) {
            forgetPassword();
        }
    });
    var regphone = /(^1[3|4|5|7|8][0-9]{9}$)/;
    //    发送验证码
    $("#sendCode").click(function(){
        var phone = $("#phone").val();
        if(empty(phone)){
            prompt("请先输入手机号!");
            return;
        }else if(!regphone.test(phone)){
            prompt("手机号格式不正确!");
            return;
        }
        var param =  {
            phone:phone,
            type:0
        };
        var data=invokeService('/web/sms/sendSms.do',param,'post');
        if(data.status!=1000){
            prompt(data.message);
            return false;
        }
        sendMessages();
    });
    var time=60;
    function sendMessages(){
        $("#sendCode").attr("disabled",true).html(time+"秒后重发");
        time=time-1;
        var clearsend=setTimeout(sendMessages,1000);
        if(time==0){
            clearTimeout(clearsend);
            $('#sendCode').attr("disabled",false);
            $("#sendCode").html("发送验证码");
            time=60;
        }
    }
});

function forgetPassword(){
    var regphone = /(^1[3|4|5|7|8][0-9]{9}$)/;
    var phone=$("#phone").val();
    var phoneCode=$("#phoneCode").val();
    var password=$("#password").val();
    if(empty(phone)){
        $(".error").html("手机号不能为空!");
        return;
    }else if(!regphone.test(phone)){
        $(".error").html("手机号格式不正确!");
        return;
    } else{
        $(".error").html("");
    }
    var param =  {
        phone:phone,
        code:phoneCode
    };
    var data=invokeService('/web/sms/checkSms.do',param,'post');
    if(empty(phoneCode)){
        $(".error").html("手机验证码不能为空!");
        return;
    }else if(data.status!=1000){
        $(".error").html(data.message);
        return;
    }else{
        $(".error").html("");
    }
    if(empty(password)){
        $(".error").html("新密码不能为空!");
        return;
    }else{
        $(".error").html("");
    }
    var forgotparam =  {
        phone:phone,
        password:password
    };
    var forgotdata=invokeService('/web/pass/forgot.do',forgotparam,'post');
    if(forgotdata.status!=1000){
        prompt(forgotdata.message);
        return false;
    }
    prompt("修改成功,请去登录!",function(){
        window.location.href="/webpage/login.html";
    });
}
