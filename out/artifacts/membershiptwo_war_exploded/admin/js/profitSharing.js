$(function(){
    $("#moneyTatal").append(0);
    var arr = new Array();
    var number;

    var result=invokeService('/admin/sysparam/getParamList.do',{},'get');
    if(result.status!=1000){
        prompt(result.message);
        return false;
    }else{
        arr.push(result.date.one);
        arr.push(result.date.two);
        arr.push(result.date.three);
        $("#one").html(result.date.one+"%");
        $("#two").html(result.date.two+"%");
        $("#three").html(result.date.three+"%");
    }


    //金额 用户数据回显
    $("#query").click(function(){
        var account = $("#account").val();
        console.log("BBBBBBBBBBBBBBB"+account);
        findAccount(account);
    });


    function findAccount(account) {
        var param={
            account:account
        };

        console.log("AAAAAAAAAAAAAAAA"+account);


        var data = invokeService('/admin/branch/getGradeName.do',param,'get');
        if(data.status!=1000){
            prompt(data.message);
            return false;
        }else{
            number = data.date.num;
            if(data.date.gradeoneName !="无"){
                $("#onename").html(data.date.gradeoneName+" | "+data.date.onevipname).attr("user",data.date.oneId);
            }else{
                $("#onename").html("无");
            }
            if(data.date.gradetwoName!="无"){
                $("#twoname").html(data.date.gradetwoName+" | "+data.date.twovipname).attr("user",data.date.twoId);
            }else{
                $("#twoname").html("无");
            }
            if(data.date.gradethreeName != "无"){
                $("#threename").html(data.date.gradethreeName+" | "+data.date.threevipname).attr("user",data.date.threeId);
            }else{
                $("#threename").html("无");
            }
        }
    }



    // 查看用户详细信息
    $(".userName").click(function(){
        var j = $(".userName").index(this);
        if($(".userName").eq(j).html() != "无" && $(".userName").eq(j).html() !=""){
            var userId = $(".userName").eq(j).attr("user");
            var param={
                userId:userId
            }
            var data = invokeService('/admin/modify/getVipInfo.do',param,'get');
            if(data.status!=1000){
                prompt("获取此用户信息失败!");
                return false;
            }
            $(".seeInformation,.seeInformationBg").show();
            var state;
            var mode;
            if(data.date.islock == 0){
                state = "正常";
            }else if(data.date.islock == 1){
                state = "冻结";
            }else if(data.date.islock == 2){
                state = "禁止登陆";
            }
            if(data.date.type == 0){
                mode = "对私银行借记卡";
                $(".zfb").hide();
            }else if(data.date.type == 1){
                mode = "支付宝账户";
                $(".bank").hide();
            }
            $("#userName").html(data.date.vipname);
            $("#phone").html(data.date.phone);
            $("#realName").html(data.date.realname);
            $("#idCard").html(data.date.idcard);
            $("#tjr").html(data.date.referrercode);
            $("#state").html(state);
            $("#mode").html(mode);
            $("#bankId").html(data.date.bankTypeName);
            $("#bankAccount").html(data.date.address);
            $("#bankNum").html(data.date.banknum);
            $("#bankName").html(data.date.bankaccountname);
            $("#zfbAccount").html(data.date.alipayAccount);
        }

    });
    // 关闭用户详细信息
    $(".close").click(function(){
        $(".seeInformation,.seeInformationBg").hide();
    })

    //计算金额
    $("#money").change(function(){

        $("#oneMoney").html("");
        $("#twoMoney").html("");
        $("#threeMoney").html("");
        $("#moneyTatal").html("");
        $("#actualMoney").html("");

        //分配总金额
        var tatalMoney  =$("#money").val();
        var gradeone = 0;
        var gradeTwo = 0;
        var gradethree = 0;
        if (number == 1){
            //等级一
            gradeone =  arr[0] * (tatalMoney/100);

        }else if(number == 2){
            //等级一
            gradeone =  arr[0] * (tatalMoney/100);
            //等级二
            gradeTwo  = arr[1] * (tatalMoney/100);
        }else {
            //等级一
            gradeone =  arr[0] * (tatalMoney/100);
            //等级二
            gradeTwo  = arr[1] * (tatalMoney/100);
            //等级三
            gradethree  = arr[2] * (tatalMoney/100);
        }

        //实际分配金额
        var money = (gradeone + gradeTwo + gradethree);

        $("#oneMoney").append(gradeone);
        $("#twoMoney").append(gradeTwo);
        $("#threeMoney").append(gradethree);
        $("#moneyTatal").append(money);
        $("#actualMoney").append(tatalMoney - money);

    });

    $("#confirm").click(function(){
        var tatalMoney=$("#money").val();
        var account=$("#account").val();
        if(empty(tatalMoney)){
            prompt('业绩金额不能为空!');
            return false;
        }
        if(empty(account)){
            prompt('账户名不能为空!');
            return false;
        }
        var param={
            tatalMoney:tatalMoney,
            account:account
        };
        var result=invokeService('/admin/branch/handlerBanch.do',param,'post');
        if(result.status!=1000){
            prompt("无此用户!");
            return false;
        }
        prompt("分配成功!");
        $("#money").val("");
        $("#account").val("");
    })
    // 搜索
    $("#account").keyup(function(){
        var account = $("#account").val();
        if(empty(account)){
            $("#tips").hide();
            return;
        }
        var param={
            account:account
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

        console.log("********** " + html);
        console.log("********** ");
        $("#account").val(html);
        findAccount(html);
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