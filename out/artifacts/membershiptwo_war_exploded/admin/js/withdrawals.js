/**
 * Created by Administrator on 2017/4/16.
 */
$(function () {
    // 选择快捷时间
    $(".time").click(function(){
        var i =$(".time").index(this);
        var time;
        $(".time").css("color","#333");
        $(".time").eq(i).css("color","red");
        time =  $(".time").eq(i).attr("value");
        $("#time").attr("value",time)
    });
    //    获取银行
    var bankData=invokeService('/web/bank/getBankList.do',{},'GET');
    if(bankData.status!=1000){
        prompt(bankData.message);
        return false
    }
    $("#bank ul").html("");
    $("#bank ul").html("<li class='xlactive'>全部</li>")
    for(var i=0;i<bankData.date.length;i++){
        $("#bank ul").html( $("#bank ul").html()+
                "<li value='"+bankData.date[i].id+"'>"+bankData.date[i].bankName+"</li>"
        )
    }
//    加载列表
    load();
    $("#query").click(function () {
        var findvalue = $("#findvalue").val();
        var findtype = $("#findtype").attr("value");
        if(empty(findvalue) && !empty(findtype)){
            prompt("请输入筛选值!");
            return;
        }
        if(!empty(findvalue) && empty(findtype)){
            prompt("请选择筛选类型!");
            return;
        }
        load();
    });
    function load() {
        pageBar("administrationListForm",1,5,function(result){
            if(result.status!=1000){
                $(".w_table tbody").html("");
                return;
            }
            $(".w_table tbody").html("");
            var data=result;
            $("#drawalsStateVo1").text(result.date.drawalsStateVo.four);
            $("#drawalsStateVo2").text(result.date.drawalsStateVo.one);
            $("#drawalsStateVo3").text(result.date.drawalsStateVo.two);
            $("#drawalsStateVo4").text(result.date.drawalsStateVo.three);
            var html='';
            for(var i=0;i<result.date.records.length;i++){
                var a=result.date.records[i].type;
                var b=result.date.records[i].status;
                if(a==0){
                    a='银联'
                }
                if(a==1){
                    a='支付宝';
                }
                if(b==0){
                    b='申请中';
                }
                if(b==1){
                    b='银行处理中';
                }
                if(b==2){
                    b='提现成功';
                }
                if(b==3){
                    b='提现失败';
                }

                $(".w_table tbody").html(
                    $(".w_table tbody").html()+
                    "<tr>"+
                    "<td>"+
                    result.date.records[i].id+
                    "</td>"+
                    "<td>"+
                    result.date.records[i].serialnumber+
                    "</td>"+
                    "<td>"+
                    result.date.records[i].applyTime+
                    "</td>"+
                    "<td>"+
                    result.date.records[i].handlerTime +
                    "</td>"+
                    "<td style='padding-left: 20px'>"+
                    "<span class='td_span'>账户类型:</span>"+
                    "<label class='td_label'>"+
                    a+
                    "</label><br/>"+
                    "<div  id='Mybank_div"+result.date.records[i].id+"'>"+
                        "<span class='td_span'>收款银行:</span>"+
                        "<label class='td_label'>"+
                        result.date.records[i].bankInfo.bankname+
                        "</label><br/>"+
                        "<span class='td_span'>开户银行:</span>"+
                        "<label class='td_label'>"+
                        result.date.records[i].bankInfo.address+
                        "</label><br/>"+
                        "<span class='td_span'>银行账户:</span>"+
                        "<label class='td_label'>"+
                        result.date.records[i].bankInfo.banknum+
                        "</label><br/>"+
                        "<span class='td_span'>账户名称:</span>"+
                        "<label class='td_label'>"+
                        result.date.records[i].bankInfo.bankaccountname+
                        "</label>"+
                    "</div>"+
                    "</td>"+
                    "<td style='padding-left: 20px'>"+
                    "<span class='td_span'>用户名:</span>"+
                    "<label class='td_label'>"+
                    result.date.records[i].realname+
                    "</label><br/>"+
                    "<span class='td_span'>手机号:</span>"+
                    "<label class='td_label'>"+
                    result.date.records[i].phone+
                    "</label>"+
                    "</td>"+
                    "<td>"+
                    result.date.records[i].money+
                    "</td>"+
                    "<td style='color: red'>"+
                    b+
                    "</td>"+
                    "<td>"+
                    result.date.records[i].mark+
                    "<td>"+
                    "<select name='"+result.date.records[i].id+"' id='Myselect"+result.date.records[i].id+"' class='clcz'>"+
                    "<option value='1' class='czval'>操作</option>"+
                    "<option value='1' class='czval'>银行处理中</option>"+
                    "<option value='2' class='czval'>提现成功</option>"+
                    "<option value='3' class='czval'>提现失败</option>"+
                    "</select>"+
                    "</td>"+
                    "</tr>"
            );
                if(b=='提现成功'||b=='提现失败'){
                    $("#Myselect"+result.date.records[i].id+"").attr("disabled",true);
                }
                if(a=='支付宝'){
                    $("#Mybank_div"+result.date.records[i].id+"").hide();
                }
            }
        });


        }
        //      处理操作状态
    var czid;
    var czval;
        $(".clcz").change(function () {
            var i = $(".clcz").index(this);
            czid =$(".clcz").eq(i).attr('name');
            czval = $($(".czval:selected")[i]).val();
            prompt2(function(){

                var mark = $("#mark").val();
                var param={
                    recordId:czid,
                    state:czval,
                    mark:mark
                };
                var result=invokeService('/admin/drawals/drawalsHandler.do',param,'post');
                if(result.status!=1000){
                    prompt(result.message);
                    return false
                }
                prompt('操作成功!', function(){
                    location.reload();
                });
            });

        });
});
