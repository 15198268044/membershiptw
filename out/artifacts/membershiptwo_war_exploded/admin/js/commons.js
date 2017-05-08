/**
 * Created by Administrator on 2016/10/8.
 */

var service = "http://192.168.0.145";

var port = "8080";

function getUrl(){
    return service+":"+port;
}

function getQueryString(referrer) {
    var reg = new RegExp("(^|&)" + referrer + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null;
}


/**
 * 同步请求
 * @param url
 * @param param
 * @returns {*}
 */
function invokeService(url,param,type){
    var result;
    $.ajax({
        url: getServiceUrl(url),
        type: type,
        async:false,
        data: param,
        success: function (data) {
            result=data;
        }
//        complete:function(xhr,ts){
//            var result = eval('('+xhr.responseText+')')
//            if(result.code==9999){
//                //跳转登录页面
//                window.location.href='./login.html';
//                return;
//            }
//        }
    });
    return result;
}

/**
 * 异步请求
 * @param url
 * @param param
 */
function invokeSyncService(url,param,success,failer){
    $.ajax({
        url: getServiceUrl(url),
        type: 'POST',
        async:true,
        data: param,
        success: function (data, textStatus, jqXHR) {
            //if(data.statusCode==9999){
            //    //跳转登录页面
            //    window.location.href='./home/';
            //    return;
            //}
            //if(data.statusCode==8888){
            //    prompt();
            //    $(".prompt-body").html(data.errorMessage);
            //    //跳转登录页面
            //    setTimeout("window.location.href='./home/'",5000)
            //    return;
            //}
            success(data, textStatus, jqXHR);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            failer(XMLHttpRequest, textStatus, errorThrown);
        }
    });
}

/**
 * 同步上传文件请求
 * @param url 请求地址
 * @param id file文件的控件的ID
 * @param name file文件的控件name
 * @returns {*}
 */
function uploadService(url,id,name,imgId){
    var result;
    var formData = new FormData();
    formData.append(name, $('#'+id)[0].files[0]);
    formData.append("userId",imgId);
    $.ajax({
        url: getServiceUrl(url),
        type: 'POST',
        cache: false,
        async:false,
        data: formData,
        processData: false,
        contentType: false
    }).done(function(res) {
        result=res;
    }).fail(function(res) {
        result=res;
    });
    return result;
}

/**
 * 异步上传文件请求
 * @param url 请求地址
 * @param id file文件的控件的ID
 * @param name file文件的控件name
 * @param success 成功之后的回调函数
 * @param fail 失败之后的回调函数
 */
function uploadSyncService(url,id,name,success,fail){
    var formData = new FormData();
    formData.append(name, $('#'+id)[0].files[0]);
    $.ajax({
        url: getServiceUrl(url),
        type: 'POST',
        cache: false,
        async:true,
        data: formData,
        processData: false,
        contentType: false
    }).done(function(res) {
        success(res);
    }).fail(function(res) {
        fail(res);
    });
}

/**
 * 获取图片验证码
 * @param me img标签对象
 */
function getKaptchaImage(me){
    me.src='/web/commons/getKaptchaImage';
}
/**
 * 拼接url全路径
 * @param url
 * @returns {string}
 */
function getServiceUrl(url){
    var url =  url + "?v=" + (new Date()).valueOf();
//    var params = this.getServiceParams(param);
//    if (params != null)
//        url += "&" + encodeURI(params.params);
    return url;
}
function getServiceParams (param) {
    return param ? { params: Ext.util.JSON.encode(param)} : null;
}
/**
 * 判断是否为空
 * @param info
 */
function empty(info){
    if(info==''||info==undefined||info==null){
        return true;
    }
    return false;
}


var callback={
    render:''
}

function pageBar(formId,pageIndex,pageSize,render){
    if(!empty(render))
        callback.render=render;
    var form=$('#'+formId);
    var url=form.attr('action');
    var param={
        // 当前页码
        pageIndex:pageIndex,
        //页大小
        pageSize:pageSize
    };
    var params=$('#'+formId+' [dataName=form]');
    for(var i=0;i<params.length;i++){
        var name=$(params[i]).attr("name");

        var type=$(params[i]).attr("dataType");
        if(type=="long"){
            var value=$(params[i]).val();
            if(empty(value))
                continue;
        }
        if(type=='int' || type=='string'){
            var value;
            if(name=="username" || name=="findvalue"){
                value=$(params[i]).val();
            }else{
                value=$(params[i]).attr("value");
            }

            if(empty(value))
                continue;
            value=value;
        }else if(type=='date'){
            var value=$(params[i]).val();
            if(empty(value))
                continue;

            value=new Date(value.replace("-", "/").replace("-", "/"));
        }
        param[name]=value;
    }
    var result=invokeService(url,param,"get");
    if(result.status!=1000){
        prompt(result.message);
    }
//    var pageIndex=result.status!=1000?pageIndex:result.date.pageIndex;
    var pageIndex=pageIndex;
    var totalPages=result.status!=1000?0:result.date.totalpage;
//    var pageSize=result.status!=1000?pageSize:result.date.pageSize;
    var pageSize=pageSize;

    $(".pageBar").html("");
    var paging=
        "<div class='previous flipButton'>"+
        "<span id='previous' onclick=previous('"+formId+"',"+pageIndex+","+pageSize+","+totalPages+")>上一页</span>"+
        "</div>"+
        "<div class='numpages'>"+pageIndex+"</div>"+
        "<div class='next flipButton'>"+
        "<span id='next' onclick=next('"+formId+"',"+pageIndex+","+pageSize+","+totalPages+")>下一页 </span>"+
        "</div>"+
        "<div class='pageCount'>共<span>"+totalPages+"</span>页</div>"+
        "<div class='jumpPages'>跳转到 <input type='text' id='jumpPages'></div>"+
        "<div class='determineJump' onclick=jumpPages('"+formId+"',"+pageSize+","+totalPages+")>确定</div>"
    $(".pageBar").append(paging);
    if(pageIndex>=totalPages){
        document.getElementById("next").onclick = null;
    }
    if(pageIndex == 1){
        document.getElementById("previous").onclick = null;
    }
    callback.render(result)
}
/**
 * 跳转指定页数
 * @param formId 表单ID
 * @param junmPage 跳转页码
 * @param pageSize 页大小
 * @param totalPages 总页数
 */
function jumpPages(formId,pageSize,totalPages){
    var junmPage=document.getElementById("jumpPages").value;
    if(junmPage>0&&junmPage<=totalPages){
        pageBar(formId,junmPage,pageSize);
    }else{
        document.getElementById("jumpPages").value='';
    }
}
/**
 * 跳转上一页
 * @param formId 表单ID
 * @param pageIndex 当前页
 * @param pageSize 页大小
 * @param totalPages 总页数
 */
function previous(formId,pageIndex,pageSize,totalPages){
    var pageIndex=previousPage(pageIndex,totalPages);
    pageBar(formId,pageIndex,pageSize);
}
/**
 * 跳转下一页
 * @param formId 表单ID
 * @param pageIndex 当前页
 * @param pageSize 页大小
 * @param totalPages 总页数
 */
function next(formId,pageIndex,pageSize,totalPages){
    var pageIndex=nextPage(pageIndex,totalPages);
    pageBar(formId,pageIndex,pageSize);
}
/**
 * 计算上一页页码
 * @param pageIndex 当前页
 * @param totalPages 总页数
 * @returns {number} 上一页页数
 */
function previousPage(pageIndex,totalPages){
    var index=pageIndex-1;
    if(index>0&&index<=totalPages){
        return index;
    }
    return 1;
}
/**
 * 计算下一页页码
 * @param pageIndex 当前页
 * @param totalPages 总页数
 * @returns {*} 下一页页数
 */
function nextPage(pageIndex,totalPages){
    var index=pageIndex+1;
    if(index>0&&index<=totalPages){
        return index;
    }
    return totalPages;
}
//保留两位小数
function decimal(num){
    var nums=Number(num);
    return Math.floor(nums * 100) / 100
}
//下拉列表
$(function(){
    var i = $(".screen ul").length;
    for(var j=0;j<i;j++){
        $(".screen ul:eq("+j+") li").eq(0).addClass("xlactive");
    }

})
$(".content").on("mouseenter",".screen ul li",function(){
    var i =  $(".screen ul li").index(this);
    $(".screen ul li").eq(i).addClass("ydactive");
});
$(".content").on("mouseleave",".screen ul li",function(){
    var i =  $(".screen ul li").index(this);
    $(".screen ul li").eq(i).removeClass("ydactive");
});
$(".content").on("click",".screen span",function(){
    var i =  $(".screen span").index(this);
    $(".screen ul").eq(i).show();
    $(".screen ul:eq("+i+") li").click(function(){
        var y=$(".screen ul li").index(this);
        $(".screen ul:eq("+i+") li").removeClass("xlactive");
        $(".screen ul li").eq(y).addClass("xlactive");
        $(".screen ul li").eq(y).addClass("xlactive");
        $(".screenText").eq(i).html($(".screen ul li").eq(y).html());
        $(".screenText").eq(i).attr("value",$(".screen ul li").eq(y).attr("value"));
        $(".screen ul").hide();
    });
});

$(".content").on("mouseleave",".screen",function(){
    $(".screen ul").hide();
});
