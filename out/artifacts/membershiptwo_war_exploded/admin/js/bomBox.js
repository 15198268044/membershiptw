var func={
    callback:''
};
// 弹出框
function prompt(content,callback){
    if(!empty(callback)){
        func.callback=callback;
    }
    var paging=
        "<div class='sui-modal hide fade in prompt' style='margin-top: -182px; display: block;z-index: 9999'>"+
        "<div class='modal-dialog'>"+
        "<div class='modal-header'>"+
        " <button type='button' class='sui-close prompt-close'> x"+
        "</button>"+
        "<h4 class='modal-title'>提示"+
        "</h4>"+
        "</div>"+
        "<div class='modal-body prompt-body'>"+content+""+
        "</div>"+
        "<div class='modal-footer'>"+
        " <button type='button' class='sui-btn prompt-btn btn-primary btn-large'>确定"+
        "</button>"+
        "</div>"+
        "</div>"+
        "</div>"+
        "<div class='sui-modal-backdrop fade in prompt-backdrop' style='background:#000;z-index:9998'>"+
        "</div>";
    $("#bombBox").html(paging);
}
function prompt2(callback2){
    if(!empty(callback2)){
        func.callback2=callback2;
    }
    var paging2=
        "<div class='sui-modal hide fade in prompt' style='margin-top: -182px; display: block;z-index: 9999'>"+
        "<div class='modal-dialog'>"+
        "<div class='modal-header'>"+
        " <button type='button' class='sui-close prompt-close2'> x"+
        "</button>"+
        "<h4 class='modal-title'>提示"+
        "</h4>"+
        "</div>"+
        "<div class='modal-body prompt-body'>"+
            "<p>请输入处理备注:</p>"+
            "<input id='mark' type='text' style='width: 96%;margin-top: 20px;height: 30px;outline: none;padding-left: 10px'>"+
            "<p class='error' style='color: red'></p>"+
        "</div>"+
        "<div class='modal-footer'>"+
        " <button type='button' class='sui-btn prompt-btn2 btn-primary btn-large'>确定"+
        " <button type='button' class='sui-btn prompt-close2 btn-primary    btn-large'>取消"+
        "</button>"+
        "</div>"+
        "</div>"+
        "</div>"+
        "<div class='sui-modal-backdrop fade in prompt-backdrop' style='background:#000;z-index:9998'>"+
        "</div>";
    $("#bombBox").html(paging2);
}
function isDel(content,callback){
    if(!empty(callback)){
        func.callback2=callback;
    }
    var paging2=
        "<div class='sui-modal hide fade in prompt' style='margin-top: -182px; display: block;z-index: 9999'>"+
        "<div class='modal-dialog'>"+
        "<div class='modal-header'>"+
        " <button type='button' class='sui-close prompt-close2'> x"+
        "</button>"+
        "<h4 class='modal-title'>提示"+
        "</h4>"+
        "</div>"+
        "<div class='modal-body prompt-body'>"+
            content+
        "</div>"+
        "<div class='modal-footer'>"+
        " <button type='button' class='sui-btn prompt-btnDel btn-primary btn-large'>确定"+
        " <button type='button' class='sui-btn prompt-close2 btn-primary    btn-large'>取消"+
        "</button>"+
        "</div>"+
        "</div>"+
        "</div>"+
        "<div class='sui-modal-backdrop fade in prompt-backdrop' style='background:#000;z-index:9998'>"+
        "</div>";
    $("#bombBox").html(paging2);
}
$(function(){
    $("body").on("click",".prompt-btn,.prompt-close",function(){
        $("#bombBox").html("");
        if(!empty(func.callback)){
            func.callback();
        }
    });
    $("body").on("click",".prompt-btn2",function(){
        if(empty($("#mark").val())){
            $(".error").html("请输入处理备注!");
            return;
        }else{
            $(".error").html("");
        }
        if(!empty(func.callback2)){
            func.callback2();
        }
    });
    $("body").on("click",".prompt-btnDel",function(){
        $("#bombBox").html("");
        if(!empty(func.callback2)){
            func.callback2();
        }
    });
    $("body").on("click",".prompt-close2",function(){
        $("#bombBox").html("");
    });
    $(".exit").click(function(){
        isDel("确认退出吗？",function(){
            var data=invokeService('/web/vipuser/exitLogin.do',{},'get');
            if(data.status!=1000){
                prompt("退出失败!");
                return false;
            }
            window.location.href="/admin/login.html";
            })
    })
});

