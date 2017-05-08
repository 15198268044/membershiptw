$(function(){
        var xli = $(".screen ul").length;
        for(var xlj=0;xlj<xli;xlj++){
            $(".screen ul:eq("+xlj+") li").eq(0).addClass("xlactive");
        }
    //第一次加载
    withdrawalsRecordsList();
    backgroundSwitch()
    //查询按钮
    $('#query').click(function(){
        withdrawalsRecordsList()
    })

    $(document).bind('click', function(e) {
        var e = e || window.event; //浏览器兼容性
        var elem = e.target || e.srcElement;
        while (elem) { //循环判断至跟节点，防止点击的是div子元素
            if (elem.className && elem.className == 'details') {
                return;
            }else if(elem.id && elem.id == 'bankDetails'){
                return;
            }
            elem = elem.parentNode;
        }
        $('#bankDetails').remove(); //点击的不是div或其子元素
    });


})

function backgroundSwitch(){
    var arr=$("table tbody tr").length;
    for(var i=0;i<arr;i++){
        if(i%2==0){
            $("table tbody tr").eq(i).css("background","#F7F8FC")
        }
    }
}
function withdrawalsRecordsList(){
    pageBar("withdrawalsRecordsListForm",1,5,function(result){
        if(result.status!=1000){
            $("#table>tbody").html("");
            return;
        }
        $("#table>tbody").html("");
        var data=result.date;
        var html='';
        var detailsHtml='';

        for(var i =0;i<data.records.length;i++){
            html+=
                    "<tr>"+
                    "<td>"+
                        data.records[i].applyTime+
                    "</td>"
                        if(data.records[i].type==0){
                           html+= "<td>" +
                            "<div class='details'>个人银行 | "+data.records[i].bankInfo.bankname+"<img src='/webpage/images/tr-black.png'></div>"+
                               "<p>"+data.records[i].bankInfo.banknum+"</p>"+
                            "</td>"
                        }else{
                            html+= "<td><div class='details'>支付宝 | "+data.records[i].phone+"</div></td>"

                        }
            html+=
                    "<td style='color: #DA3644;font-weight: bold'>"+
                    data.records[i].tatalmoney+
                    "</td>"+
                    "<td>"+
                    data.records[i].counterFee+"%"+
                    "</td>"+
                    "<td style='color: #41A265;font-weight: bold'>"+
                        data.records[i].money+
                    "</td>"+
                    "<td>"+
                    data.records[i].handlerTime+
                    "</td>"+
                    "<td>"+
                    data.records[i].status+
                    "</td>"+
                    "<td>"+
                    data.records[i].realname+
                     "<p>"+data.records[i].phone+"</p>"+
                    "</td>"+
                    "<td>"+
                    data.records[i].mark+
                    "</td>"+
                    "</tr>"
        }

        $("#table tbody").html(html);
        $("#content").on("click",".details",function(){
            var i =$(".details").index(this);
            if(data.records[i].type==undefined||data.records[i].type==null){
                return;
            }
            else if(data.records[i].type==1){
                return;
            }
            detailsHtml="<div class='bankDetails' id='bankDetails'>"+
                "<div>"+
                "<label>收款银行 :</label>"+
                "<span>"+data.records[i].bankInfo.bankname+"</span>"+
                "</div>"+
                "<div>"+
                "<label>开户银行 :</label>"+
                "<span>"+data.records[i].bankInfo.address+"</span>"+
                "</div>"+
                "<div>"+
                "<label>银行账户 :</label>"+
                "<span>"+data.records[i].bankInfo.banknum+"</span>"+
                "</div>"+
                "<div>"+
                "<label>账户名称 :</label>"+
                "<span>"+data.records[i].bankInfo.bankaccountname+"</span>"+
                "</div>"+
                "</div>"
            $(".bankDetails").remove();
            $(".details").eq(i).append(detailsHtml);
        });
        backgroundSwitch();
    })
}