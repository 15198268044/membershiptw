/**
 * Created by Administrator on 2017/4/16.
 */
$(function (){
    $("#return").click(function(){
        window.location.href="/admin/administration.html"
    });
//    获取银行
    var bankData=invokeService('/admin/bankInfo/getBankList.do',{},'GET');
    if(bankData.status!=1000){
        prompt(bankData.message);
        return false
    }
    $("#bank ul").html("");
    for(var i=0;i<bankData.date.length;i++){
        $("#bank ul").html( $("#bank ul").html()+
                "<li value='"+bankData.date[i].id+"'>"+bankData.date[i].bankname+"</li>"
        )
    }
    var regphone = /(^1[3|4|5|7|8][0-9]{9}$)/;
    // 身份证
    var regcard=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
    // 选择提现方式
    $("#mode input").click(function(){
        var i =  $("#mode input").index(this);
        if(i ==0){
            $(".bank").show();
            $(".zfb").hide();
        }else if(i == 1){
            $(".bank").hide();
            $(".zfb").show();
        }
    })
    $("#btn_add_user").click(function (){
        var vipname=$("#vipname").val();
        var password=$("#password").val();
        var phone=$("#phone").val();
        var idcard=$("#idcard").val();
        var referrercodeId=$("#referrercodeId").val();
        var realname =$("#realName").val();
        var state=$("#state .radio:checked").val();
//    当前选中状态 0:正常，1：冻结，2：禁止登录
        var mode=$("#mode .radio:checked").val();
        var bankId=$("#bankId").attr("value");
        var bankAccount=$("#bankAccount").val();
        var bankNum=$("#bankNum").val();
        var bankaccountname=$("#bankaccountname").val();
        var zfbAccount=$("#zfbAccount").val();
        if(empty(vipname)){
            $(".error").eq(0).html('用户名不能为空!');
            return false
        }else{
            $(".error").eq(0).html("");
        }
        if(empty(password)){
            $(".error").eq(1).html('密码不能为空!');
            return false
        }else{
            $(".error").eq(1).html("");
        }
        if(empty(phone)){
            $(".error").eq(2).html('手机号码不能为空!');
            return false
        }else if(!regphone.test(phone)){
            $(".error").eq(2).html('手机号码格式不正确!');
            return false
        }else{
            $(".error").eq(2).html("");
        }
        if(empty(state)){
            prompt('请设置当前用户状态!');
            return false
        }
        if(empty(mode)){
            prompt('请设置当前用户提现方式!');
            return false
        }
        var withdrawals;
        if(mode == 0){
            zfbAccount = "";
            withdrawals = {
                bankType:mode,
                bankId:bankId,
                banknum:bankNum,
                alipayAccount:zfbAccount,
                bankaccountname:bankaccountname,
                address:bankAccount,
                islock:state
            }
        }else if(mode == 1){
            bankAccount = "";
            bankNum = "";
            bankaccountname = "";
            bankId = "";
            withdrawals = {
                bankType:mode,
                bankId:bankId,
                banknum:bankNum,
                alipayAccount:zfbAccount,
                bankaccountname:bankaccountname,
                address:bankAccount,
                islock:state
            }
        }
        var user={
            vipname:vipname,
            password:password,
            phone:phone,
            idcard:idcard,
            referrercode:referrercodeId,
            realname:realname
        };
        JSON.stringify(user);
        JSON.stringify(withdrawals);
        var result=invokeService('/admin/vipuser/addVipUser.do',{"vipuserjson":JSON.stringify(user),"bankinfo": JSON.stringify(withdrawals)},'post');
        if(result.status!=1000){
            prompt(result.message);
            return false
        }
        prompt('添加成功!');
        $("input").val("");
        $(".choice .radio:checked").val('');
    });
    // 搜索
    $("#referrercodeId").keyup(function(){
        var referrercodeId = $("#referrercodeId").val();
        if(empty(referrercodeId)){
            $("#tips").hide();
            return;
        }
        var param={
            account:referrercodeId
        };
        var result=invokeService('/web/vipuser/lenovoAccount.do',param,'get');
        if(result.status!=1000){
            prompt(result.message);
            return false
        }

        $("#tips").show();
        $("#tips").html("");
        for(var i=0;i<result.date.length;i++)
            $("#tips").html( $("#tips").html()+
                    "<li>"+result.date[i]+"</li>"
            )
        if(result.date.length == 0){
            $("#tips").hide();
        }
    })
    $("#tips").on("click","li",function(){
        var i = $("#tips li").index(this);
        var html = $("#tips li").eq(i).html();
        $("#referrercodeId").val(html);
        $("#tips").hide();
    })
    $("#tips").on("mouseenter","li",function(){
        var i =  $("#tips li").index(this);
        $("#tips li").eq(i).addClass("activess");
    });
    $("#tips").on("mouseleave","li",function(){
        var i =  $("#tips li").index(this);
        $("#tips li").eq(i).removeClass("activess");
    });
});