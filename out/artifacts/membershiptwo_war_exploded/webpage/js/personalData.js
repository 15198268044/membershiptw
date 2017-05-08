$(function(){
    var se =  sessionStorage;
    var phone = se.phone;
    var userId = se.userId;
    var email = se.email;
    $("#email").html(email);
    $("#phone").html(phone);
    var regphone = /(^1[3|4|5|7|8][0-9]{9}$)/;
    var param =  {
         userId:userId
    };
    var data=invokeService('/web/uploadfile/getUserHead.do',param,'GET');
    if(data.status!=1000){
         prompt(data.message);
         return false;
    }
    if(empty(data.date.headUrl)){
        $("#imghead").attr("src","/webpage/images/defaultAvatar.png");
    }else{
        $("#imghead").attr("src","/resources/upload/images/"+data.date.headUrl);
    }
// 点击重置
    $(".email-right").click(function(){
        if($('.reset').eq(0).css('display') == "none"){
            $('.reset').eq(0).show();
            $(".email-right img").css("-webkit-transform","rotate(0deg)")
        }else{
            $('.reset').eq(0).hide();
            $(".email-right img").css("-webkit-transform","rotate(-90deg)")
        }
    })
    $(".phone-right").click(function(){
        if($('.reset').eq(1).css('display') == "none"){
            $('.reset').eq(1).show();
            $(".phone-right img").css("-webkit-transform","rotate(0deg)")
        }else{
            $('.reset').eq(1).hide();
            $(".phone-right img").css("-webkit-transform","rotate(-90deg)")
        }
    })
    // 修改邮箱
    // 邮箱
    var regemail=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+(\.[a-zA-Z]{2,3})+$/;
    $("#modifyEmail").click(function(){
//        var email = $("#email").html();
        var newEmail= $("#newEmail").val();
        var emailCode= $("#emailCode").val();
        if(empty(newEmail)){
            $(".errors").eq(0).html("新邮箱不能为空!");
            return;
        }else if(!regemail.test(newEmail)){
            $(".errors").eq(0).html("新邮箱格式不正确!");
            return;
        }else{
            $(".errors").eq(0).html("");
        }
        var emailParam =  {
            phone:newEmail,
            code:emailCode
        };
        var emailData=invokeService('/web/sms/checkSms.do',emailParam,'post');
        if(empty(emailCode)){
            $(".errors").eq(1).html("验证码不能为空!");
            return;
        }
        else if(emailData.status!=1000){
            $(".errors").eq(1).html("验证码输入错误!");
            return;
        }else{
            $(".errors").eq(1).html("");
        }
        var param =  {
            userId:userId,
            email:newEmail
        };
        //
        var data=invokeService('/web/vipuser/bindEmail.do',param,'post');
        if(data.status!=1000){
            prompt(data.message);
            return false;
        }
        prompt("修改成功!",function(){
            var se =  sessionStorage;
            se.email=newEmail;
            location.reload();
        })
    });
//    发送邮箱验证码
    $("#sendEmailCode").click(function(){
        var newEmail= $("#newEmail").val();
        if(empty(newEmail)){
            $(".errors").eq(0).html("新邮箱不能为空!");
            return;
        }else if(!regemail.test(newEmail)){
            $(".errors").eq(0).html("新邮箱格式不正确!");
            return;
        }else{
            $(".errors").eq(0).html("");
        }
        var param =  {
            email:newEmail
        };
        //
        var data=invokeService('/web/sms/sendEmail.do',param,'post');
        if(data.status!=1000){
            prompt(data.message);
            return false;
        }
        $(".errors").eq(1).html("验证码发送成功!");
    });
//    上传头像
    $("#upload").click(function(){
        if(!empty(userId)){
            var file=$('#headUrl')[0].files[0];
            if(file!=undefined){
                uploadService('/fileupload','headUrl','file',userId);
//                var imgData = uploadService('/fileupload','headUrl','file',imgID);
//                if (imgData.status!=1000){
//                    prompt("头像上传失败!");
//                    return;
//                }
                prompt("头像上传成功!",function(){
                    location.reload();
                });
            }else{
                prompt("请选择头像!");
                return;
            }
        }
    })
    $("#preservation").click(function(){
        var phone=$("#phone").html();
        var newPhone=$("#newPhone").val();
        var phoneCode=$("#phoneCode").val();
        if(empty(newPhone)){
            $(".error").eq(0).html("新手机号不能为空!");
            return;
        }else if(!regphone.test(newPhone)){
            $(".error").eq(0).html("新手机号格式不正确!");
            return;
        }
        else{
            $(".error").eq(0).html("");
        }
        var phoneParam =  {
            phone:phone,
            code:phoneCode
        };
        var phoneData=invokeService('/web/sms/checkSms.do',phoneParam,'post');
        if(empty(phoneCode)){
            $(".error").eq(1).html("手机验证码不能为空!");
            return;
        }else if(phoneData.status!=1000){
            $(".error").eq(1).html("手机验证码输入错误!");
            return;
        }else{
            $(".error").eq(1).html("");
        }
        var param =  {
            userId:userId,
            oldTel:phone,
            newTel:newPhone
        };
        var data=invokeService('/web/pass/modifyUserPhone.do',param,'post');
        if(data.status!=1000){
            prompt(data.message);
            return false;
        }
        prompt("修改成功,请重新登录!",function(){
            window.location.href="/webpage/login.html";
        })
    })
    //    发送验证码
    $("#sendCode").click(function(){
        var phone = $("#phone").html();
        var param =  {
            phone:phone,
            type:3
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
})