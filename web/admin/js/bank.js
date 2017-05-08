/**
 * Created by Administrator on 2017/4/16.
 */
$(function () {
//        打开添加银行弹出框
    $("#add_bank").click(function () {
        $(".all_bg").show();
    });
//        关闭添加银行弹出框
    $(".add_bank_close").click(function () {
        $(".all_bg").hide();
        $(".modifyBankBg").hide();
    });
//    添加银行卡
    $("#btn_add_bank").click(function () {
        var bankname=$("#bankname").val();
        if(empty(bankname)){
            prompt('银行卡名称不能为空');
            return false
        }
        var param={
            bankname:bankname
        };
        var result=invokeService('/admin/bankInfo/addBankAccount.do',param,'post');
        if(result.status!=1000){
            prompt(result.message);
            return false
        }
        $(".modifyBankBg").hide();
        prompt('添加成功',function(){
            location.reload();
        });
    });
    var result=invokeService('/admin/bankInfo/getBankList.do',{},'get');
    if(result.status!=1000){
        prompt(result.message);
        return false
    }
    $("#bank_table tbody").html('');

    for(var i=0;i<result.date.length;i++){
        var type=result.date[i].status;
        if(type==0){
            type='禁用';
        }
        if(type==1){
            type='启用';
        }
        $("#bank_table tbody").html(
                $("#bank_table tbody").html()+
                "<tr>"+
                "<td>"+
                    result.date[i].id+
                "</td>"+
                "<td>"+
                    result.date[i].bankname+
                "</td>"+
                "<td>"+
                    type+
                "</td>"+
                "<td>"+
                "<span class='edit_bank' id='"+result.date[i].id+"'>"+
                    '编辑'+
                "</span>丨"+
                "<span class='del_bank' id='"+result.date[i].id+"'>删除</span>"+
                "</td>"+
                "</tr>"
        );
    }



//    删除银行卡；
    $("#bank_table").on("click",".del_bank",function () {
        var i=$(".del_bank").index(this);
        var a= $(".del_bank").eq(i).attr("id");
        isDel("确认删除该银行吗？",function(){
            var param={
                bankId:a
            };
            var result=invokeService('/admin/bankInfo/deleteBankAccount.do',param,'post');
            if(result.status!=1000){
                prompt(result.message);
                return false
            }
            location.reload();
        })

    });
    //    编辑银行卡状态
    var bankId;
    $("#bank_table").on('click','.edit_bank',function () {
        $(".modifyBankBg").show();
        var i=$(".edit_bank").index(this);
        bankId= $(".edit_bank").eq(i).attr("id");
        var state;
        $("#bank").val($("#bank_table tbody tr:eq("+i+") td:eq("+1+")").html());
        if($("#bank_table tbody tr:eq("+i+") td:eq("+2+")").html() == "禁用"){
            state = 0;
        }else if($("#bank_table tbody tr:eq("+i+") td:eq("+2+")").html() == "启用"){
            state = 1;
        }
        $("#state input").eq(state).attr("checked",true);
    });
    $("#modify").click(function(){
        var param={
            bankId:bankId,
            bankname:$("#bank").val(),
            status:$("#state .bank_input:checked").val()
        };
        var result=invokeService('/admin/bankInfo/bankState.do',param,'post');
        if(result.status!=1000){
            prompt(result.message);
            return false
        }
        $(".modifyBankBg").hide();
        prompt('修改成功',function(){
            location.reload();
        });
})









////        打开编辑银行卡弹出框
//    $(".edit_bank").click(function () {
//        $(".all_bg").show();
//        $(".edit_bank_box").show()
//    });
////        关闭编辑银行卡弹出框
//    $(".edit_bank_close").click(function () {
//        $(".all_bg").hide();
//        $(".edit_bank_box").hide()
//    });
});