/**
 * Created by Administrator on 2017/4/12.
 */
$(function(){
    $("nav ul li").click(function(){
        var i =  $("nav ul li").index(this);
        $("nav ul li").removeClass("active");
        $("nav ul li").eq(i).addClass("active");
    })
    var a=1;
    var b=2;
    var c=3;
    var recommdLink = getUrl()+'/webpage/register.html';
//    var recommdLink = getUrl()+'/webpage/register.html?flag=1&recommId='+a+'&recommName='+b+'&recommName2='+c;
    $('#qrcode').qrcode({
        render: "table", //table方式
        width: 280, //宽度
        height:280, //高度
        text: recommdLink //任意内容
    });
    $("#invitationCode").val(recommdLink)
});
$(".copyLink").eq(0).on('click', function() {
    var e = document.getElementById("invitationCode"); //对象是content
    e.select(); //选择对象
    var isCopy = document.execCommand("Copy"); //执行浏览器复制命令
    document.getElementById("success").innerHTML="复制成功";
});
