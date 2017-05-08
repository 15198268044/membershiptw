/**
 * Created by Administrator on 2017/4/11.
 */
$(function(){
    var se =  sessionStorage;
    var username=se.username;
    var userId=se.userId;
    var banktype;
    var bankId;

    var param={
        userId:se.userId
    };
    var result=invokeService('/web/summary/getSummary.do',param,'get');
    if(result.status!=1000){
        prompt(result.message);
        return false;
    }
    $("#username").text(result.date.vipName);
    $("#phone").text(result.date.phone);
    if(result.date.headUrl.indexOf(".")<0){
        $("#myhead").attr("src","/webpage/images/defaultAvatar.png");
    }else{
        $("#myhead").attr('src',"/"+result.date.headUrl);
    }
    $("#tx_new").text(result.date.bepresent);
    $("#tx_old").text(result.date.already);
    if(result.date.status == 3){
        $(".click_tx").hide();
        $("#banknum").hide();
        $("#tx_select").show();
        $("#tx_select option").eq(0).html(result.date.bankTypeName);
        $("#tx_select option").eq(1).html("支付宝账户-"+result.date.banknum);
        bankId=result.date.bankId;
        banktype=0;
        $("#tx_select").change(function(){
            banktype=$("#tx_select  option:selected").val();
        });
    }
    if(result.date.status == 0){
        $("#btn_tx_submit").attr("disabled",true).css({"background":"#ecedf1"});
        $(".click_tx span").html("暂无收款方式，点击添加>>");
        $("#banknum").val("");
        $("#banknum").attr("disabled",true).css({"background":"white","border":"1px solid #A9A9A9"});
        $("#tatalmoney").attr("disabled",true).css({"background":"white","border":"1px solid #A9A9A9"});
    }else if(result.date.status == 1){
        $(".click_tx span").html("已有银行卡提现，还可以去设置支付宝提现，点击添加>>");
        $("#banknum").val(result.date.bankTypeName+"-"+result.date.banknum);
        $("#banknum").attr("disabled",true).css({"background":"white","border":"1px solid #A9A9A9"});
        bankId=result.date.bankId;
        banktype=0;
    }else if(result.date.status == 2){
        $(".click_tx span").html("已有支付宝提现，还可以去设置银行卡提现，点击添加>>");
        $("#banknum").val("支付宝账户-"+result.date.banknum);
        $("#banknum").attr("disabled",true).css({"background":"white","border":"1px solid #A9A9A9"});
        bankId=result.date.bankId;
        banktype=1;
    }
    $("#tatalmoney").keyup(function () {
        var money=-$('#tatalmoney').val();
        var sxf = result.date.feeMoney * (money/100);
        $('#sxf').val(-(sxf));
        $('#txm').val(-(money-sxf));
    });

    // s 手续费
    // 100 -   s = 95

$(".click_tx").click(function(){
    $(".click_tx").attr("href","#/cash");
});
$(".pas").click(function(){
    $(".pas").attr("href","#/modifyPassword");
});

    $("#btn_tx_submit").click(function () {
        var tatalmoney=$('#tatalmoney').val();
        var ttt={
            userId:userId,
            tatalmoney:tatalmoney,
            bankId:bankId,
            type:banktype
        };
        var param=JSON.stringify(ttt);
        var result=invokeService('/web/drawals/withDrawalsApply.do',{"jsonData":param},"post");
        if(result.status!=1000){
            prompt(result.message);
            return false;
        }
        prompt('申请提交成功!',function(){
            location.reload();
        })
    })
});