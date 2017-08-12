
/* 展开搜索栏目 */
$(document).on('click','.screen-show',function(){
    $(this).hide();
    $(this).next().show();
    $(this).parent().css("height","auto");
});

$(document).on('click','.screen-hide',function(){
    $(this).hide();
    $(this).prev().show();
    $(this).parent().css("height","52px");
});


/* ie8浏览器下把select小图标隐藏  */
function isIE() {
    var browser = navigator.appName;
    var b_version = navigator.appVersion;
    var version = b_version.split(";");
    var trim_Version = version[1].replace(/[ ]/g, "");
    if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE8.0"){
        $("select").attr("style","padding:5px 10px\0");
        $(".downIcon").attr("style","display:none");
        $('.upfile').attr("style","width:46px");
        $('.uploading').attr("style","width:60px");
    }
}


$(function(){

   /** 全局设置 session失效跳转到 登录页 */
   $.ajaxSetup({
        cache: false,
        complete:function(XMLHttpRequest,textStatus){
            parent.layer.closeAll('loading');
            layer.closeAll('loading');
            var text = XMLHttpRequest.responseText;
            try {
                var $result = JSON.parse(text);
                if($result.status == 1 ){
                    window.location.reload();
                }
            } catch(e) {
                ;
            }
        }
    });

    /* 鼠标移动到上传的图片出现删除icon */
    $('.upload-img').hover(function(){
        var $p = $(this).find(".img-cont");
        if($p.children('img').length == 0){
            $(this).children(".close").hide();
        }else{
            $(this).children(".close").show();
        }
    },function(){
        $(this).children(".close").hide();
    });


    /* 图片删除icon 点击事件 */
    $('.upload-img .close').on('click',function () {
        var $this = $(this);
        $this.prev().empty();
        // $this.next().empty().removeAttr('title');
    })


    //isIE();

})





/* 验证字段必填和字数限制  e:当前元素(直接传this即可) str:当前元素的值(直接穿this.value即可) obj1:控制字数提示信息，obj2:必填提示信息  */
function RequiredAndNumber(e,str,num,obj1,obj2) {
    if(str == ""){
        $(e).css("border","1px solid #ff2c41");
        $(e).next().html(obj1);
    }else if(str != ""){
        if(str.length > num){
            $(e).css("border","1px solid #ff2c41");
            $(e).next().html(obj2);
        }else{
            $(e).css("border","1px solid #dbdcde");
            $(e).next().html("");
        }
    }else{
        $(e).css("border","1px solid #dbdcde");
        $(e).next().html(obj1);
    }
}
/* 验证字段必填和字数限制  e:当前元素(直接传this即可) str:当前元素的值(直接穿this.value即可) obj:必填提示信息  */
function selectRequired(e,str,obj){
    if(str == "请选择"){
        $(e).css("border","1px solid #ff2c41");
        $(e).parent().next().html(obj);
    }else{
        $(e).css("border","1px solid #dbdcde");
        $(e).parent().next().html("");
    }
}
/* 验证字段必填和字数限制  e:当前元素(直接传this即可) str:当前元素的值(直接穿this.value即可) obj:必填提示信息  */
function inputRequired(e,str,obj){
    if(str == ""){
        $(e).css("border","1px solid #ff2c41");
        $(e).next().html(obj);
    }else{
        $(e).css("border","1px solid #dbdcde");
        $(e).next().html("");
    }
}
/* 验证字段必填和字数限制  e:当前元素(直接传this即可) str:当前元素的值(直接穿this.value即可) obj:控制字数提示信息  */
function inputNumber(e,str,num,obj){
    if(str.length > num){
        $(e).css("border","1px solid #ff2c41");
        $(e).next().html(obj);
        return false;
    }else{
        $(e).css("border","1px solid #dbdcde");
        $(e).next().html("");
        return true;
    }
}

/**
 * 1.点击上传按钮
 * @param filePath 文件路径
 * @param remark 备注
 * @return {*|jQuery}
 */
function uploadClick(filePath, remark,targeId){
    $('#_filePath').val(filePath);
    $('#_remark').val(remark);
    $('#_targerId').val(targeId);
    return $('#uploading').click();
}

/**
 * 2.后台上传到 文件资源中心
 * @param url 请求url
 * @param type 文件类型 1.图片
 * @param formId formid
 * @param callback 回调函数
 */
function uploadS(url,type,formid,callback) {
    // 有时候 fileResourceApi  imgurl 没有值,去查询
    if(!fileResourceApi || !imgurl){
        $.ajax({
            timeout : Ms.AJAX_TIME_OUT ,
            type : "POST",
            url : '/sys/initFileresource',
            async: false,
        }).done(function (result) {
            fileResourceApi= result.data.split(",")[0];
            imgurl =  result.data.split(",")[1];
        });
    }

    var file = document.getElementById("uploading");
    var f= file.value;
    if(type ==1 && $.inArray(MS.getFileExtension(f),MS.PIC) < 0){
        layer.msg('图片格式不对，目前只支持' +MS.PIC.join(','), {icon: 2,offset: '100px'});
        document.getElementById(formid).reset();
        return false;
    }

    // 判断大小
    if(MSYT.PIC_UPLOAD_MAX_SIZE < MS.getFileSize(file)){
        layer.msg('图片过大，目前最大只支持1MB', {icon: 2,offset: '100px'});
        document.getElementById(formid).reset();
        return false;
    }

    var formData = new FormData($('#'+formid+'')[0]);
    var inx = layer.msg(MSYT.UPLOAD_LOADING_MSG, {icon: 16,shade: 0.01,offset: '100px',time:MSYT.UPLOAD_TIME});
    $.ajax({
        url: fileResourceApi + '/cos/uploadFiles',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false
    }).always(function () {
        document.getElementById(formid).reset();
    }).done(function (result) {
        layer.close(inx);
        callback(result);
    }).fail(function () {
        layer.close(inx);
        layer.msg(MSYT.UPLOAD_FAIL_MSG, {icon: 2, time:1500,offset: '100px'});
    });
}

/**
 * 3.回现图片，和保存messageId
 * @param result
 */
function upCallback(result) {
    //
    if(result && result.status ==200){
        if(result.data.length == 0){
            layer.msg(MSYT.UPLOAD_FAIL_MSG, {icon: 2, time:1500,offset: '100px'});
            return;
        }
        if(result.data[0].uploadStatus ==1){
            var $targerId = $('#'+ $('#_targerId').val());
            var path = imgurl + result.data[0].path;
            var dataurl = result.data[0].messageId + "||" + result.data[0].path;
            $targerId.find('.img-cont').html('<img src="'+path+'" messageid='+dataurl+' />');
        }else {
            layer.msg(MSYT.UPLOAD_FAIL_MSG, {icon: 2, time:1500,offset: '100px'});
        }
    }else {
        layer.msg(MSYT.UPLOAD_FAIL_MSG, {icon: 2, time:1500,offset: '100px'});
    }
}

/** easyui datagird 跨页勾选 start */
/**
 *  表格单选行
 * @param idx 当前表格的idx
 * @param arr idx数组，需要初始化
 * @param txtArr 文本数组，idx|name
 */
function girdAddRow(row,arr,txtArr){
    if($.inArray(row.idx+"", arr) == -1){
        arr.push(row.idx+"");
    }
    if($.inArray(row.idx + "|" + row.name, txtArr) == -1){
        txtArr.push(row.idx + "|" + row.name);
    }
}

/**
 * 表格全选
 * @param rows 所有行
 * @param arr idx数组
 */
function girdAddAll(rows,arr,txtArr){
    for(var i=0; i<rows.length; i++){
        girdAddRow(rows[i],arr,txtArr);
    }
}

/**
 * 表格去掉单选
 * @param idx 当前行idx
 * @param arr idx数组
 */
function girdRemoveRow(row,arr,txtArr){
    var inx = $.inArray(row.idx+"", arr);
    if(inx !=-1){
        arr.splice(inx, 1);
        txtArr.splice($.inArray(row.idx+ "|" + row.name, txtArr), 1);
    }
}

/**
 * 表格全部不选
 * @param rows 所有行
 * @param arr idx数组
 */
function girdRemoveAll(rows,arr,txtArr){
    for(var i=0; i< rows.length; i++){
        girdRemoveRow(rows[i],arr,txtArr);
    }
}
/** easyui datagird 跨页勾选 end */


/**
 *  通用操作状态
 * @param url  url
 * @param msg 提示语，非必填，
 * @param reloadTableId 操作成功后需要刷新tableId
 */
function $updateStatus(url, msg, reloadTableId){
    var info ='';
    var status = url.substr(-1);
    if(status == '1'){
        info = msg || MSYT.RECOVER_MSG;
    }else if(status == '2'){
        info = msg || MSYT.DISABLED_MSG;
    }else if(status == '0'){
        info = msg || MSYT.DELETE_MSG;
    }
   layer.confirm(info, {
        btn: ['确定','关闭'] //按钮
    }, function(){
        layer.load(2, {shade : 0.01, time : 5000});
        $.ajax({
            timeout : Ms.AJAX_TIME_OUT ,
            type : "POST",
            url : url,
            cache : false,
        }).done(function (result) {
            if(Ms.SUC_CODE == result.status){
                layer.msg(MSYT.SUC_MSG, {icon: 1});
                $('#'+reloadTableId).datagrid('reload',{});
            }else {
                layer.msg(result.msg, {icon: 2});
            }
            layer.close('loading');
        }).fail(function(result) {
            layer.msg(MSYT.ERROR_MSG, {icon: 2});
            layer.close('loading');
        });
    });
}































