/**
 * Created by Administrator on 2017/4/13.
 */
$(function(){
    var xli = $(".screen ul").length;
    for(var xlj=0;xlj<xli;xlj++){
        $(".screen ul:eq("+xlj+") li").eq(0).addClass("xlactive");
    }
    //第一次加载
    withdrawalsRecordsList();
    //查询按钮
    $('#query').click(function(){
        withdrawalsRecordsList();
    })
    $(".serialnumber").keyup(function () {

    })
    $("body").on('click','.dddd',function () {
        var i=$(".dddd").index(this);
        var jlid=$(".dddd").eq(i).attr("id");
        var param={
            id:jlid
        };
        var result=invokeService('/web/branch/getBranchDetails.do',param,'get');
        if(result.status!=1000){
            prompt(result.message);
            return false;
        }
        $("#serialnumber").text(result.date.serialnumber);
        $("#dateTime").text(result.date.dateTime);
        $("#tatalmoney").text(result.date.tatalmoney);
        $("#vipname").text(result.date.realname+"("+result.date.usergrade+")");
        $(".allbg").show();
    });
    $("body").on('click','.close',function () {
        $(".allbg").hide()
    });
//    var se =  sessionStorage;
//    var userId=se.userId;
//    var param={
//        userId:userId
//    };
//    var result=invokeService('/web/branch/getBranchList.do',param,'get');
//    if(result.status!=1000){
//        prompt(result.message);
//        return false;
//    }
    function withdrawalsRecordsList(){
        pageBar("withdrawalsRecordsListForm",1,5,function(result){
            if(result.status!=1000){
                $(".sy_table tbody").html("");
                return;
            }
            $(".sy_table tbody").html("");
            for(var i =0;i<result.date.records.length;i++){
                $(".sy_table tbody").html(
                        $(".sy_table tbody").html()+
                        "<tr>"+
                        "<td>"+
                        result.date.records[i].dateTime+
                        "</td>"+
                        "<td>"+
                        result.date.records[i].serialnumber+
                        "</td>"+
                        "<td>"+
                        result.date.records[i].tatalmoney+
                        "</td>"+
                        "<td>"+
                        result.date.records[i].realname+"("+result.date.records[i].usergrade+")"+
                        "</td>"+
                        "<td>"+
                        "<a  class='dddd' href='javascript:void(0)' style='color: red' id='"+result.date.records[i].id+"'>查看</a>"+
                        "</td>"+
                        "</tr>"
                );
            }
        })
    }
});