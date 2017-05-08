$(function(){
    var regphone = /(^1[3|4|5|7|8][0-9]{9}$)/;
    // 身份证
    var regcard=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
    var imgID=34;
    // 验证手机号是否存在
    $("#phone").blur(function(){
        var verifyPhone=verifyPhones();
        if(verifyPhone == false){
            $(".error").html("该手机号已存在,请重新输入!");
            return;
        }else{
            $(".error").html("");
        }
    });

    // 验证用户名是否存在
    $("#userName").blur(function(){
        var verifyUserName=verifyUserNames();
        if(verifyUserName == false){
            $(".error").html("该用户名已存在,请重新输入!");
            return;
        }else{
            $(".error").html("");
        }
    });

    $("#nextOne").click(function(){
        var phone=$("#phone").val();
        var imgCode=$("#imgCode").val();
        var phoneCode=$("#phoneCode").val();
        var userName=$("#userName").val();
        var password=$("#password").val();
        var passwordAgain=$("#passwordAgain").val();
        var tjrId=$("#tjrId").val();
        if(empty(phone)){
            $(".error").html("手机号不能为空!");
            return;
        }else if(!regphone.test(phone)){
            $(".error").html("手机号格式不正确!");
            return;
        } else{
            $(".error").html("");
        }
        var imgCodeParam =  {
            "smscode":imgCode
        };
        var imgCodeData=invokeService('/web/sms/checkGraphical.do',imgCodeParam,'post');
        if(empty(imgCode)){
            $(".error").html("图形验证码不能为空!");
            return;
        }else if(imgCodeData.status!=1000){
            $(".error").html("图形验证码错误!");
            return;
        }else{
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
        if(empty(userName)){
            $(".error").html("用户名不能为空!");
            return;
        }else{
            $(".error").html("");
        }
        if(empty(password)){
            $(".error").html("密码不能为空!");
            return;
        }else{
            $(".error").html("");
        }
        if(empty(passwordAgain)){
            $(".error").html("再次输入密码不能为空!");
            return;
        }else if(passwordAgain != password){
            $(".error").html("两次输入的密码不一致!");
            return;
        }else{
            $(".error").html("");
        }
        var verifyPhone=verifyPhones();
        if(verifyPhone == false){
            $(".error").html("该手机号已存在,请重新输入!");
            return;
        }else{
            $(".error").html("");
        }
        var verifyUserName=verifyUserNames();
        if(verifyUserName == false){
            $(".error").html("该用户名已存在,请重新输入!");
            return;
        }else{
            $(".error").html("");
        }
        $(".stepOne").hide();
        $(".stepTwo").show();
    });
//    发送验证码
    $("#sendCode").click(function(){
        var phone = $("#phone").val();
        var imgCode=$("#imgCode").val();
        var imgCodeParam =  {
            "smscode":imgCode
        };
        var imgCodeData=invokeService('/web/sms/checkGraphical.do',imgCodeParam,'post');
        if(empty(imgCode)){
            $(".error").html("图形验证码不能为空!");
            return;
        }else if(imgCodeData.status!=1000){
            $(".error").html("图形验证码错误!");
            return;
        }else{
            $(".error").html("");
        }
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
    $("#rebackOne").click(function(){
        $(".stepOne").show();
        $(".stepTwo").hide();
    });
    // 身份证验证
    $("#nextTwo").click(function(){
        var realName=$("#realName").val();
        var idCard=$("#idCard").val();
        if(empty(realName)){
            $(".errors").html("真实姓名不能为空!");
            return;
        }else{
            $(".errors").html("");
        }
        if(empty(idCard)){
            $(".errors").html("身份证号不能为空!");
            return;
        }else if(!regcard.test(idCard)){
            $(".errors").html("身份证号格式错误!");
            return;
        }else{
            $(".errors").html("");
        }
        var param={
            "phone":$("#phone").val(),
            "vipname":$("#userName").val(),
            "referrercode":$("#tjrId").val(),
            "password":$("#password").val(),
            "idcard":idCard,
            "realname":realName
        };
        var params =  JSON.stringify(param);
        var data=invokeService('/web/vipuser/vipRegister.do',{"vipuserjson":params},'post');
        if(data.status!=1000){
            prompt(data.message);
            return false;
        }
        imgID=data.date.userId;
        $(".stepTwo").hide();
        $(".stepThree").show();
    })
    $("#rebackTwo").click(function(){
        $(".stepTwo").show();
        $(".stepThree").hide();
    })
    // 上传头像
    $("#nextThree").click(function(){
        if(!empty(imgID)){
            var file=$('#headUrl')[0].files[0];
            if(file!=undefined){
                uploadService('/fileupload','headUrl','file',imgID);
//                var imgData = uploadService('/fileupload','headUrl','file',imgID);
//                if (imgData.status!=1000){
//                    prompt("头像上传失败!");
//                    return;
//                }
                prompt("头像上传成功!",function(){
                    $(".stepThree").hide();
                    $(".stepFour").show();
                    seconds();
                });
            }else{
                prompt("请选择头像!");
                return;
            }
        }
    });
    $("#nextFour").click(function(){
        window.location.href="/webpage/login.html";
    });
    var second=3;
    function seconds(){
        $("#second").html(second+"秒");
        second=second-1;
        var clearsend=setTimeout(seconds,1000);
        if(second==0){
            userLogin();
            clearTimeout(clearsend);
        }
    }
    $("#jump").click(function(){
        userLogin();
    })
});
// 获取图形验证码
function getImgCode(){
    var data=invokeService('/web/sms/getGraphicalSms.do',[],'post');
    if(data.status!=1000){
        prompt(data.message);
        return false;
    }
    return data.date;
}
// 手机号验证
function verifyPhones(){
    var account = $("#phone").val();
    var param =  {
        account:account,
        types:0
    };
    var data=invokeService('/web/vipuser/checkOnly.do',param,'GET');
    if(data.status!=1000){
        return false;
    }
    return true;
}
// 用户名验证
function verifyUserNames(){
    var account = $("#userName").val();
    var param =  {
        account:account,
        types:1
    };
    var data=invokeService('/web/vipuser/checkOnly.do',param,'GET');
    if(data.status!=1000){
        return false;
    }
    return true;
}
function userLogin(){
    var phone=$("#phone").val();
    var password=$("#password").val();
    var param={
        account:phone,
        password:password
    };
    var result=invokeService('/web/vipuser/userlogin.do',param,'post');
    if(result.status!=1000){
        prompt("系统错误!");
        return false;
    }
    var se =  sessionStorage;
    se.userId=result.date.userId;
    se.username=result.date.vipname;
    se.phone=result.date.phone;
    se.email=result.date.email;
    window.location.href='/webpage/index.html'
}