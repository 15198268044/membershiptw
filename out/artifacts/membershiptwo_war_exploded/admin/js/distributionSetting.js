$(function(){
    var data=invokeService('/admin/sysparam/getParamList.do',{},'GET');
    if(data.status!=1000){
        prompt(data.message);
        return false;
    }
    $("#oneMember").val(data.date.one);
    $("#twoMember").val(data.date.two);
    $("#threeMember").val(data.date.three);
    $("#fee").val(data.date.fee);
    $("#confirm").click(function(){
        var oneMember=$("#oneMember").val();
        var twoMember=$("#twoMember").val();
        var threeMember=$("#threeMember").val();
        var fee=$("#fee").val();
        if(empty(oneMember)){
            prompt('一级用户分销不能为空!');
            return false;
        }
        if(empty(twoMember)){
            prompt('二级用户分销不能为空!');
            return false;
        }
        if(empty(threeMember)){
            prompt('三级用户分销不能为空!');
            return false;
        }
        if(empty(fee)){
            prompt('提现手续费不能为空!');
            return false;
        }
        var param={
            costone:oneMember,
            costtwo:twoMember,
            costthree:threeMember
        };
        var data=invokeService('/admin/sysparam/upGrade.do',param,'post');
        if(data.status!=1000){
            prompt("设置失败!");
            return false;
        }
        var costParam={
            cost:fee
        };
        var costData=invokeService('/admin/sysparam/upparam.do',costParam,'post');
        if(costData.status!=1000){
            prompt("设置失败!");
            return false;
        }
        prompt("设置成功!",function(){
            location.reload();
        });
    })
})