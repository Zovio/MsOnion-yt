<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../header.jsp"/>

<div id="verifyCodef-div" style="display: block;">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName"><i>*</i>类型：</label>
            <p class="relative">
                <select class="brandTxt" id="verifyCodef-type">
                    <c:forEach items="${verifyCodePictureTypeMap}" var="p">
                        <option value="${p.key}">${p.value}</option>
                    </c:forEach>
                </select>
                <i class="downIcon"></i>
            </p>
            <p class="palce-hint hint4">*类型必填</p>
        </div>
        <!-- 图片上传 start -->
        <input id="_targerId" type="hidden"/>
        <form id="uploadForm">
            <input name="filePath" id="_filePath" type="hidden"/>
            <input name="remark" id="_remark" type="hidden"/>
            <input id="uploading" style="display: none" type="file" name="files"
                   onchange="vUploadS('',1,'uploadForm',vUpCallback);"/>
        </form>
        <div class="uploadImg-col">
            <span class="img-title pd"><em class="left"><i>*</i>验证码图片：</em><em class="ts">（支持jpg/png，宽 x 高)</em></span>
            <div class="upload-cont wtdm fr">
                <div class="upload-col">
                    <div class="uploadBtn" onclick="vUploadClick('verifyCode','','verifyCodef-upload-img');">
                        <a href="javascript:;" class="upload-icon"></a>
                        <span class="font-btn">上传图片</span>
                    </div>
                    <p class="size-ts">${verifyCodePictureSize} x ${verifyCodePictureSize}</p>
                    <div class="upload-img" id="verifyCodef-upload-img">
                        <p class="img-cont">
                            <c:if test="${not empty itemOrigin.nationalFlagImageBigDynamicS}">
                                <img src="${itemOrigin.nationalFlagImageBigDynamicS}"
                                     messageid='${itemOrigin.nationalFlagImageBig}' alt="">
                            </c:if>
                        </p>
                        <span class="close"><i></i> </span>
                        <span class="img-name"></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="winRow2">
            <label class="brandName">备注：</label>
            <textarea class="brandTxt" id="verifyCodef-remark">${contentKeyword.data.remark}</textarea>
        </div>
        <!-- 图片上传 end -->
    </div>
</div>

<script type="text/javascript">
    /**
     * 1.点击上传按钮
     * @param filePath 文件路径
     * @param remark 备注
     * @return {*|jQuery}
     */
    function vUploadClick(filePath, remark,targeId){
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
    function vUploadS(url,type,formid,callback) {
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
        if(type ==1 && $.inArray(MS.getFileExtension(f),MS.VERIFYPIC) < 0){
            layer.msg('图片格式不对，目前只支持' +MS.VERIFYPIC.join(','), {icon: 2,offset: '100px'});
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
    function submitForm() {
        var pass = true;

        var imagePath = $("#verifyCodef-upload-img").find('.img-cont').find('img:first').attr("messageid");
        if (imagePath == ""||!imagePath) {
            layer.msg('请上传验证码图片！', {icon: 2});
            Ms.closeLoading();
            pass = false;
        }

        if (pass) {
            $.ajax({
                type: "post",
                url: "/system/verifyCode/list/doSave",
                data: {
                    type: $("#verifyCodef-type").val(),
                    remark: $("#verifyCodef-remark").val(),
                    imagePath: $("#verifyCodef-upload-img").find('.img-cont').find('img:first').attr("messageid") || null,
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.msg('操作成功！', {icon: 1});
                        parent.layer.close(index);
                    } else {
                        parent.layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                    }
                    parent.$('#verifyCode-tableList').datagrid('reload', {});
                },
                error: function (d) {
                    parent.layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                }
            });
        }
    }
    /**
     * 3.回现图片，和保存messageId
     * @param result
     */
    function vUpCallback(result) {
        //
        if(result && result.status ==200){
            if(result.data.length == 0){
                layer.msg(MSYT.UPLOAD_FAIL_MSG, {icon: 2, time:1500,offset: '100px'});
                return;
            }
            if(result.data[0].uploadStatus ==1){
                var $targerId = $('#'+ $('#_targerId').val());
                var path = imgurl + result.data[0].path;
                var dataurl = result.data[0].path;
                $targerId.find('.img-cont').html('<img src="'+path+'" messageid='+dataurl+' />');
            }else {
                layer.msg(MSYT.UPLOAD_FAIL_MSG, {icon: 2, time:1500,offset: '100px'});
            }
        }else {
            layer.msg(MSYT.UPLOAD_FAIL_MSG, {icon: 2, time:1500,offset: '100px'});
        }
    }
</script>

<jsp:include page="../../footer.jsp"/>