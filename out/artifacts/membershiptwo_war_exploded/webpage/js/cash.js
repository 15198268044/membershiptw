/**
 * Created by Administrator on 2017/4/11.
 */
$(function(){
    var se =  sessionStorage;
    var userId=se.userId;
    //    选择收取方式  0：银行卡；1：支付宝；默认是银行卡：0
    var banktype=0;
//    银行类型 0：中国银行；1：建设银行；2工商银行；3：农业银行
    var bankId;
    var param={
        userId:userId
    };
    var data=invokeService('/web/summary/getSummary.do',param,'get');
    if(data.status!=1000) {
        prompt(data.message);
        return false;
    }
    var withdrawalState = data.date.status;
    $(".txfs").click(function (){
        var i =  $(".txfs").index(this);
        $(".txfs").removeClass('active');
        $(".txfs").eq(i).addClass("active");
        banktype=i;
        $(".tx_box_div").hide();
        $(".tx_box_div").eq(i).show();
        if($(".txfs").eq(1).attr('class')=="txfs active"){
            $(".zfbts").hide();
        }
        else{
            $(".zfbts").show();
        }
    });


//    ****
//
// 银行卡
//
// *
//    获取银行类型  用于展示
    var result=invokeService('/web/bank/getBankList.do',{},'get');
    if(result.status!=1000){
        prompt(result.message);
        return false
    }
    $("#tx_select").html('');
    for(var i=0;i<result.date.length;i++){
        bankId = result.date[0].id
        $("#tx_select").html($("#tx_select").html()+
                "<option id='"+result.date[i].id+"' class='option'>"+
                result.date[i].bankName+
                "</option>"
        )
    }
    // bankInfo
//  获取银行code  用于传值
    $("#tx_select").change(function(){
        bankId=$("#tx_select  option:selected").attr("id");
    });
    $("#submit1").click(function () {
        var bankaddress=$("#bankaddress").val();
        var banknum=$("#banknum").val();
        var bankuser=$("#bankuser").val();
        if(empty(bankaddress)){
            prompt('开户银行地址不能为空!');
            return false
        }
        if(empty(banknum)){
            prompt('银行卡号不能为空!');
            return false
        }
        if(empty(bankuser)){
            prompt('开户姓名不能为空!');
            return false
        }
        var param={
            bankType:banktype,
            bankId:bankId,
            banknum:banknum,
            bankaccountname:bankuser,
            address:bankaddress,
            islock:0
        };
        var data=invokeService('/web/userbank/upUserBank.do',{"userId":userId,"bankInfo":JSON.stringify(param)},'POST');
        if(data.status!=1000){
            prompt(data.message);
            return false;
        }
        prompt("添加成功!",function(){
            location.reload();
        });

    });
//    *
//
//
// 支付宝
//
// *
    $("#submit2").click(function () {
        var zfbzh=$("#zfbzh").val();
        var zfbName=$("#zfbName").val();
        if(empty(zfbzh)){
            prompt('支付宝账号不能为空!');
        }
        if(empty(zfbName)){
            prompt('真实姓名不能为空!');
        }
        var param={
            bankType:banktype,
            bankId:"",
            banknum:zfbzh,
            bankaccountname:zfbName,
            address:"",
            islock:0
        };
        var data=invokeService('/web/userbank/upUserBank.do',{"userId":userId,"bankInfo":JSON.stringify(param)},'POST');
        if(data.status!=1000){
            prompt(data.message);
            return false;
        }
        prompt("添加成功!",function(){
            location.reload();
        });

    });
//    返回
    $(".btn_back").click(function () {
        $(".btn_back").attr('href','#/income')
    })
});