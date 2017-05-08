$(function(){
    //添加会员
    $("#addMember").click(function(){
        window.location.href="/admin/addMember.html"
    });
    //第一次加载
    administrationList();
    //查询按钮
    $('#query').click(function(){
        administrationList();
    })
    // 选择用户状态
    $("#bombBox").on("click",".state",function(){
        radioState =  $(":radio:checked").val();
    });
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
//    获取银行
    var bankData=invokeService('/web/bank/getBankList.do',{},'GET');
    if(bankData.status!=1000){
        prompt(bankData.message);
        return false
    }
    $("#bank ul").html("");
    for(var i=0;i<bankData.date.length;i++){
        $("#bank ul").html( $("#bank ul").html()+
                "<li value='"+bankData.date[i].id+"'>"+bankData.date[i].bankName+"</li>"
        )
    }
    var bId;
    var userIds;
    // 修改用户信息
    $("#table").on("click",".edit",function(){
        //获取用户信息
        var j = $(".edit").index(this);
        var userId = $(".edit").eq(j).attr("user");
        var param={
                userId:userId
            }
            var data =invokeService('/admin/modify/getVipInfo.do',param,'GET');
            if(data.status!=1000){
                prompt(data.message);
                return false;
            }
        bId = data.date.bId;
        userIds = userId;
        $(".modifyMember,.modifyMemberBg").show();
//        if(data.date.type == 0){
//            $("#mode span").show();
//            $("#mode span").eq(1).hide();
//            $(".bank").show();
//            $(".zfb").hide();
//        }else if(data.date.type == 1){
//            $("#mode span").show();
//            $("#mode span").eq(0).hide();
//            $(".bank").hide();
//            $(".zfb").show();
//        }

        $("#vipname").val(data.date.vipname);
        $("#phone").val(data.date.phone);
        $("#realName").val(data.date.realname);
        $("#idcard").val(data.date.idcard);
        $("#referrercodeId").val(data.date.referrercode);
        $("#state input").eq(data.date.islock).attr("checked",true);
        $("#mode input").eq(data.date.type).attr("checked",true);
        $("#bankId").html(data.date.bankTypeName);
        $("#bankId").attr("value",data.date.bankId);
        $("#bankAccount").val(data.date.address);
        $("#bankNum").val(data.date.banknum);
        $("#bankaccountname").val(data.date.bankaccountname);
        $("#zfbAccount").val(data.date.alipayAccount);


    });
    // 确认修改
    $("#phone").blur(function(){
        var verifyPhone=verifyPhones();
        if(verifyPhone == false){
            $(".error").eq(2).html("该手机号已存在,请重新输入!");
            return;
        }else{
            $(".error").eq(2).html("");
        }
    });

    // 验证用户名是否存在
    $("#vipname").blur(function(){
        var verifyUserName=verifyUserNames();
        if(verifyUserName == false){
            $(".error").eq(0).html("该用户名已存在,请重新输入!");
            return;
        }else{
            $(".error").eq(0).html("");
        }
    });
    var regphone = /(^1[3|4|5|7|8][0-9]{9}$)/;
    // 身份证
    var regcard=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
    $("#modify_add_user").click(function(){
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
        var verifyPhone=verifyPhones();
        if(verifyPhone == false){
            $(".error").eq(2).html("该手机号已存在,请重新输入!");
            return;
        }else{
            $(".error").eq(2).html("");
        }
        var verifyUserName=verifyUserNames();
        if(verifyUserName == false){
            $(".error").eq(0).html("该用户名已存在,请重新输入!");
            return;
        }else{
            $(".error").eq(0).html("");
        }
        var withdrawals;
        if(mode == 0){
            zfbAccount = "";

            withdrawals = {
                userId:userIds,
                bId:bId,
                vipname:vipname,
                password:password,
                phone:phone,
                idcard:idcard,
                referrercode:referrercodeId,
                realname:realname,
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
                userId:userIds,
                bId:bId,
                vipname:vipname,
                password:password,
                phone:phone,
                idcard:idcard,
                referrercode:referrercodeId,
                realname:realname,
                bankType:mode,
                bankId:bankId,
                banknum:bankNum,
                alipayAccount:zfbAccount,
                bankaccountname:bankaccountname,
                address:bankAccount,
                islock:state
            }
        }
        var result=invokeService('/admin/modify/modifyUserInfo.do',{"userInfo":JSON.stringify(withdrawals)},'post');
        if(result.status!=1000){
            prompt(result.message);
            return false
        }
        $(".modifyMember,.modifyMemberBg").hide();
        prompt('修改成功!',function(){
            location.reload();
        });
    })
//    关闭修改用户信息
    $(".close").click(function(){
        $(".modifyMember,.modifyMemberBg").hide();
    });
    // 删除用户
    $("#table").on("click",".del",function(){
        var i = $(".del").index(this);
        var userId = $(".del").eq(i).attr("id");
        isDel("确认删除该用户吗？",function(){
            var param={
                userId:userId
            }
            var data =invokeService('/admin/vipuser/deleteUser.do',param,'GET');
            if(data.status!=1000){
                prompt(data.message);
                return false;
            }
            location.reload();
        })
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
    var account = $("#vipname").val();
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
function administrationList(){
    pageBar("administrationListForm",1,5,function(result){
        if(result.status!=1000){
            $("#table>tbody").html("");
            return;
        }
        $("#table>tbody").html("");
        var data=result.date;
        var html='';
        var stateVal='';
        var userHead;
        for(var i =0;i<data.records.length;i++){
            var state = data.records[i].islock;
            if(empty(data.records[i].headUrl)){
                userHead="/admin/images/defaultAvatar.png";
            }else{
                userHead="/resources/upload/images/"+data.records[i].headUrl;
            }
            if(state == 0){
                stateVal="正常";
            }else if(state == 1){
                stateVal="冻结";
            }else{
                stateVal="禁止登录";
            }
            html+=
                "<tr>"+
                "<td>"+
                "<img src='"+userHead+"'>"+
                "</td>"+
                "<td>"+
                data.records[i].vipname+
                "</td>"+
                "<td>"+
                data.records[i].phone+
                "</td>"+
                "<td>"+
                data.records[i].loginnum+
                "</td>"+
                "<td>"+
                data.records[i].loginIp+
                "</td>"+
                "<td>"+
                data.records[i].loginDate+
                "</td>"+
                "<td>"+
                data.records[i].realname+
                "</td>"+
                "<td>"+
                data.records[i].refername+
                "</td>"+
                "<td>"+
                data.records[i].diname+
                "</td>"+
                "<td>"+
                data.records[i].total+
                "</td>"+
                "<td>"+
                 stateVal+
                "</td>"+
                "<td class='operation'>"+
                "<span class='edit' user='"+data.records[i].userId+"'>编辑</span>|<span class='del' id='"+data.records[i].userId+"'>删除</span>"+
                "</td>"+
                "</tr>"
        }
        $("#table tbody").html(html);
    })
}
