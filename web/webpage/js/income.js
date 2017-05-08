/**
 * Created by Administrator on 2017/4/10.
 */
$(function(){
    var se =  sessionStorage;
    var uesrID=se.userId;
//    获取登陆用户财务信息
    var param={
        userId:uesrID
    };
    var result=invokeService('/web/summary/getFinance.do',param,'get');
    if(result.status!=1000){
        prompt("获取财务信息失败!");
        return false;
    }
    $("#already").text(result.date.already);
    $("#balance").text(result.date.balance);
    $("#gradeone").text(result.date.gradeone);
    $("#gradetwo").text(result.date.gradetwo);
    $("#gradethree").text(result.date.gradethree);

    $("#tatalMoney").text(result.date.tatalMoney);
    $("#drawalMoney").text(result.date.drawalMoney);
    $("#gradeCount").text(result.date.gradeCount);


    $(".btn_more1").click(function () {
        $(".btn_more").eq(0).attr("href","#/profit");
    });
    $(".btn_more2").click(function () {
        $(".btn_more").eq(1).attr("href","#/profit");
    });



    $("nav ul li").click(function(){
        var i =  $("nav ul li").index(this);
        $("nav ul li").removeClass("active");
        $("nav ul li").eq(i).addClass("active");
    })
});