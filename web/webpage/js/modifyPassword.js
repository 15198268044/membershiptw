/**
 * Created by Administrator on 2017/4/12.
 */
$(function () {
    var se =  sessionStorage;
    var myphone=se.phone;
    var userId=se.userId;
    $("#myphone").text(myphone);
    $(".modify input").keyup(function () {
        $(".err").text('')
    });

    $("#btn_pas_submit").click(function () {
        var newpass=$("#newpass").val();
        var newpass2=$("#newpass2").val();
        var oldpass=$("#oldpass").val();
        var verification_val=$("#verification_val").val();
        if(empty(oldpass)){
            $(".err").text('旧密码不能为空');
            return false
        }
        else if(empty(newpass)){
            $(".err").text('新密码不能为空');
            return false
        }
        else if(empty(newpass2)){
            $(".err").text('确认密码不能为空');
            return false
        }
        else if(newpass2!=newpass){
            $(".err").text('两次密码不一致');
            return false
        }
        else if(empty(verification_val)){
            $(".err").text('验证码不能为空');
            return false
        }
        var vcparam={
            phone:myphone,
            code:verification_val
        };
        var vcresule=invokeService('/web/sms/checkSms.do',vcparam,'post');
        if(vcresule.status!=1000){
            $(".err").text(vcresule.message);
            return false
        }
        var param={
            userId:userId,
            newpass:newpass,
            oldpass:oldpass
        };
        var result=invokeService('/web/pass/updatePass.do',param,'post');
        if(result.status!=1000){
            prompt(result.message);
            return false
        }
        prompt('修改成功',function () {
            location.reload()
        })
    });
//   验证码函数
    function verification() {
        var se =  sessionStorage;
        var myphone=se.phone;
        var param={
            phone:myphone,
            type:2
        };
        var result=invokeService('/web/sms/sendSms.do',param,'post');
        if(result.status!=1000){
            prompt(result.message)
        }
    }
});
var countdown=60;
function settime(obj) {
    if (countdown == 0) {
        obj.removeAttribute("disabled");
        obj.innerHTML="发送验证码";
        countdown = 60;
        return;
    } else {
        obj.setAttribute("disabled", true);
        obj.innerHTML="重新发送(" + countdown + ")";
        countdown--;
    }
    setTimeout(function() {
            settime(obj) }
        ,1000)

}